package isel.pdm.yamda.model.repository;

import java.io.IOException;
import java.util.HashMap;

import isel.pdm.yamda.model.entity.Configuration;
import isel.pdm.yamda.model.entity.MovieListing;

/**
 * Created by Nuno on 30/10/2015.
 */
public interface IMovieRepository {
    Configuration getApiConfiguration() throws IOException;
    HashMap<String, MovieListing> getListings() throws IOException;
    MovieListing getListing(String tag) throws IOException;
}
