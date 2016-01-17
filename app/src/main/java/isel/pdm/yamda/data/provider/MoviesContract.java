package isel.pdm.yamda.data.provider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Contract of the movies content provider
 * This class knows how to create all the uris of the content provider and contains
 * information about tables columns
 */
public class MoviesContract {

    public static final String CONTENT_AUTHORITY = "isel.pdm.yamda.provider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    // All paths (appended to base content URI for possible URI's)
    public static final String PATH_MOVIES = "movie";
    public static final String PATH_FOLLOW = "follow";


    /**
     * Inner class that defines the table contents of the movies table
     */
    public static final class MovieEntry implements BaseColumns {
        /*
        |--------------------------------------------------------------------------
        | Table information
        |--------------------------------------------------------------------------
        */
        public static final String TABLE_NAME = "movies";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_ORIGINAL_TITLE = "original_title";
        public static final String COLUMN_RELEASE_DATE = "release_date";
        public static final String COLUMN_RATING = "rating";
        public static final String COLUMN_POSTER = "poster";
        public static final String COLUMN_POPULARITY = "popularity";
        public static final String COLUMN_LIST_TYPE = "list_type";
        public static final String COLUMN_LANG = "lang";
        public static final String COLUMN_TITLE = "title";

        //two type of movies list
        public static final String TYPE_SOON = "soon";
        public static final String TYPE_NOW = "now";


        /*
        |--------------------------------------------------------------------------
        | Uri information
        |--------------------------------------------------------------------------
        */
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIES;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIES;

        public static Uri buildMovieUri(int id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    /**
     * Inner class that defines the table contents of the follow table
     */
    public static final class FollowEntry implements BaseColumns {
        /*
        |--------------------------------------------------------------------------
        | Table information
        |--------------------------------------------------------------------------
        */
        public static final String TABLE_NAME = "movies_followed";
        public static final String COLUMN_MOVIE_ID = "_movie_id";
        public static final String COLUMN_MOVIE_LIST = "_movie_list_type";

        /*
        |--------------------------------------------------------------------------
        | Uri information
        |--------------------------------------------------------------------------
        */
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_FOLLOW).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FOLLOW;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FOLLOW;

        public static Uri buildFollowUri(int id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
