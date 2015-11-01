package isel.pdm.yamda.data.repository.datasource;


import isel.pdm.yamda.data.api.common.IMovieApi;
import isel.pdm.yamda.data.entity.tmdb.ConfigurationDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieListingDTO;
import retrofit.Call;

/**
 * This class should use an api to fetch the data
 */
public class CloudMovieDataStorage implements IMovieDataStore {

    private final IMovieApi api;

    public CloudMovieDataStorage(IMovieApi api) {
        this.api = api;
    }

    @Override
    public Call<ConfigurationDTO> apiConfiguration() {
        return this.api.getApiConfiguration();
    }

    @Override
    public Call<MovieListingDTO> theaterMovieListEntity(int page) {
        return this.api.getTheatersMovies(page);
    }

    @Override
    public Call<MovieListingDTO> soonMovieListEntity(int page) {
        return this.api.getSoonMovies(page);
    }

    @Override
    public Call<MovieListingDTO> topMovieListEntity(int page) {
        return this.api.getTopMovies(page);
    }

    @Override
    public Call<MovieDTO> movieEntityDetails(int id) {
        return this.api.getMovie(id);
    }
}
