package isel.pdm.yamda.data.repository.datasource;

import java.util.HashMap;
import java.util.Map;

import isel.pdm.yamda.data.api.entity.MovieDTO;
import isel.pdm.yamda.data.api.entity.MovieListingDTO;


/**
 * This class should get all the movies information stored in ram
 */
public class MemoryMovieDataStore {

    private MovieListingDTO theatersMovies;

    private MovieListingDTO soonMovies;


    private Map<Integer, MovieDTO> movie;

    private Map<Integer, Boolean> isBeingFollowed;

    /**
     * Constructs a new MemoryMovieDataStore instance
     */
    public MemoryMovieDataStore() {
        movie = new HashMap<>();
        isBeingFollowed = new HashMap<>();
    }

    public MovieListingDTO getTheatersMovies(int page) {
        return this.theatersMovies;
    }

    public MovieListingDTO getSoonMovies(int page) {
        return this.soonMovies;
    }

    public MovieListingDTO getTopMovies(int page) {
         throw new UnsupportedOperationException("Operation is not available yet!!!");
    }

    public MovieDTO getMovie(int id) {
        if (this.movie.get(id) == null) {
            return null;
        }
        return this.movie.get(id);
    }

    public MovieListingDTO getMoviesSearch(String search, int page) {
         throw new UnsupportedOperationException("Operation is not available yet!!!");
    }

    public void setTheatersMovies(MovieListingDTO theatersMovies) {
        this.theatersMovies = theatersMovies;
    }

    public void setSoonMovies(MovieListingDTO soonMovies) {
        this.soonMovies = soonMovies;
    }

    public void setMovie(MovieDTO movie) {
        int id = movie.getId();
        if (this.movie.get(id) == null) {
            this.movie.put(id, movie);
            this.setIsBeingFollowed(id, false);
        }
    }

    public void setIsBeingFollowed(Integer movieId, Boolean isBeingFollowed) {
        this.isBeingFollowed.put(movieId, isBeingFollowed);
    }

    public boolean isBeingFollowed(int movieId) {
        return this.isBeingFollowed.get(movieId);
    }
}
