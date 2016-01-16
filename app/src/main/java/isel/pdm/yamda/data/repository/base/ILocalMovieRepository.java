package isel.pdm.yamda.data.repository.base;

import java.util.List;

import isel.pdm.yamda.model.Movie;

/**
 * Contract of the local repository, witch has more functions due to content provider
 */
public interface ILocalMovieRepository {

    /**
     * Get the movies in theaters right now from the local repo
     * @return
     */
    List<Movie> getTheatersMovies();

    /**
     * Get movies in the soon list from the local repo
     * @return
     */
    List<Movie> getSoonMovies();

    /**
     * Save the movies in a local repository, like a content provider
     * @param movies
     * @param type
     * @return total of inserts
     */
    int insertMovies(List<Movie> movies, String type);

    /**
     * Checks if there are movies in the local repo
     * @return
     */
    boolean hasMovies();

    /**
     * Get acknowledge of user following or not the movie
     *
     * @param movieId
     * @return true if being followed
     */
    boolean isBeingFollowed(int movieId);

    /**
     * Follow a movie
     *
     * @param movieId
     */
    void followMovie(int movieId);

    /**
     * Unfollow a movie
     * @param movieId
     */
    void unfollowMovie(int movieId);
}
