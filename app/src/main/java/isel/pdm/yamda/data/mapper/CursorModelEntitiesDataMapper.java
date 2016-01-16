package isel.pdm.yamda.data.mapper;

import android.database.Cursor;

import java.util.LinkedList;
import java.util.List;

import isel.pdm.yamda.data.provider.MoviesContract;
import isel.pdm.yamda.model.Movie;

/**
 * This mapper knows how to transform Data Cursors from the the content provider to a
 * model entity
 */
public class CursorModelEntitiesDataMapper {

    /**
     * Converts a cursor of a movies select, to a list of model entities
     *
     * @param cursor
     * @return
     */
    public List<Movie> transformToMoviesList(Cursor cursor) {
        List<Movie> list = new LinkedList<>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_ID));
            String title = cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_TITLE));
            String original_title = cursor
                    .getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_ORIGINAL_TITLE));
            String release_date = cursor
                    .getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_RELEASE_DATE));
            String poster = cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_POSTER));
            double rating = cursor.getDouble(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_RATING));
            double popularity = cursor
                    .getDouble(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_POPULARITY));

            list.add(new Movie(id, title, original_title, release_date, poster,
                               rating,
                               popularity));
        }

        return list;
    }

}
