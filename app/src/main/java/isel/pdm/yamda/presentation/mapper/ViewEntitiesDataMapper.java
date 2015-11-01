package isel.pdm.yamda.presentation.mapper;

import java.util.ArrayList;
import java.util.List;

import isel.pdm.yamda.model.entity.Movie;
import isel.pdm.yamda.presentation.view.entity.MovieView;

/**
 * This class knows how to transform a model business to a view entity
 */
public class ViewEntitiesDataMapper {

    /**
     * Transform a model movie to a view entity
     * @param movie
     * @return
     */
    public MovieView transform(Movie movie) {
        return new MovieView(movie.getTitle(),
                movie.getStatus(),
                movie.getReleaseDate(),
                movie.getAbbreviation(),
                new String[] {"oi"},
                movie.getRating());
    }


    /**
     * Transform a model movie list to a view movie list
     * @param movies
     * @return
     */
    public List<MovieView> transform(List<Movie> movies) {
        List<MovieView> movieViews = new ArrayList<>();
        for (Movie movie : movies) {
            movieViews.add(transform(movie));
        }
        return movieViews;
    }
}
