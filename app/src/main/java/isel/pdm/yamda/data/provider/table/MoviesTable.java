package isel.pdm.yamda.data.provider.table;

import android.database.sqlite.SQLiteDatabase;

/**
 * Class used to create the movies table where it belongs to a list type and have a movie details info
 */
public class MoviesTable {

    public static final String NAME = "movies";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ORIGINAL_TITLE = "original_title";
    public static final String COLUMN_BACKDROP = "backdrop";
    public static final String COLUMN_RELEASE_DATE = "release_date";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_POSTER = "poster";
    public static final String COLUMN_POPULARITY = "popularity";
    public static final String COLUMN_LIST_TYPE = "list_type";
    public static final String COLUMN_LANG = "lang";
    public static final String COLUMN_TITLE = "title";

    //flag to know if the movie details are present
    public static final String COLUMN_DOWNLOADED = "downloaded";
    public static final String COLUMN_OVERVIEW = "overview";
    public static final String COLUMN_RUNTIME = "runtime";

    public static final String TYPE_SOON = "soon";
    public static final String TYPE_NOW = "now";

    // Database creation SQL statement
    private static final String CREATE = "CREATE TABLE " + NAME
            + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_ORIGINAL_TITLE + " TEXT NOT NULL, "
            + COLUMN_BACKDROP + " TEXT NOT NULL, "
            + COLUMN_RELEASE_DATE + " TEXT NOT NULL, "
            + COLUMN_RATING + " REAL NOT NULL, "
            + COLUMN_POPULARITY + " REAL NOT NULL, "
            + COLUMN_POSTER + " TEXT NOT NULL, "
            + COLUMN_LIST_TYPE + " TEXT NOT NULL, "
            + COLUMN_LANG + " TEXT NOT NULL, "
            + COLUMN_TITLE + " TEXT NOT NULL, "
            + COLUMN_DOWNLOADED + " INTEGER, "
            + COLUMN_OVERVIEW + " TEXT, "
            + COLUMN_RUNTIME + " INTEGER"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        //Do nothing on upgrade
    }

}
