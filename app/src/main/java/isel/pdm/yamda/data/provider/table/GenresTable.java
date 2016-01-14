package isel.pdm.yamda.data.provider.table;

import android.database.sqlite.SQLiteDatabase;

/**
 * This class is used to create the genres table
 */
public class GenresTable {

    public static final String NAME = "genres";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_LANG = "lang";

    //pivot table for the n to n relation with movies table
    public static final String PIVOT_NAME = "genres_movies";
    public static final String PIVOT_COLUMN_ID = "genre_id";
    public static final String PIVOT_COLUMN_MOVIE_ID = "movie_id";
    public static final String PIVOT_COLUMN_LANG = "lang";

    // Database creation SQL statement
    private static final String CREATE = "CREATE TABLE " + NAME
            + "("
            + COLUMN_ID + " INTEGER NOT NULL, "
            + COLUMN_TITLE + " TEXT NOT NULL, "
            + COLUMN_LANG + " TEXT NOT NULL, "
            + "PRIMARY KEY(" + COLUMN_ID + ", " + COLUMN_LANG + ")"
            + ");";

    private static final String CREATE_PIVOT = "CREATE TABLE " + PIVOT_NAME
            + "("
            + PIVOT_COLUMN_ID + " INTEGER NOT NULL, "
            + PIVOT_COLUMN_MOVIE_ID + " INTEGER NOT NULL, "
            + PIVOT_COLUMN_LANG + " TEXT NOT NULL, "
            + "FOREIGN KEY(" + PIVOT_COLUMN_MOVIE_ID + ") REFERENCES " + MoviesTable.NAME + "(" + MoviesTable.COLUMN_ID + ") ON DELETE CASCADE, "
            + "FOREIGN KEY(" + PIVOT_COLUMN_ID + ", " + PIVOT_COLUMN_LANG + ") REFERENCES " + GenresTable.NAME + "(" + GenresTable.COLUMN_ID + ", " + GenresTable.COLUMN_LANG + ") ON DELETE CASCADE,"
            + "PRIMARY KEY(" + PIVOT_COLUMN_ID + ", " + PIVOT_COLUMN_MOVIE_ID + ", " + PIVOT_COLUMN_LANG + ")"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE);
        database.execSQL(CREATE_PIVOT);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        //Do nothing on upgrade
    }
}
