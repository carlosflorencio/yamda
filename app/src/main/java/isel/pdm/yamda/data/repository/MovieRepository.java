package isel.pdm.yamda.data.repository;

import java.io.IOException;
import java.util.List;

import isel.pdm.yamda.data.entity.tmdb.MovieDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieListingDTO;
import isel.pdm.yamda.data.exception.ApiFailedGettingDataException;
import isel.pdm.yamda.data.repository.datasource.DiskMovieDataStore;
import isel.pdm.yamda.data.repository.datasource.MovieDataStoreFactory;
import isel.pdm.yamda.model.entity.MovieDetails;
import isel.pdm.yamda.model.entity.MovieListDetails;
import isel.pdm.yamda.model.mapper.ModelEntitiesDataMapper;

/**
 * Movies Repository, fetch list of movies and movies details
 * This class  uses a factory and a mapper to retrieve the data and convert to a model entity
 * synchronously
 */
public class MovieRepository implements IMovieRepository {

    protected final String TAG = "DEBUG_" + getClass().getSimpleName();

    private final MovieDataStoreFactory factory;
    private final ModelEntitiesDataMapper mapper;

    private final DiskMovieDataStore cache;

    public MovieRepository(MovieDataStoreFactory factory,
                           ModelEntitiesDataMapper mapper) {
        this.factory = factory;
        this.mapper = mapper;
        this.cache = DiskMovieDataStore.create();
    }

    /**
     * Get movies list in theaters synchronously
     * And convert to a model entity
     *
     * @param page api page
     * @return Entity model
     * @throws ApiFailedGettingDataException
     */
    @Override
    public List<MovieListDetails> getTheatersMovies(int page) throws ApiFailedGettingDataException {
        try {
            MovieListingDTO data = cache.getTheatersMovies(page);
            if (data == null) {
                data = this.factory.createCloudDataStore().getTheatersMovies(page);
                cache.setTheatersMovies(data);
            }
            return this.mapper.transform(data);
        } catch (IOException e) {
            throw new ApiFailedGettingDataException(e);
        }
    }

    /**
     * Get movies list that will be in theaters soon synchronously
     * And convert to a model entity
     *
     * @param page api page
     * @return Entity model
     * @throws ApiFailedGettingDataException
     */
    @Override
    public List<MovieListDetails> getSoonMovies(int page) throws ApiFailedGettingDataException {
        try {
            MovieListingDTO data = cache.getSoonMovies(page);
            if (data == null) {
                data = this.factory.createCloudDataStore().getSoonMovies(page);
                cache.setSoonMovies(data);
            }
            return this.mapper.transform(data);
        } catch (IOException e) {
            throw new ApiFailedGettingDataException(e);
        }
    }

    /**
     * Get top movies list synchronously
     * And convert to a model entity
     *
     * @param page api page
     * @return Entity model
     * @throws ApiFailedGettingDataException
     */
    @Override
    public List<MovieListDetails> getTopMovies(int page) throws ApiFailedGettingDataException {
        try {
            MovieListingDTO data = this.factory.createCloudDataStore().getTopMovies(page);
            return this.mapper.transform(data);
        } catch (IOException e) {
            throw new ApiFailedGettingDataException(e);
        }
    }

    /**
     * Get movies list for a search query synchronously
     * And convert to a model entity
     *
     * @param search search query
     * @param page   api page
     * @return Entity model
     * @throws ApiFailedGettingDataException
     */
    @Override
    public List<MovieListDetails> getMovieSearch(String search, int page)
            throws ApiFailedGettingDataException {
        try {
            MovieListingDTO data = this.factory.createCloudDataStore().getMoviesSearch(search, page);
            return this.mapper.transform(data);
        } catch (IOException e) {
            throw new ApiFailedGettingDataException(e);
        }
    }

    /**
     * Get movie by id synchronously
     * And convert to a model entity
     *
     * @return Entity model
     * @throws ApiFailedGettingDataException
     */
    @Override
    public MovieDetails getMovieById(int id) throws ApiFailedGettingDataException {
        try {
            MovieDTO data = cache.getMovie(id);
            if (data == null) {
                data = this.factory.createCloudDataStore().getMovie(id);
                cache.setMovie(data);
            }
            return this.mapper.transform(data);
        } catch (IOException e) {
            throw new ApiFailedGettingDataException(e);
        }
    }

    @Override
    public Boolean getMovieIsBeingFollowed(int movieId) {
        return cache.isBeingFollowed(movieId);
    }

    @Override
    public void setMovieIsBeingFollowed(int movieId, boolean value) {
        cache.setIsBeingFollowed(movieId, value);
    }
}
