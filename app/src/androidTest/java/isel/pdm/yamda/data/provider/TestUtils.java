package isel.pdm.yamda.data.provider;

import android.content.ContentValues;
import android.database.Cursor;
import android.test.AndroidTestCase;

import java.util.Locale;
import java.util.Map;
import java.util.Set;

import isel.pdm.yamda.data.provider.table.GenresTable;
import isel.pdm.yamda.data.provider.table.MoviesTable;
import isel.pdm.yamda.model.Genre;

public class TestUtils extends AndroidTestCase {

    static class Movie {
        public int id;
        public String original_title;
        public String backdrop;
        public String release_date;
        public double rating;
        public String poster;
        public String list_type;
        public double popularity;

        public String lang;
        public String title;
        public boolean downloaded;
        public int runtime;
        public String overview;

        public Movie(int id, String original_title, String backdrop, String release_date,
                     double rating,
                     String poster, String list_type, double popularity, String lang, String title,
                     boolean downloaded, int runtime,
                     String overview) {
            this.downloaded = downloaded;
            this.overview = overview;
            this.runtime = runtime;
            this.title = title;
            this.lang = lang;
            this.popularity = popularity;
            this.list_type = list_type;
            this.poster = poster;
            this.rating = rating;
            this.release_date = release_date;
            this.backdrop = backdrop;
            this.original_title = original_title;
            this.id = id;
        }

        public Movie(int id, String original_title, String backdrop, String release_date,
                     double rating,
                     String poster, String list_type, double popularity, String lang,
                     String title) {
            this.title = title;
            this.lang = lang;
            this.popularity = popularity;
            this.list_type = list_type;
            this.poster = poster;
            this.rating = rating;
            this.release_date = release_date;
            this.backdrop = backdrop;
            this.original_title = original_title;
            this.id = id;
        }
    }

    //important: should be ordered by popularity to some tests work
    public static Movie[] defaultMovies = new Movie[]{
            new Movie(1, "Original: Star Wars", "path/back", "2015-12-01", 8.2, "path/poster",
                      MoviesTable.TYPE_NOW, 9, "en", "EN: Star Wars", true, 124, "EN: Overview"),
            new Movie(2, "Original: Lego", "path/back", "2015-11-05", 7.5, "path/poster",
                      MoviesTable.TYPE_NOW, 8.5, "en", "EN: LEGO"),
            new Movie(3, "Original: Joy", "path/back", "2015-12-25", 7, "path/poster",
                      MoviesTable.TYPE_NOW, 8.0, "en", "EN: Joy"),
            new Movie(4, "Original: Big", "path/back", "2016-01-06", 5.4, "path/poster",
                      MoviesTable.TYPE_SOON, 7.6, "en", "EN: Big", true, 215, "EN: Overview"),
            new Movie(5, "Original: Small", "path/back", "2016-01-03", 5, "path/poster",
                      MoviesTable.TYPE_SOON, 7.3, "en", "EN: Small"),
    };

    public static Genre[] defaultGenres = new Genre[] {
            new Genre(1, "Acção"),
            new Genre(2, "Drama"),
            new Genre(3, "Romance")
    };

    /*
    |--------------------------------------------------------------------------
    | Create values
    |--------------------------------------------------------------------------
    */
    static ContentValues createMovieContent(Movie m) {

        ContentValues testValues = new ContentValues();
        testValues.put(MoviesTable.COLUMN_ID, m.id);
        testValues.put(MoviesTable.COLUMN_ORIGINAL_TITLE, m.original_title);
        testValues.put(MoviesTable.COLUMN_BACKDROP, m.backdrop);
        testValues.put(MoviesTable.COLUMN_RELEASE_DATE, m.release_date);
        testValues.put(MoviesTable.COLUMN_RATING, m.rating);
        testValues.put(MoviesTable.COLUMN_POSTER, m.poster);
        testValues.put(MoviesTable.COLUMN_LIST_TYPE, m.list_type);
        testValues.put(MoviesTable.COLUMN_POPULARITY, m.popularity);
        testValues.put(MoviesTable.COLUMN_LANG, m.lang);
        testValues.put(MoviesTable.COLUMN_TITLE, m.title);

        testValues.put(MoviesTable.COLUMN_DOWNLOADED, m.downloaded ? 1 : 0);
        testValues.put(MoviesTable.COLUMN_RUNTIME, m.runtime);
        testValues.put(MoviesTable.COLUMN_OVERVIEW, m.overview);


        return testValues;
    }

    static ContentValues createGenreContent(Genre m) {

        ContentValues testValues = new ContentValues();
        testValues.put(GenresTable.COLUMN_ID, m.getId());
        testValues.put(GenresTable.COLUMN_LANG, Locale.getDefault().getLanguage());
        testValues.put(GenresTable.COLUMN_TITLE, m.getName());


        return testValues;
    }

    /*
    |--------------------------------------------------------------------------
    | Validate cursor
    |--------------------------------------------------------------------------
    */
    static void validateCursor(Cursor valueCursor, ContentValues expectedValues) {
        assertTrue("Empty cursor returned. ", valueCursor.moveToFirst());
        validateCurrentRecord(valueCursor, expectedValues);
        valueCursor.close();
    }

    static void validateCurrentRecord(Cursor valueCursor, ContentValues expectedValues) {
        Set<Map.Entry<String, Object>> valueSet = expectedValues.valueSet();
        for (Map.Entry<String, Object> entry : valueSet) {
            String columnName = entry.getKey();
            int idx = valueCursor.getColumnIndex(columnName);
            assertFalse("Column '" + columnName + "' not found.", idx == -1);

            int type = valueCursor.getType(idx);

            switch (type) {
                case Cursor.FIELD_TYPE_NULL:
                    assertNull(valueCursor.getString(idx));
                    break;
                case Cursor.FIELD_TYPE_FLOAT:
                    assertEquals(entry.getValue(), valueCursor.getDouble(idx));
                    break;
                default:
                    assertEquals(entry.getValue().toString(), valueCursor.getString(idx));
            }
        }
    }
}
