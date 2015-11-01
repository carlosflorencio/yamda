package isel.pdm.yamda.data.repository.datasource;

import isel.pdm.yamda.data.entity.tmdb.ConfigurationDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieListingDTO;

public class DiskMovieDataStore implements IMovieDataStore {


    @Override
    public MovieListingDTO theaterMovieListEntity(int page) {
        throw new UnsupportedOperationException("Operation is not available yet!!!");
    }

    @Override
    public MovieListingDTO soonMovieListEntity(int page) {
        throw new UnsupportedOperationException("Operation is not available yet!!!");
    }

    @Override
    public MovieListingDTO topMovieListEntity(int page) {
        throw new UnsupportedOperationException("Operation is not available yet!!!");
    }

    @Override
    public MovieDTO movieEntityDetails(int id) {
        throw new UnsupportedOperationException("Operation is not available yet!!!");
    }

    @Override
    public ConfigurationDTO apiConfiguration() {
        throw new UnsupportedOperationException("Operation is not available yet!!!");
    }
}
