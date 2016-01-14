package isel.pdm.yamda.data.repository.base;

import java.util.List;

import isel.pdm.yamda.data.exception.FailedGettingDataException;
import isel.pdm.yamda.model.Genre;
import isel.pdm.yamda.model.MovieDetails;
import isel.pdm.yamda.model.MovieListDetails;

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
     * @throws FailedGettingDataException
     */
    List<MovieListDetails> getTheatersMovies(int page) throws FailedGettingDataException;

    /**
     * Get movies list that will be in theaters soon synchronously
     * And convert to a model entity
     *
     * @param page api page
     * @return Entity model
     * @throws FailedGettingDataException
     */
    List<MovieListDetails> getSoonMovies(int page) throws FailedGettingDataException;

    /**
     * Get top movies list synchronously
     * And convert to a model entity
     *
     * @param page api page
     * @return Entity model
     * @throws FailedGettingDataException
     */
    List<MovieListDetails> getTopMovies(int page) throws FailedGettingDataException;

    /**
     * Get movies list for a search query synchronously
     * And convert to a model entity
     *
     * @param search search query
     * @param page   api page
     * @return Entity model
     * @throws FailedGettingDataException
     */
    List<MovieListDetails> getMovieSearch(String search, int page)
            throws FailedGettingDataException;

    /**
     * Get movie by id synchronously
     * And convert to a model entity
     *
     * @param id
     * @return Entity model
     * @throws FailedGettingDataException
     */
    MovieDetails getMovieById(int id) throws FailedGettingDataException;

    /**
     * Get a list of genres synchronously
     * And convert to a model entity
     *
     * @return
     * @throws FailedGettingDataException
     */
    List<Genre> getGenres() throws FailedGettingDataException;
}
