package isel.pdm.yamda.data.repository.datasource;

import isel.pdm.yamda.data.entity.tmdb.ConfigurationDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieListingDTO;
import retrofit.Call;

public class DiskMovieDataStore implements IMovieDataStore {


    @Override
    public Call<MovieListingDTO> theaterMovieListEntity(int page) {
        throw new UnsupportedOperationException("Operation is not available yet!!!");
    }

    @Override
    public Call<MovieListingDTO> soonMovieListEntity(int page) {
        throw new UnsupportedOperationException("Operation is not available yet!!!");
    }

    @Override
    public Call<MovieListingDTO> topMovieListEntity(int page) {
        throw new UnsupportedOperationException("Operation is not available yet!!!");
    }

    @Override
    public Call<MovieDTO> movieEntityDetails(int id) {
        throw new UnsupportedOperationException("Operation is not available yet!!!");
    }

    @Override
    public Call<ConfigurationDTO> apiConfiguration() {
        throw new UnsupportedOperationException("Operation is not available yet!!!");
    }
}
