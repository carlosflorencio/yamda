package isel.pdm.yamda.data.provider.table;

import android.database.sqlite.SQLiteDatabase;

/**
 * Class used to create the movies details table witch belongs to a movie
 * Can have multiple translations
 */
public class MoviesDetailsTable {

    public static final String NAME = "movies_details";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_LANG = "lang";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_RUNTIME = "runtime";
    public static final String COLUMN_OVERVIEW = "overview";

    // Database creation SQL statement
    private static final String CREATE = "CREATE TABLE " + NAME
            + "("
            + COLUMN_ID + " INTEGER NOT NULL, "
            + COLUMN_LANG + " TEXT NOT NULL, "
            + COLUMN_TITLE + " TEXT NOT NULL, "
            + COLUMN_RUNTIME + " INTEGER, "
            + COLUMN_OVERVIEW + " TEXT, "
            + "PRIMARY KEY(" + COLUMN_ID + ", " + COLUMN_LANG + "), "
            + "FOREIGN KEY(" + COLUMN_ID +") REFERENCES " + MoviesTable.NAME
                                        + "(" + MoviesTable.COLUMN_ID + ") ON DELETE CASCADE"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        //Do nothing on upgrade
    }
}
