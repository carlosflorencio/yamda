package isel.pdm.yamda.data.repository.datasource;


import isel.pdm.yamda.data.api.common.IMovieApi;
import isel.pdm.yamda.data.entity.tmdb.ConfigurationDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieListingDTO;
import retrofit.Call;

/**
 * This class should use an api to fetch the data
 */
public class CloudMovieDataStorage implements IMovieApi {

    private final IMovieApi api;

    public CloudMovieDataStorage(IMovieApi api) {
        this.api = api;
    }

    @Override
    public Call<ConfigurationDTO> getApiConfiguration() {
        return this.api.getApiConfiguration();
    }

    @Override
    public Call<MovieListingDTO> getTheatersMovies(int page) {
        return this.api.getTheatersMovies(page);
    }

    @Override
    public Call<MovieListingDTO> getSoonMovies(int page) {
        return this.api.getSoonMovies(page);
    }

    @Override
    public Call<MovieListingDTO> getTopMovies(int page) {
        return this.api.getTopMovies(page);
    }

    @Override
    public Call<MovieDTO> getMovie(int id) {
        return this.api.getMovie(id);
    }

    @Override
    public Call<MovieListingDTO> getMoviesSearch(String search, int page) {
        return this.api.getMoviesSearch(search, page);
    }
}
