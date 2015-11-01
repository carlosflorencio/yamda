package isel.pdm.yamda.data.api.common;


import isel.pdm.yamda.data.entity.tmdb.ConfigurationDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieListingDTO;
import isel.pdm.yamda.data.exception.ApiFailedGettingDataException;

/**
 * The contract that all apis must obey
 */
public interface IMovieApi {

    /**
     * Get the api configuration if present
     * @return
     */
    ConfigurationDTO getApiConfiguration() throws ApiFailedGettingDataException;

    /**
     * Get the movies in the theaters right now
     * @param page
     * @return
     */
    MovieListingDTO getTheatersMovies(int page) throws ApiFailedGettingDataException;

    /**
     * Get next movies getting into theaters
     * @param page
     * @return
     */
    MovieListingDTO getSoonMovies(int page) throws ApiFailedGettingDataException;

    /**
     * Get the top movies in theaters
     * @param page
     * @return
     */
    MovieListingDTO getTopMovies(int page) throws ApiFailedGettingDataException;

    /**
     * Get a movie details
     * @param id
     * @return
     */
    MovieDTO getMovie(int id) throws ApiFailedGettingDataException;
}
