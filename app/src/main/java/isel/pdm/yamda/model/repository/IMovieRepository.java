package isel.pdm.yamda.model.repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import isel.pdm.yamda.data.entity.MovieListingDTO;
import isel.pdm.yamda.model.entity.Configuration;
import isel.pdm.yamda.model.entity.Movie;

/**
 * Created by Nuno on 30/10/2015.
 */
public interface IMovieRepository {
    Configuration getApiConfiguration() throws IOException;
    HashMap<String, MovieListingDTO> getListings() throws IOException;

    List<Movie> getListing(String tag) throws IOException;

    Movie getMovie(int id);
}
