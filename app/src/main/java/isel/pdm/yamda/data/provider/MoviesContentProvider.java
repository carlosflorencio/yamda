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

import isel.pdm.yamda.data.provider.table.MoviesDetailsTable;
import isel.pdm.yamda.data.provider.table.MoviesTable;

/**
 * Movies content provider, stores all the data in a sqlite database
 */
public class MoviesContentProvider extends ContentProvider {

    //Uri matcher
    private static final String AUTHORITY = "isel.pdm.yamda.provider";

    //types
    private static final int NOW_LIST = 1;
    private static final int SOON_LIST = 2;
    private static final int MOVIE = 3;


    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(AUTHORITY, "movie/list/now", NOW_LIST);
        sURIMatcher.addURI(AUTHORITY, "movie/list/soon", SOON_LIST);
        sURIMatcher.addURI(AUTHORITY, "movie/#", MOVIE);
    }

    private MoviesDatabaseHelper database;

    @Override
    public boolean onCreate() {
        database = new MoviesDatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        // default sort is by popularity
        if (TextUtils.isEmpty(sortOrder)) sortOrder = MoviesTable.COLUMN_POPULARITY + " DESC";

        // join movies details table
        queryBuilder.setTables(MoviesTable.NAME + " INNER JOIN " + MoviesDetailsTable.NAME + " ON (" +
            MoviesDetailsTable.NAME + "." + MoviesDetailsTable.COLUMN_ID + " = " + MoviesTable.NAME + "." + MoviesTable.COLUMN_ID + ")"
        );

        // where movies details lang = system
        queryBuilder.appendWhere(MoviesDetailsTable.COLUMN_LANG + " = '" + Locale.getDefault().getLanguage() + "'");

        switch (sURIMatcher.match(uri)) {
            case NOW_LIST:
                // where movies list is now
                queryBuilder.appendWhere(" AND " + MoviesTable.COLUMN_LIST_TYPE + " = '" + MoviesTable.TYPE_NOW + "'");
                break;
            case SOON_LIST:
                // where movies list is soon
                queryBuilder.appendWhere(" AND " + MoviesTable.COLUMN_LIST_TYPE + " = '" + MoviesTable.TYPE_SOON + "'");
                break;
            case MOVIE:
                // where movie id = same as uri
                queryBuilder.appendWhere(" AND " + MoviesTable.COLUMN_ID + " = " + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        SQLiteDatabase db = this.database.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection,
                                           selectionArgs, null, null, sortOrder);

        // make sure that potential listeners are getting notified
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
