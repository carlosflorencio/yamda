package isel.pdm.yamda.data.repository.datasource;


import java.io.IOException;

import isel.pdm.yamda.data.entity.tmdb.ConfigurationDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieListingDTO;
import isel.pdm.yamda.data.providers.IMovieApi;

/**
 * This class should use an api to fetch the data
 */
public class CloudMovieDataStorage implements IMovieApi {

    private final IMovieApi api;

    public CloudMovieDataStorage(IMovieApi api) {
        this.api = api;
    }

    @Override
    public ConfigurationDTO getApiConfiguration() throws IOException {
        return this.api.getApiConfiguration();
    }

    @Override
    public MovieListingDTO getTheatersMovies(int page) throws IOException {
        return this.api.getTheatersMovies(page);
    }

    @Override
    public MovieListingDTO getSoonMovies(int page) throws IOException {
        return this.api.getSoonMovies(page);
    }

    @Override
    public MovieListingDTO getTopMovies(int page) throws IOException {
        return this.api.getTopMovies(page);
    }

    @Override
    public MovieDTO getMovie(int id) throws IOException {
        return this.api.getMovie(id);
    }

    @Override
    public MovieListingDTO getMoviesSearch(String search, int page) throws IOException {
        return this.api.getMoviesSearch(search, page);
    }
}
