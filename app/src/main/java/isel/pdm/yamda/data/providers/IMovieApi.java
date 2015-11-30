package isel.pdm.yamda.data.providers;


import java.io.IOException;

import isel.pdm.yamda.data.entity.tmdb.ConfigurationDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieListingDTO;

/**
 * The contract that all apis providers must obey
 * *Warning* Dependency: Retrofit!
 */
public interface IMovieApi {

    /**
     * Get the api configuration if present
     * @return
     * @throws IOException
     */
    ConfigurationDTO getApiConfiguration() throws IOException;

    /**
     * Get the movies in the theaters right now
     * @param page
     * @return
     * @throws IOException
     */
    MovieListingDTO getTheatersMovies(int page) throws IOException;

    /**
     * Get next movies getting into theaters
     * @param page
     * @return
     * @throws IOException
     */
    MovieListingDTO getSoonMovies(int page) throws IOException;

    /**
     * Get the top movies in theaters
     * @param page
     * @return
     * @throws IOException
     */
    MovieListingDTO getTopMovies(int page) throws IOException;

    /**
     * Get a movie details
     * @param id
     * @return
     * @throws IOException
     */
    MovieDTO getMovie(int id) throws IOException;

    /**
     * Get the search result from the api given a search query and page
     * @param search
     * @param page
     * @return
     * @throws IOException
     */
    MovieListingDTO getMoviesSearch(String search, int page) throws IOException;
}
