package isel.pdm.yamda.data.provider.table;

import android.database.sqlite.SQLiteDatabase;

public class MoviesTable {

    public static final String NAME = "movies";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ORIGINAL_TITLE = "original_title";
    public static final String COLUMN_BACKDROP = "backdrop";
    public static final String COLUMN_RELEASE_DATE = "release_date";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_POSTER = "poster";

    // Database creation SQL statement
    private static final String CREATE = "CREATE TABLE " + NAME
            + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_ORIGINAL_TITLE + " TEXT NOT NULL, "
            + COLUMN_BACKDROP + " TEXT NOT NULL, "
            + COLUMN_RELEASE_DATE + " TEXT NOT NULL, "
            + COLUMN_RATING + " REAL NOT NULL, "
            + COLUMN_POSTER + " TEXT NOT NULL "
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        //Do nothing on upgrade
    }

}
