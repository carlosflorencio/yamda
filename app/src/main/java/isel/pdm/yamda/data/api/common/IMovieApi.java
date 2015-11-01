package isel.pdm.yamda.data.api.common;


import isel.pdm.yamda.data.entity.tmdb.ConfigurationDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieListingDTO;
import retrofit.Call;

/**
 * The contract that all apis must obey
 */
public interface IMovieApi {

    /**
     * Get the api configuration if present
     * @return
     */
    Call<ConfigurationDTO> getApiConfiguration();

    /**
     * Get the movies in the theaters right now
     * @param page
     * @return
     */
    Call<MovieListingDTO> getTheatersMovies(int page);

    /**
     * Get next movies getting into theaters
     * @param page
     * @return
     */
    Call<MovieListingDTO> getSoonMovies(int page);

    /**
     * Get the top movies in theaters
     * @param page
     * @return
     */
    Call<MovieListingDTO> getTopMovies(int page);

    /**
     * Get a movie details
     * @param id
     * @return
     */
    Call<MovieDTO> getMovie(int id);

    /**
     * Get the top movies in theaters
     * @param search
     * @return
     */
    Call<MovieListingDTO> getMoviesSearch(String search, int page);
}
