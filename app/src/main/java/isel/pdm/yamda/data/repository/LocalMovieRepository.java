package isel.pdm.yamda.data.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.List;
import java.util.Locale;

import isel.pdm.yamda.data.mapper.CursorModelEntitiesDataMapper;
import isel.pdm.yamda.data.provider.MoviesContract;
import isel.pdm.yamda.data.repository.base.ILocalMovieRepository;
import isel.pdm.yamda.model.Movie;

/**
 * This repository fetches data from a local content provider, should be used on a
 * worker thread because disk operations are slow
 */
public class LocalMovieRepository implements ILocalMovieRepository {

    private Context ctx;
    private CursorModelEntitiesDataMapper mapper;

    public LocalMovieRepository(Context ctx, CursorModelEntitiesDataMapper mapper) {
        this.ctx = ctx;
        this.mapper = mapper;
    }

    /**
     * Get the movies with the type = NOW from the content provider
     * @return
     */
    @Override
    public List<Movie> getTheatersMovies() {
        final Uri uri = MoviesContract.MovieEntry.CONTENT_URI;
        Cursor c = ctx.getContentResolver().query(uri,
                                                  null,
                                                  MoviesContract.MovieEntry.COLUMN_LIST_TYPE + " = ?",
                                                  new String[]{MoviesContract.MovieEntry.TYPE_NOW},
                                                  null);
        List<Movie> res = mapper.transformToMoviesList(c);
        c.close();

        return res;
    }

    /**
     * Get the movies with the type = SOON from the content provider
     *
     * @return
     */
    @Override
    public List<Movie> getSoonMovies() {
        final Uri uri = MoviesContract.MovieEntry.CONTENT_URI;
        Cursor c = ctx.getContentResolver().query(uri,
                                                  null,
                                                  MoviesContract.MovieEntry.COLUMN_LIST_TYPE + " = ?",
                                                  new String[]{MoviesContract.MovieEntry.TYPE_SOON},
                                                  null);

        List<Movie> res = mapper.transformToMoviesList(c);
        c.close();

        return res;
    }

    /**
     * Insert movies in the content provider
     *
     * @param movies    list of movies
     * @param type      list in which movies are
     */
    @Override
    public int insertMovies(List<Movie> movies, String type) {
        final Uri uri = MoviesContract.MovieEntry.CONTENT_URI;

        ContentValues[] values = new ContentValues[movies.size()];

        for (int i = 0; i < movies.size(); i++) {
            ContentValues v = new ContentValues();
            v.put(MoviesContract.MovieEntry.COLUMN_ID, movies.get(i).getId());
            v.put(MoviesContract.MovieEntry.COLUMN_ORIGINAL_TITLE, movies.get(i).getOriginalTitle());
            v.put(MoviesContract.MovieEntry.COLUMN_RELEASE_DATE, movies.get(i).getReleaseDate());
            v.put(MoviesContract.MovieEntry.COLUMN_RATING, movies.get(i).getRating());
            v.put(MoviesContract.MovieEntry.COLUMN_POSTER, movies.get(i).getPoster());
            v.put(MoviesContract.MovieEntry.COLUMN_LIST_TYPE, type);
            v.put(MoviesContract.MovieEntry.COLUMN_POPULARITY, movies.get(i).getPopularity());
            v.put(MoviesContract.MovieEntry.COLUMN_LANG, Locale.getDefault().getLanguage());
            v.put(MoviesContract.MovieEntry.COLUMN_TITLE, movies.get(i).getTitle());

            values[i] = v;
        }

        return ctx.getContentResolver().bulkInsert(uri, values);
    }

    @Override
    public int deleteMovies(String type) {
        final Uri uri = MoviesContract.MovieEntry.CONTENT_URI;

        return ctx.getContentResolver().delete(uri,
                MoviesContract.MovieEntry.COLUMN_LIST_TYPE + " = ?",
                new String[]{type});
    }

    /**
     * Checks if the local provider has movies rows
     * @return
     */
    @Override
    public boolean hasMovies() {
        final Uri uri = MoviesContract.MovieEntry.CONTENT_URI;
        boolean res = false;
        Cursor c = ctx.getContentResolver()
                      .query(uri, new String[]{"count(*) AS count"}, null, null, null);
        if (c.moveToFirst()) {
            res =  c.getInt(0) > 0;
        }

        c.close();

        return res;
    }


    /**
     * Checks if the movie is being followed in the content provider
     *
     * @param movieId
     * @return
     */
    @Override
    public boolean isBeingFollowed(int movieId) {
        final Uri uri = MoviesContract.FollowEntry.buildFollowUri(movieId);

        Cursor c = ctx.getContentResolver().query(uri, null, null, null, null);
        int rows = c.getCount();

        c.close();
        return rows > 0;
    }


    /**
     * Set to follow movie in the content provider
     *
     * @param movieId
     */
    @Override
    public void followMovie(int movieId) {
        final Uri uri = MoviesContract.FollowEntry.CONTENT_URI;

        ContentValues values = new ContentValues();
        values.put(MoviesContract.FollowEntry.COLUMN_MOVIE_ID, movieId);
        values.put(MoviesContract.FollowEntry.COLUMN_MOVIE_LIST, MoviesContract.MovieEntry.TYPE_SOON);

        ctx.getContentResolver().insert(uri, values);
    }

    /**
     * Unfollow movie in the content provider
     *
     * @param movieId
     */
    @Override
    public void unfollowMovie(int movieId) {
        final Uri uri = MoviesContract.FollowEntry.buildFollowUri(movieId);

        ctx.getContentResolver().delete(uri, null, null);
    }
}
