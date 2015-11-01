package isel.pdm.yamda.data.repository;

import java.io.IOException;
import java.util.List;

import isel.pdm.yamda.model.mapper.MovieEntityDataMapper;
import isel.pdm.yamda.data.repository.datasource.IMovieDataStore;
import isel.pdm.yamda.data.repository.datasource.MovieDataStoreFactory;
import isel.pdm.yamda.model.entity.Movie;
import isel.pdm.yamda.model.repository.IMovieRepository;

/**
 * This class uses a factory and a mapper to retrieve the data to the model layer
 */
public class MovieDataRepository implements IMovieRepository {

    private final MovieDataStoreFactory factory;
    private final MovieEntityDataMapper mapper;

    public MovieDataRepository(MovieDataStoreFactory factory, MovieEntityDataMapper mapper) {
        this.factory = factory;
        this.mapper = mapper;
    }

    @Override
    public List<Movie> getTheatersMovies(int page) throws IOException {
        // Always get the online data ?
        final IMovieDataStore storage = this.factory.createCloudDataStore();

        return mapper.transform(storage.theaterMovieListEntity(page));
    }

    @Override
    public List<Movie> getSoonMovies(int page) throws IOException {
        // Always get the online data ?
        final IMovieDataStore storage = this.factory.createCloudDataStore();

        return mapper.transform(storage.soonMovieListEntity(page));
    }

    @Override
    public List<Movie> getTopMovies(int page) throws IOException {
        // Always get the online data ?
        final IMovieDataStore storage = this.factory.createCloudDataStore();

        return mapper.transform(storage.topMovieListEntity(page));
    }

    @Override
    public Movie getMovie(int id) {
        // get cached or online
        final IMovieDataStore storage = this.factory.create(id);

        return mapper.transform(storage.movieEntityDetails(id));
    }
}
