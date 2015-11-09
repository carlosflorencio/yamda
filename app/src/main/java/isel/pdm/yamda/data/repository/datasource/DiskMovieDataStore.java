package isel.pdm.yamda.data.repository.datasource;

import isel.pdm.yamda.data.providers.IMovieApi;
import isel.pdm.yamda.data.entity.tmdb.ConfigurationDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieListingDTO;
import retrofit.Call;

public class DiskMovieDataStore implements IMovieApi {

    @Override
    public Call<ConfigurationDTO> getApiConfiguration() {
         throw new UnsupportedOperationException("Operation is not available yet!!!");
    }

    @Override
    public Call<MovieListingDTO> getTheatersMovies(int page) {
         throw new UnsupportedOperationException("Operation is not available yet!!!");
    }

    @Override
    public Call<MovieListingDTO> getSoonMovies(int page) {
         throw new UnsupportedOperationException("Operation is not available yet!!!");
    }

    @Override
    public Call<MovieListingDTO> getTopMovies(int page) {
         throw new UnsupportedOperationException("Operation is not available yet!!!");
    }

    @Override
    public Call<MovieDTO> getMovie(int id) {
         throw new UnsupportedOperationException("Operation is not available yet!!!");
    }

    @Override
    public Call<MovieListingDTO> getMoviesSearch(String search, int page) {
         throw new UnsupportedOperationException("Operation is not available yet!!!");
    }
}
