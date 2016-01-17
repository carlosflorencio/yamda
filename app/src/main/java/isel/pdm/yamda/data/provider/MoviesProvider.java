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

/**
 * Movies content provider, stores all the data in a sqlite mOpenHelper
 */
public class MoviesProvider extends ContentProvider {

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private MoviesDatabaseHelper mOpenHelper;

    static final int MOVIE_LIST = 100;
    static final int MOVIE_ID = 101;
    static final int FOLLOW = 200;
    static final int FOLLOW_ID = 201;

    static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = MoviesContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, MoviesContract.PATH_MOVIES, MOVIE_LIST);
        matcher.addURI(authority, MoviesContract.PATH_MOVIES + "/#", MOVIE_ID);

        matcher.addURI(authority, MoviesContract.PATH_FOLLOW, FOLLOW);
        matcher.addURI(authority, MoviesContract.PATH_FOLLOW + "/#", FOLLOW_ID);

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

        Cursor retCursor;
        switch (sUriMatcher.match(uri)) {
            case MOVIE_LIST:
                // default sort is by popularity
                if (TextUtils.isEmpty(sortOrder))
                    sortOrder = MoviesContract.MovieEntry.COLUMN_POPULARITY + " DESC";
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
                        MoviesContract.MovieEntry.COLUMN_ID + " = ?",
                        new String[]{uri.getLastPathSegment()},
                        null,
                        null,
                        sortOrder
                );
                break;
            case FOLLOW:
                retCursor = getSimpleCursor(MoviesContract.FollowEntry.TABLE_NAME, projection, selection,
                                            selectionArgs, sortOrder);
                break;
            case FOLLOW_ID:
                retCursor = getSimpleCursor(MoviesContract.FollowEntry.TABLE_NAME, projection, MoviesContract.FollowEntry.COLUMN_MOVIE_ID + " = ?",
                                            new String[] {uri.getLastPathSegment()}, sortOrder);
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

        long id = 0;
        switch (sUriMatcher.match(uri)) {
            case MOVIE_LIST:
                id = db.insert(MoviesContract.MovieEntry.TABLE_NAME, null, values);
                //get the movie id, because the primary of movies is two tables id is not the real id
                int movieId = values.getAsInteger(MoviesContract.MovieEntry.COLUMN_ID);
                returnUri = MoviesContract.MovieEntry.buildMovieUri(movieId);
                break;
            case FOLLOW:
                id = db.insert(MoviesContract.FollowEntry.TABLE_NAME, null, values);
                returnUri = MoviesContract.FollowEntry.buildFollowUri((int) id);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (id <= 0) {
            throw new android.database.SQLException("Failed to insert row into " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int rowsDeleted;
        // this makes delete all rows return the number of rows deleted
        if (null == selection) selection = "1";

        switch (sUriMatcher.match(uri)) {
            case MOVIE_LIST:
                rowsDeleted = db.delete(MoviesContract.MovieEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case MOVIE_ID:
                rowsDeleted = db.delete(MoviesContract.MovieEntry.TABLE_NAME, MoviesContract.MovieEntry.COLUMN_ID + " = ?",
                                        new String[]{uri.getLastPathSegment()});
                break;
            case FOLLOW_ID:
                rowsDeleted = db.delete(MoviesContract.FollowEntry.TABLE_NAME, MoviesContract.FollowEntry.COLUMN_MOVIE_ID + " = ?",
                                        new String[]{uri.getLastPathSegment()});
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
                rowsUpdated = db.update(MoviesContract.MovieEntry.TABLE_NAME, values, MoviesContract.MovieEntry.COLUMN_ID + " = ?",
                                        new String[]{uri.getLastPathSegment()});
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
                return bulkInsertInTable(uri, values, db, MoviesContract.MovieEntry.TABLE_NAME);
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
    | Helpers
    |--------------------------------------------------------------------------
    */
    private Cursor getSimpleCursor(String table, String[] projection, String selection,
                                   String[] selectionArgs, String sort) {
        return mOpenHelper.getReadableDatabase().query(
                table,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sort
        );
    }

    private int bulkInsertInTable(Uri uri, ContentValues[] values, SQLiteDatabase db,
                                  String table) {
        db.beginTransaction();
        int returnCount = 0;
        try {
            for (ContentValues value : values) {
                long _id = db.insertOrThrow(table, null, value);
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
    }

    /*
    |--------------------------------------------------------------------------
    | Queries
    |--------------------------------------------------------------------------
    */
    // query builder that selects the movies where the language = system
    private static final SQLiteQueryBuilder sMoviesWhereLanguageEqualsSystem;

    static {
        sMoviesWhereLanguageEqualsSystem = new SQLiteQueryBuilder();

        sMoviesWhereLanguageEqualsSystem.setTables(MoviesContract.MovieEntry.TABLE_NAME);

        // movies_details.lang = ?
        sMoviesWhereLanguageEqualsSystem.appendWhere(
                MoviesContract.MovieEntry.COLUMN_LANG + " = '" + Locale.getDefault().getLanguage() + "'"
        );
    }
}
