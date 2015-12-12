package isel.pdm.yamda.data.repository.datasource;

import java.util.HashMap;
import java.util.Map;

import isel.pdm.yamda.data.entity.tmdb.ConfigurationDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieListingDTO;
import isel.pdm.yamda.data.providers.IMovieApi;

/**
 * This class should get all the movies information stored elsewhere in the memory
 */
public class DiskMovieDataStore implements IMovieApi {

    private MovieListingDTO theatersMovies;

    private MovieListingDTO soonMovies;

    private ConfigurationDTO configuration;

    private Map<Integer, MovieDTO> movie;

    private Map<Integer, Boolean> isBeingFollowed;

    /**
     * Constructs a new DiskMovieDataStore instance
     */
    private DiskMovieDataStore() {
        movie = new HashMap<>();
        isBeingFollowed = new HashMap<>();
    }

    @Override
    public ConfigurationDTO getApiConfiguration() {
        return this.configuration;
    }

    @Override
    public MovieListingDTO getTheatersMovies(int page) {
        return this.theatersMovies;
    }

    @Override
    public MovieListingDTO getSoonMovies(int page) {
        return this.soonMovies;
    }

    @Override
    public MovieListingDTO getTopMovies(int page) {
         throw new UnsupportedOperationException("Operation is not available yet!!!");
    }

    @Override
    public MovieDTO getMovie(int id) {
        if (this.movie.get(id) == null) {
            return null;
        }
        return this.movie.get(id);
    }

    @Override
    public MovieListingDTO getMoviesSearch(String search, int page) {
         throw new UnsupportedOperationException("Operation is not available yet!!!");
    }

    public void setTheatersMovies(MovieListingDTO theatersMovies) {
        this.theatersMovies = theatersMovies;
    }

    public void setSoonMovies(MovieListingDTO soonMovies) {
        this.soonMovies = soonMovies;
    }

    public void setConfiguration(ConfigurationDTO configuration) {
        this.configuration = configuration;
    }

    public void setMovie(MovieDTO movie) {
        if (this.movie.get(movie.getId()) == null) {
            this.movie.put(movie.getId(), movie);
            this.setIsBeingFollowed(movie.getId(), false);
        }
    }

    public void setIsBeingFollowed(Integer movieId, Boolean isBeingFollowed) {
        this.isBeingFollowed.put(movieId, isBeingFollowed);
    }

    public boolean isBeingFollowed(int movieId) {
        return this.isBeingFollowed.get(movieId);
    }

    /**
     * Create a new instance of DiskMovieDataStore and returns it
     * @return A new Instance
     */
    public static DiskMovieDataStore create() {
        return new DiskMovieDataStore();
    }
}
