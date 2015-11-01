package isel.pdm.yamda.model.entity.mapper;

import java.util.ArrayList;
import java.util.List;

import isel.pdm.yamda.model.entity.Movie;
import isel.pdm.yamda.presentation.view.entity.MovieView;

/**
 * Created by Nuno on 01/11/2015.
 */
public class MovieModelMapper {

    public MovieView transform(Movie movie) {
        return new MovieView(movie.getTitle(),
                movie.getStatus(),
                movie.getReleaseDate(),
                movie.getAbbreviation(),
                movie.getGenres(),
                movie.getRating());
    }

    public List<MovieView> transform(List<Movie> movies) {
        List<MovieView> movieViews = new ArrayList<>();
        for (Movie movie : movies) {
            movieViews.add(transform(movie));
        }
        return movieViews;
    }
}
