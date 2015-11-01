package isel.pdm.yamda.data.repository.datasource;


import isel.pdm.yamda.data.api.common.IMovieApi;
import isel.pdm.yamda.data.entity.tmdb.ConfigurationDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieListingDTO;
import isel.pdm.yamda.data.exception.ApiFailedGettingDataException;

/**
 * This class should use an api to fetch the data
 */
public class CloudMovieDataStorage implements IMovieDataStore {

    private final IMovieApi api;

    public CloudMovieDataStorage(IMovieApi api) {
        this.api = api;
    }

    @Override
    public ConfigurationDTO apiConfiguration() {
        try {
            return this.api.getApiConfiguration();
        } catch (ApiFailedGettingDataException e) {
            return null;
        }
    }

    @Override
    public MovieListingDTO theaterMovieListEntity(int page) {
        try {
            return this.api.getTheatersMovies(page);
        } catch (ApiFailedGettingDataException e) {
            return null;
        }
    }

    @Override
    public MovieListingDTO soonMovieListEntity(int page) {
        try {
            return this.api.getSoonMovies(page);
        } catch (ApiFailedGettingDataException e) {
            return null;
        }
    }

    @Override
    public MovieListingDTO topMovieListEntity(int page) {
        try {
            return this.api.getTopMovies(page);
        } catch (ApiFailedGettingDataException e) {
            return null;
        }
    }

    @Override
    public MovieDTO movieEntityDetails(int id) {
        try {
            return this.api.getMovie(id);
        } catch (ApiFailedGettingDataException e) {
            return null;
        }
    }
}
