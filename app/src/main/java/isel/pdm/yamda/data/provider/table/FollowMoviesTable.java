package isel.pdm.yamda.data.provider.table;

import android.database.sqlite.SQLiteDatabase;

/**
 * This class is used to create the follow movies table witch knows witch movies the user is
 * following
 */
public class FollowMoviesTable {

    public static final String NAME = "movies_followed";
    public static final String COLUMN_ID = "_movie_id";

    // Database creation SQL statement
    private static final String CREATE = "CREATE TABLE " + NAME
            + "("
            + COLUMN_ID + " INTEGER NOT NULL, "
            + "PRIMARY KEY(" + COLUMN_ID  + "), "
            + "FOREIGN KEY(" + COLUMN_ID +") REFERENCES " + MoviesTable.NAME + "(" + MoviesTable.COLUMN_ID + ") ON DELETE CASCADE"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        //Do nothing on upgrade
    }
}
