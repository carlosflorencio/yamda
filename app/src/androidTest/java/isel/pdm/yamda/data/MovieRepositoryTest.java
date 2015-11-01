package isel.pdm.yamda.data;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import isel.pdm.yamda.data.entity.MovieListingDTO;
import isel.pdm.yamda.data.repository.MovieRepository;
import isel.pdm.yamda.model.entity.Configuration;
import isel.pdm.yamda.model.entity.Movie;
import isel.pdm.yamda.model.repository.IMovieRepository;

/**
 * Created by Nuno on 30/10/2015.
 */
public class MovieRepositoryTest extends TestCase{

    @Test
    public void testApiConfigurationNotNull() throws Exception {
        IMovieRepository repo = MovieRepository.create();
        Configuration configuration = repo.getApiConfiguration();

        assertNotNull(configuration);
    }

    @Test
    public void testApiConfigurationResponse() throws Exception {
        IMovieRepository repo = MovieRepository.create();
        Configuration configuration = repo.getApiConfiguration();

        assertEquals("http://image.tmdb.org/t/p/", configuration.getBaseUrl());
        assertEquals("w92", configuration.getPosterSizes()[0]);
    }

    @Test
    public void testGetListingsSize() throws Exception {
        IMovieRepository repo = MovieRepository.create();
        HashMap<String, List<Movie>> map = repo.getListings();

        assertEquals(3, map.size());
    }

    @Test
    public void testGetListingsNotNull() throws Exception {
        IMovieRepository repo = MovieRepository.create();
        HashMap<String, List<Movie>> map = repo.getListings();

        assertNotNull(map.get(MovieListingDTO.POPULAR_TAG));
        assertNotNull(map.get(MovieListingDTO.UPCOMING_TAG));
        assertNotNull(map.get(MovieListingDTO.NOW_PLAYING_TAG));
    }

    @Test
    public void testGetListingsResponse() throws Exception {
        IMovieRepository repo = MovieRepository.create();
        HashMap<String, List<Movie>> map = repo.getListings();

        List<Movie> listing = map.get(MovieListingDTO.POPULAR_TAG);
        assertNotNull(listing);

        Movie movie = listing.get(0);
        assertNotNull(movie);
    }

    @Test
    public void testGetListingPopular() throws Exception {
        IMovieRepository repo = MovieRepository.create();
        List<Movie> listing = repo.getListing(MovieListingDTO.POPULAR_TAG);

        assertNotNull(listing);
    }
}