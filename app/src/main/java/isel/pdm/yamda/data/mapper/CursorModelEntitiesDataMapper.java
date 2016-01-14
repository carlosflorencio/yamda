package isel.pdm.yamda.data.mapper;

import android.database.Cursor;

import java.util.LinkedList;
import java.util.List;

import isel.pdm.yamda.data.provider.table.MoviesTable;
import isel.pdm.yamda.model.MovieDetails;
import isel.pdm.yamda.model.MovieListDetails;

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
    public List<MovieListDetails> transformToMoviesList(Cursor cursor) {
        List<MovieListDetails> list = new LinkedList<>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(MoviesTable.COLUMN_ID));
            String title = cursor.getString(cursor.getColumnIndex(MoviesTable.COLUMN_TITLE));
            String original_title = cursor
                    .getString(cursor.getColumnIndex(MoviesTable.COLUMN_ORIGINAL_TITLE));
            String release_date = cursor
                    .getString(cursor.getColumnIndex(MoviesTable.COLUMN_RELEASE_DATE));
            String poster = cursor.getString(cursor.getColumnIndex(MoviesTable.COLUMN_POSTER));
            String backdrop = cursor.getString(cursor.getColumnIndex(MoviesTable.COLUMN_BACKDROP));
            double rating = cursor.getDouble(cursor.getColumnIndex(MoviesTable.COLUMN_RATING));
            double popularity = cursor
                    .getDouble(cursor.getColumnIndex(MoviesTable.COLUMN_POPULARITY));

            list.add(new MovieListDetails(id, title, original_title, release_date, poster, backdrop,
                                          rating,
                                          popularity, null));
        }

        return list;
    }

    /**
     * Convert a cursor of a single movie row to a MovieDetails model entity
     * @param cursor
     * @return
     */
    public MovieDetails transformToMovieDetails(Cursor cursor) {
        if (!cursor.moveToFirst()) return null;

        int id = cursor.getInt(cursor.getColumnIndex(MoviesTable.COLUMN_ID));
        String title = cursor.getString(cursor.getColumnIndex(MoviesTable.COLUMN_TITLE));
        String original_title = cursor
                .getString(cursor.getColumnIndex(MoviesTable.COLUMN_ORIGINAL_TITLE));
        String release_date = cursor
                .getString(cursor.getColumnIndex(MoviesTable.COLUMN_RELEASE_DATE));
        String poster = cursor.getString(cursor.getColumnIndex(MoviesTable.COLUMN_POSTER));
        String backdrop = cursor.getString(cursor.getColumnIndex(MoviesTable.COLUMN_BACKDROP));
        double rating = cursor.getDouble(cursor.getColumnIndex(MoviesTable.COLUMN_RATING));
        double popularity = cursor
                .getDouble(cursor.getColumnIndex(MoviesTable.COLUMN_POPULARITY));

        int runtime = cursor.getInt(cursor.getColumnIndex(MoviesTable.COLUMN_RUNTIME));
        String overview = cursor.getString(cursor.getColumnIndex(MoviesTable.COLUMN_OVERVIEW));


        return new MovieDetails(id, title, original_title, release_date, poster, backdrop, rating,
                                popularity, runtime, overview, null, null, null);
    }

}
