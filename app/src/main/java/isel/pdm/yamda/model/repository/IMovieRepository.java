package isel.pdm.yamda.model.repository;

import java.io.IOException;
import java.util.List;

import isel.pdm.yamda.model.entity.Movie;

/**
 * This interface defines the contract with the data layer for getting the data
 */
public interface IMovieRepository {

    /**
     * Get the movies in the theaters right now
     * @param page
     * @return
     * @throws IOException
     */
    List<Movie> getTheatersMovies(int page) throws IOException;

    /**
     * Get next movies getting into theaters
     * @param page
     * @return
     * @throws IOException
     */
    List<Movie> getSoonMovies(int page) throws IOException;

    /**
     * Get the top movies in theaters
     * @param page
     * @return
     * @throws IOException
     */
    List<Movie> getTopMovies(int page) throws IOException;

    /**
     * Get a movie details
     * @param id
     * @return
     */
    Movie getMovie(int id);
}
