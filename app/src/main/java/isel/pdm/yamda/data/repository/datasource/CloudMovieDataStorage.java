package isel.pdm.yamda.data.repository.datasource;


import java.io.IOException;

import isel.pdm.yamda.data.api.TMDbApiSync;
import isel.pdm.yamda.data.api.entity.MovieDTO;
import isel.pdm.yamda.data.api.entity.MovieListingDTO;

/**
 * This class should use an api to fetch the data
 */
public class CloudMovieDataStorage {

    private final TMDbApiSync api;

    /**
     * Constructs a new CloudMovieDataStorage with the specified api
     * @param api
     */
    public CloudMovieDataStorage(TMDbApiSync api) {
        this.api = api;
    }

    public MovieListingDTO getTheatersMovies(int page) throws IOException {
        return this.api.getTheatersMovies(page);
    }

    public MovieListingDTO getSoonMovies(int page) throws IOException {
        return this.api.getSoonMovies(page);
    }

    public MovieListingDTO getTopMovies(int page) throws IOException {
        return this.api.getTopMovies(page);
    }

    public MovieDTO getMovie(int id) throws IOException {
        return this.api.getMovie(id);
    }

    public MovieListingDTO getMoviesSearch(String search, int page) throws IOException {
        return this.api.getMoviesSearch(search, page);
    }
}
