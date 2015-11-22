package isel.pdm.yamda.data.repository.datasource;

import isel.pdm.yamda.data.entity.tmdb.ConfigurationDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieListingDTO;
import isel.pdm.yamda.data.providers.IMovieApi;

public class DiskMovieDataStore implements IMovieApi {

    @Override
    public ConfigurationDTO getApiConfiguration() {
         throw new UnsupportedOperationException("Operation is not available yet!!!");
    }

    @Override
    public MovieListingDTO getTheatersMovies(int page) {
         throw new UnsupportedOperationException("Operation is not available yet!!!");
    }

    @Override
    public MovieListingDTO getSoonMovies(int page) {
         throw new UnsupportedOperationException("Operation is not available yet!!!");
    }

    @Override
    public MovieListingDTO getTopMovies(int page) {
         throw new UnsupportedOperationException("Operation is not available yet!!!");
    }

    @Override
    public MovieDTO getMovie(int id) {
         throw new UnsupportedOperationException("Operation is not available yet!!!");
    }

    @Override
    public MovieListingDTO getMoviesSearch(String search, int page) {
         throw new UnsupportedOperationException("Operation is not available yet!!!");
    }
}
