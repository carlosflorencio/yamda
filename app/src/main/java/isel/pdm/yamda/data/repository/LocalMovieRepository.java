package isel.pdm.yamda.data.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.List;
import java.util.Locale;

import isel.pdm.yamda.data.exception.FailedGettingDataException;
import isel.pdm.yamda.data.mapper.CursorModelEntitiesDataMapper;
import isel.pdm.yamda.data.provider.MoviesContract;
import isel.pdm.yamda.data.provider.table.GenresTable;
import isel.pdm.yamda.data.provider.table.MoviesTable;
import isel.pdm.yamda.data.repository.base.ILocalMovieRepository;
import isel.pdm.yamda.data.repository.base.IMovieRepository;
import isel.pdm.yamda.model.Genre;
import isel.pdm.yamda.model.MovieDetails;
import isel.pdm.yamda.model.MovieListDetails;

/**
 * This repository fetches data from a local content provider, should be used on a
 * worker thread because disk operations are slow
 */
public class LocalMovieRepository implements IMovieRepository, ILocalMovieRepository {

    private Context ctx;
    private CursorModelEntitiesDataMapper mapper;

    // FIXME: 14/01/2016 CLOSE ALL CURSORS!
    public LocalMovieRepository(Context ctx, CursorModelEntitiesDataMapper mapper) {
        this.ctx = ctx;
        this.mapper = mapper;
    }

    /**
     * Get the movies with the type = NOW from the content provider
     *
     * @param page api page
     * @return
     * @throws FailedGettingDataException
     */
    @Override
    public List<MovieListDetails> getTheatersMovies(int page) throws FailedGettingDataException {
        final Uri uri = MoviesContract.MovieEntry.CONTENT_URI;
        Cursor c = ctx.getContentResolver().query(uri,
                                                  null,
                                                  MoviesTable.COLUMN_LIST_TYPE + " = ?",
                                                  new String[]{MoviesTable.TYPE_NOW},
                                                  null);

        return mapper.transformToMoviesList(c);
    }

    /**
     * Get the movies with the type = SOON from the content provider
     *
     * @param page api page
     * @return
     * @throws FailedGettingDataException
     */
    @Override
    public List<MovieListDetails> getSoonMovies(int page) throws FailedGettingDataException {
        final Uri uri = MoviesContract.MovieEntry.CONTENT_URI;
        Cursor c = ctx.getContentResolver().query(uri,
                                                  null,
                                                  MoviesTable.COLUMN_LIST_TYPE + " = ?",
                                                  new String[]{MoviesTable.TYPE_SOON},
                                                  null);

        return mapper.transformToMoviesList(c);
    }

    /**
     * This operation is not supported locally, another repository must be used
     *
     * @param page api page
     * @return
     * @throws FailedGettingDataException
     */
    @Override
    public List<MovieListDetails> getTopMovies(int page) throws FailedGettingDataException {
        throw new UnsupportedOperationException();
    }

    /**
     * This operation is not supported locally, another repository must be used
     *
     * @param search search query
     * @param page   api page
     * @return
     * @throws FailedGettingDataException
     */
    @Override
    public List<MovieListDetails> getMovieSearch(String search, int page)
            throws FailedGettingDataException {
        throw new UnsupportedOperationException();
    }

    /**
     * Selects a single movie row by from content provider
     *
     * @param id
     * @return
     * @throws FailedGettingDataException
     */
    @Override
    public MovieDetails getMovieById(int id) throws FailedGettingDataException {
        final Uri uri = MoviesContract.MovieEntry.buildMovieUri(id);
        Cursor c = ctx.getContentResolver().query(uri,
                                                  null,
                                                  null,
                                                  null,
                                                  null);

        return mapper.transformToMovieDetails(c);
    }

    /**
     * Selects the genres from the content provider
     * @return
     * @throws FailedGettingDataException
     */
    @Override
    public List<Genre> getGenres() throws FailedGettingDataException {
        return null;
    }

    /**
     * Insert movies in the content provider
     *
     * @param movies
     * @param type
     */
    @Override
    public int insertMovies(List<MovieListDetails> movies, String type) {
        final Uri uri = MoviesContract.MovieEntry.CONTENT_URI;

        ContentValues[] values = new ContentValues[movies.size()];

        for (int i = 0; i < movies.size(); i++) {
            ContentValues v = new ContentValues();
            v.put(MoviesTable.COLUMN_ID, movies.get(i).getId());
            v.put(MoviesTable.COLUMN_ORIGINAL_TITLE, movies.get(i).getOriginalTitle());
            v.put(MoviesTable.COLUMN_BACKDROP, movies.get(i).getBackdrop());
            v.put(MoviesTable.COLUMN_RELEASE_DATE, movies.get(i).getReleaseDate());
            v.put(MoviesTable.COLUMN_RATING, movies.get(i).getRating());
            v.put(MoviesTable.COLUMN_POSTER, movies.get(i).getPoster());
            v.put(MoviesTable.COLUMN_LIST_TYPE, type);
            v.put(MoviesTable.COLUMN_POPULARITY, movies.get(i).getPopularity());
            v.put(MoviesTable.COLUMN_LANG, Locale.getDefault().getLanguage());
            v.put(MoviesTable.COLUMN_TITLE, movies.get(i).getTitle());

            values[i] = v;
        }

        return ctx.getContentResolver().bulkInsert(uri, values);
    }

    /**
     * Associate in the content provider, the movie and genres
     * @param movie
     * @param genres
     * @return
     */
    @Override
    public int associateGenres(MovieListDetails movie, List<Genre> genres) {
        final Uri uri = MoviesContract.GenreEntry.CONTENT_URI_PIVOT;
        ContentValues[] values = new ContentValues[genres.size()];

        for (int i = 0; i < genres.size(); i++) {
            ContentValues v = new ContentValues();

            v.put(GenresTable.PIVOT_COLUMN_ID, genres.get(i).getId());
            v.put(GenresTable.PIVOT_COLUMN_LANG, Locale.getDefault().getLanguage());
            v.put(GenresTable.PIVOT_COLUMN_MOVIE_ID, movie.getId());

            values[i] = v;
        }

        return ctx.getContentResolver().bulkInsert(uri, values);
    }

    /**
     * Update an existing content provider movie with his details
     *
     * @param movie
     * @return
     */
    @Override
    public boolean updateMovieDetails(MovieDetails movie) {
        final Uri uri = MoviesContract.MovieEntry.buildMovieUri(movie.getId());

        ContentValues v = new ContentValues();

        v.put(MoviesTable.COLUMN_DOWNLOADED, 1);
        v.put(MoviesTable.COLUMN_RUNTIME, movie.getRuntime());
        v.put(MoviesTable.COLUMN_OVERVIEW, movie.getOverview());

        return ctx.getContentResolver().update(uri, v, null, null) == 1;
    }

    /**
     * Checks if the local provider has movies rows
     * @return
     */
    @Override
    public boolean hasMovies() {
        final Uri uri = MoviesContract.MovieEntry.CONTENT_URI;

        Cursor c = ctx.getContentResolver()
                      .query(uri, new String[]{"count(*) AS count"}, null, null, null);
        if (c.moveToFirst()) {
            return c.getInt(0) > 0;
        }

        return false;
    }


    /**
     * Checks if the movie is being followed in the content provider
     *
     * @param movieId
     * @return
     */
    @Override
    public boolean isBeingFollowed(int movieId) {
        throw new UnsupportedOperationException();
    }


    /**
     * Set to follow movie in the content provider
     *
     * @param movieId
     */
    @Override
    public void followMovie(int movieId) {
        throw new UnsupportedOperationException();
    }

    /**
     * Unfollow movie in the content provider
     *
     * @param movieId
     */
    @Override
    public void unfollowMovie(int movieId) {
        throw new UnsupportedOperationException();
    }
}
