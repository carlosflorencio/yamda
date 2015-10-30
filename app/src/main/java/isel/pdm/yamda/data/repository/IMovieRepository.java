package isel.pdm.yamda.data.repository;

import java.io.IOException;
import java.util.HashMap;

import isel.pdm.yamda.data.entity.ConfigurationDTO;
import isel.pdm.yamda.data.entity.MovieListingDTO;

/**
 * Created by Nuno on 30/10/2015.
 */
public interface IMovieRepository {
    ConfigurationDTO getApiConfiguration() throws IOException;
    HashMap<String, MovieListingDTO> getListings() throws IOException;
    MovieListingDTO getListing(String tag) throws IOException;
}
