package isel.pdm.yamda.data.repository;

import java.io.IOException;
import java.util.List;

import isel.pdm.yamda.data.api.TMDbApiSync;
import isel.pdm.yamda.data.api.entity.MovieDTO;
import isel.pdm.yamda.data.api.entity.MovieListingDTO;
import isel.pdm.yamda.data.exception.FailedGettingDataException;
import isel.pdm.yamda.data.mapper.DTOModelEntitiesDataMapper;
import isel.pdm.yamda.data.repository.base.IMovieRepository;
import isel.pdm.yamda.model.Genre;
import isel.pdm.yamda.model.MovieDetails;
import isel.pdm.yamda.model.MovieListDetails;

/**
 * Movies Repository, fetch list of movies and movies details
 * This class  uses an api and a mapper to retrieve the data and convert to a model entity
 * synchronously
 */
public class TMDbMovieRepository implements IMovieRepository {

    protected final String TAG = "DEBUG_" + getClass().getSimpleName();

    private final TMDbApiSync api;
    private final DTOModelEntitiesDataMapper mapper;

    public TMDbMovieRepository(TMDbApiSync api,
                               DTOModelEntitiesDataMapper mapper) {
        this.api = api;
        this.mapper = mapper;
    }

    /**
     * Get movies list in theaters synchronously
     * And convert to a model entity
     *
     * @param page api page
     * @return Entity model
     * @throws FailedGettingDataException
     */
    @Override
    public List<MovieListDetails> getTheatersMovies(int page) throws FailedGettingDataException {
        try {
            MovieListingDTO data = this.api.getTheatersMovies(page);
            return this.mapper.transform(data);
        } catch (IOException e) {
            throw new FailedGettingDataException(e);
        }
    }

    /**
     * Get movies list that will be in theaters soon synchronously
     * And convert to a model entity
     *
     * @param page api page
     * @return Entity model
     * @throws FailedGettingDataException
     */
    @Override
    public List<MovieListDetails> getSoonMovies(int page) throws FailedGettingDataException {

        try {
            MovieListingDTO data = this.api.getSoonMovies(page);
            return this.mapper.transform(data);
        } catch (IOException e) {
            throw new FailedGettingDataException(e);
        }
    }

    /**
     * Get top movies list synchronously
     * And convert to a model entity
     *
     * @param page api page
     * @return Entity model
     * @throws FailedGettingDataException
     */
    @Override
    public List<MovieListDetails> getTopMovies(int page) throws FailedGettingDataException {
        try {
            MovieListingDTO data = this.api.getTopMovies(page);
            return this.mapper.transform(data);
        } catch (IOException e) {
            throw new FailedGettingDataException(e);
        }
    }

    /**
     * Get movies list for a search query synchronously
     * And convert to a model entity
     *
     * @param search search query
     * @param page   api page
     * @return Entity model
     * @throws FailedGettingDataException
     */
    @Override
    public List<MovieListDetails> getMovieSearch(String search, int page)
            throws FailedGettingDataException {
        try {
            MovieListingDTO data = this.api.getMoviesSearch(search, page);
            return this.mapper.transform(data);
        } catch (IOException e) {
            throw new FailedGettingDataException(e);
        }
    }

    /**
     * Get movie by id synchronously
     * And convert to a model entity
     *
     * @return Entity model
     * @throws FailedGettingDataException
     */
    @Override
    public MovieDetails getMovieById(int id) throws FailedGettingDataException {
        try {
            MovieDTO data = this.api.getMovie(id);
            return this.mapper.transform(data);
        } catch (IOException e) {
            throw new FailedGettingDataException(e);
        }
    }

    /**
     * Get a list of genres synchronously
     * And convert to a model entity
     *
     * @return
     * @throws FailedGettingDataException
     */
    @Override
    public List<Genre> getGenres() throws FailedGettingDataException {
        try {
            return this.mapper.transform(this.api.getGenres());
        } catch (IOException e) {
            throw new FailedGettingDataException(e);
        }
    }
}
