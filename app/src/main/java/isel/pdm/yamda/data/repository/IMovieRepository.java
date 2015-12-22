package isel.pdm.yamda.data.repository;

import java.util.List;

import isel.pdm.yamda.data.exception.ApiFailedGettingDataException;
import isel.pdm.yamda.model.entity.MovieDetails;
import isel.pdm.yamda.model.entity.MovieListDetails;

/**
 * This interface defines the contract with the data layer
 * and the presentation layer for requesting data synchronously
 */
public interface IMovieRepository {

    /**
     * Get movies list in theaters synchronously
     * And convert to a model entity
     *
     * @param page api page
     * @return Entity model
     * @throws ApiFailedGettingDataException
     */
    List<MovieListDetails> getTheatersMovies(int page) throws ApiFailedGettingDataException;

    /**
     * Get movies list that will be in theaters soon synchronously
     * And convert to a model entity
     *
     * @param page api page
     * @return Entity model
     * @throws ApiFailedGettingDataException
     */
    List<MovieListDetails> getSoonMovies(int page) throws ApiFailedGettingDataException;

    /**
     * Get top movies list synchronously
     * And convert to a model entity
     *
     * @param page api page
     * @return Entity model
     * @throws ApiFailedGettingDataException
     */
    List<MovieListDetails> getTopMovies(int page) throws ApiFailedGettingDataException;

    /**
     * Get movies list for a search query synchronously
     * And convert to a model entity
     *
     * @param search search query
     * @param page   api page
     * @return Entity model
     * @throws ApiFailedGettingDataException
     */
    List<MovieListDetails> getMovieSearch(String search, int page)
            throws ApiFailedGettingDataException;

    /**
     * Get movie by id synchronously
     * And convert to a model entity
     *
     * @param id
     * @return Entity model
     * @throws ApiFailedGettingDataException
     */
    MovieDetails getMovieById(int id) throws ApiFailedGettingDataException;

    /**
     * Get acknowledge of user following or not the movie
     *
     * @param movieId
     * @return Boolean value
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
