package isel.pdm.yamda.data.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import isel.pdm.yamda.data.provider.table.FollowMoviesTable;
import isel.pdm.yamda.data.provider.table.MoviesDetailsTable;
import isel.pdm.yamda.data.provider.table.MoviesTable;

/**
 * Helper to creater the movies db
 */
public class MoviesDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 1;


    public MoviesDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /**
     * Enable foreign keys in sqlite
     * @param db
     */
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        MoviesTable.onCreate(db);
        MoviesDetailsTable.onCreate(db);
        FollowMoviesTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        MoviesTable.onUpgrade(db, oldVersion, newVersion);
        MoviesDetailsTable.onUpgrade(db, oldVersion, newVersion);
        FollowMoviesTable.onUpgrade(db, oldVersion, newVersion);
    }
}
