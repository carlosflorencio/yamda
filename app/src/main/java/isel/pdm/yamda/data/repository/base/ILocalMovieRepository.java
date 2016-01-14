package isel.pdm.yamda.data.repository.base;

import java.util.List;

import isel.pdm.yamda.model.Genre;
import isel.pdm.yamda.model.MovieDetails;
import isel.pdm.yamda.model.MovieListDetails;

/**
 * Contract of the local repository, witch has more functions due to content provider
 */
public interface ILocalMovieRepository extends IMovieRepository {

    /**
     * Save the movies in a local repository, like a content provider
     * @param movies
     * @param type
     * @return total of inserts
     */
    int insertMovies(List<MovieListDetails> movies, String type);

    /**
     * Associate in the local repository, the movie and genres
     * @param movie
     * @param genres
     * @return
     */
    int associateGenres(MovieListDetails movie, List<Genre> genres);

    /**
     * Update the local repository with a new details
     * @param movie
     * @return
     */
    boolean updateMovieDetails(MovieDetails movie);

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
