package isel.pdm.yamda.data.repository;

import java.util.List;

import isel.pdm.yamda.model.entity.MovieDetails;
import isel.pdm.yamda.model.entity.MovieListDetails;
import isel.pdm.yamda.presentation.view.contract.ILoadDataView;

/**
 * This interface defines the contract with the data layer
 * and the presentation layer for requesting data
 */
public interface IMovieRepository {

    /**
     * Set the movies in the theaters right now
     * @param page
     * @return
     */
    void setTheatersMovies(ILoadDataView<List<MovieListDetails>> presenter, int page);

    /**
     * Set next movies getting into theaters
     * @param page
     * @return
     */
    void setSoonMovies(ILoadDataView<List<MovieListDetails>> presenter,int page);

    /**
     * Set the top movies in theaters
     * @param page
     * @return
     */
    void setTopMovies(ILoadDataView<List<MovieListDetails>> presenter, int page);

    /**
     * Set a movie details
     * @param id
     * @return
     */
    void setMovie(ILoadDataView<MovieDetails> presenter, int id);

    /**
     * Search for a movie by name
     * @param presenter
     * @param search
     */
    void setMovieSearch(ILoadDataView<List<MovieListDetails>> presenter, String search, int page);
}
