package isel.pdm.yamda.data.repository.base;

import java.util.List;

import isel.pdm.yamda.model.Movie;

/**
 * Contract of the local repository, witch has more functions due to content provider
 */
public interface ILocalMovieRepository {

    /**
     * Get the movies in theaters right now from the local repo
     * @return a list of movies that are in theaters
     */
    List<Movie> getTheatersMovies();

    /**
     * Get movies in the soon list from the local repo
     * @return a list of movies that are coming to theaters
     */
    List<Movie> getSoonMovies();

    /**
     * Save the movies in a local repository, like a content provider
     * @param movies movies to be inserted
     * @param type  list type of the movies
     * @return total of inserts
     */
    int insertMovies(List<Movie> movies, String type);

    /**
     * deletes all movies with {@code type}
     *
     * @param type list type of the movies to be deleted
     * @return  number of deleted rows
     */
    int deleteMovies(String type);

    /**
     * Checks if there are movies in the local repo
     * @return  if there are movies in local repo
     */
    boolean hasMovies();

    /**
     * Get acknowledge of user following or not the movie
     *
     * @param movieId   id of the movie
     * @return true if being followed
     */
    boolean isBeingFollowed(int movieId);

    /**
     * Follow a movie
     *
     * @param movieId id of the movie
     */
    void followMovie(int movieId);

    /**
     * Unfollow a movie
     * @param movieId   id of the movie
     */
    void unfollowMovie(int movieId);
}
