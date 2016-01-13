package isel.pdm.yamda.data.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.Locale;

import isel.pdm.yamda.data.provider.table.FollowMoviesTable;
import isel.pdm.yamda.data.provider.table.MoviesTable;

/**
 * Movies content provider, stores all the data in a sqlite mOpenHelper
 */
public class MoviesProvider extends ContentProvider {

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private MoviesDatabaseHelper mOpenHelper;

    static final int MOVIE_LIST = 100;
    static final int MOVIE_ID = 200;
    static final int FOLLOW = 300;

    static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = MoviesContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, MoviesContract.PATH_MOVIES, MOVIE_LIST);
        matcher.addURI(authority, MoviesContract.PATH_MOVIES + "/#", MOVIE_ID);
        matcher.addURI(authority, MoviesContract.PATH_FOLLOW, FOLLOW);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new MoviesDatabaseHelper(getContext());
        // success creating, return true
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {

        // default sort is by popularity
        if (TextUtils.isEmpty(sortOrder)) sortOrder = MoviesTable.COLUMN_POPULARITY + " DESC";

        Cursor retCursor;
        switch (sUriMatcher.match(uri)) {
            case MOVIE_LIST:
                retCursor = sMoviesWhereLanguageEqualsSystem.query(
                        mOpenHelper.getReadableDatabase(),
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case MOVIE_ID:
                retCursor = sMoviesWhereLanguageEqualsSystem.query(
                        mOpenHelper.getReadableDatabase(),
                        projection,
                        MoviesTable.COLUMN_ID + " = ?",
                        new String[] { uri.getLastPathSegment() },
                        null,
                        null,
                        null
                );
                break;
            case FOLLOW:
                retCursor = mOpenHelper.getReadableDatabase().query(
                        FollowMoviesTable.NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        // make sure that potential listeners are getting notified
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case MOVIE_LIST:
                return MoviesContract.MovieEntry.CONTENT_TYPE;
            case MOVIE_ID:
                return MoviesContract.MovieEntry.CONTENT_ITEM_TYPE;
            case FOLLOW:
                return MoviesContract.FollowEntry.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        Uri returnUri;

        switch (sUriMatcher.match(uri)) {
            case MOVIE_LIST: {
                long _id = db.insert(MoviesTable.NAME, null, values);
                if ( _id > 0 )
                    returnUri = MoviesContract.MovieEntry.buildMovieUri((int)_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);

        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int rowsDeleted;
        // this makes delete all rows return the number of rows deleted
        if ( null == selection ) selection = "1";

        switch (sUriMatcher.match(uri)) {
            case MOVIE_LIST:
                rowsDeleted = db.delete(MoviesTable.NAME, selection, selectionArgs);
                break;
            case MOVIE_ID:
                rowsDeleted = db.delete(MoviesTable.NAME, MoviesTable.COLUMN_ID + " = ?", new String[] {uri.getLastPathSegment()});
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        // Because a null deletes all rows
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int rowsUpdated;

        switch (sUriMatcher.match(uri)) {
            case MOVIE_ID:
                rowsUpdated = db.update(MoviesTable.NAME, values, MoviesTable.COLUMN_ID + " = ?",
                                        new String[] {uri.getLastPathSegment()});
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        switch (sUriMatcher.match(uri)) {
            case MOVIE_LIST:
                db.beginTransaction();
                int returnCount = 0;
                try {
                    for (ContentValues value : values) {
                        long _id = db.insert(MoviesTable.NAME, null, value);
                        if (_id != -1) {
                            returnCount++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri, null);
                return returnCount;
            default:
                return super.bulkInsert(uri, values);
        }
    }

    @Override
    public void shutdown() {
        mOpenHelper.close();

        super.shutdown();
    }

    /*
        |--------------------------------------------------------------------------
        | Queries
        |--------------------------------------------------------------------------
        */
    // query builder that joins movies with details
    private static final SQLiteQueryBuilder sMoviesWhereLanguageEqualsSystem;

    static{
        sMoviesWhereLanguageEqualsSystem = new SQLiteQueryBuilder();

        sMoviesWhereLanguageEqualsSystem.setTables(MoviesTable.NAME);

        // movies_details.lang = ?
        sMoviesWhereLanguageEqualsSystem.appendWhere(
            MoviesTable.COLUMN_LANG + " = '" + Locale.getDefault().getLanguage() + "'"
        );
    }
}
