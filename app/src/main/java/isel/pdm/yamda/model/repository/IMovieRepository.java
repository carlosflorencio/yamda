package isel.pdm.yamda.model.repository;

import java.util.List;

import isel.pdm.yamda.model.entity.Movie;
import isel.pdm.yamda.presentation.ILoadDataView;

/**
 * This interface defines the contract with the data layer for setting the data in a viewLoadData
 */
public interface IMovieRepository {

    /**
     * Set the movies in the theaters right now
     * @param page
     * @return
     */
    void setTheatersMovies(ILoadDataView<List<Movie>> presenter, int page);

    /**
     * Set next movies getting into theaters
     * @param page
     * @return
     */
    void setSoonMovies(ILoadDataView<List<Movie>> presenter,int page);

    /**
     * Set the top movies in theaters
     * @param page
     * @return
     */
    void setTopMovies(ILoadDataView<List<Movie>> presenter, int page);

    /**
     * Set a movie details
     * @param id
     * @return
     */
    void setMovie(ILoadDataView<Movie> presenter, int id);
}
