package isel.pdm.yamda.data;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import isel.pdm.yamda.data.entity.ConfigurationDTO;
import isel.pdm.yamda.data.entity.MovieListDTO;
import isel.pdm.yamda.data.entity.MovieListingDTO;
import isel.pdm.yamda.data.repository.IMovieRepository;
import isel.pdm.yamda.data.repository.MovieRepository;

/**
 * Created by Nuno on 30/10/2015.
 */
public class MovieRepositoryTest extends TestCase{

    @Test
    public void testApiConfigurationNotNull() throws Exception {
        IMovieRepository repo = MovieRepository.create();
        ConfigurationDTO configuration = repo.getApiConfiguration();

        assertNotNull(configuration);
    }

    @Test
    public void testApiConfigurationResponse() throws Exception {
        IMovieRepository repo = MovieRepository.create();
        ConfigurationDTO configuration = repo.getApiConfiguration();

        assertEquals("http://image.tmdb.org/t/p/", configuration.getBaseUrl());
        assertEquals("w92", configuration.getPosterSizes()[0]);
    }

    @Test
    public void testGetListingsSize() throws Exception {
        IMovieRepository repo = MovieRepository.create();
        HashMap<String, MovieListingDTO> map = repo.getListings();

        assertEquals(3, map.size());
    }

    @Test
    public void testGetListingsNotNull() throws Exception {
        IMovieRepository repo = MovieRepository.create();
        HashMap<String, MovieListingDTO> map = repo.getListings();

        assertNotNull(map.get(MovieListingDTO.POPULAR_TAG));
        assertNotNull(map.get(MovieListingDTO.UPCOMING_TAG));
        assertNotNull(map.get(MovieListingDTO.NOW_PLAYING_TAG));
    }

    @Test
    public void testGetListingsResponse() throws Exception {
        IMovieRepository repo = MovieRepository.create();
        HashMap<String, MovieListingDTO> map = repo.getListings();

        MovieListingDTO listing = map.get(MovieListingDTO.POPULAR_TAG);
        List<MovieListDTO> movies = listing.getResults();

        assertEquals(1, listing.getPage());
        assertNotNull(movies);
    }

    @Test
    public void testGetListingPopular() throws Exception {
        IMovieRepository repo = MovieRepository.create();
        MovieListingDTO listing = repo.getListing(MovieListingDTO.POPULAR_TAG);

        assertNotNull(listing);
    }
}