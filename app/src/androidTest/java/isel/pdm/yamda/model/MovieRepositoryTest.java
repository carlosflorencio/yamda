package isel.pdm.yamda.model;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import isel.pdm.yamda.model.entity.Configuration;
import isel.pdm.yamda.model.entity.Movie;
import isel.pdm.yamda.model.entity.MovieListing;
import isel.pdm.yamda.model.repository.IMovieRepository;
import isel.pdm.yamda.model.repository.MovieRepository;

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
        HashMap<String, MovieListing> map = repo.getListings();

        assertEquals(3, map.size());
    }

    @Test
    public void testGetListingsNotNull() throws Exception {
        IMovieRepository repo = MovieRepository.create();
        HashMap<String, MovieListing> map = repo.getListings();

        assertNotNull(map.get(MovieListing.POPULAR_TAG));
        assertNotNull(map.get(MovieListing.UPCOMING_TAG));
        assertNotNull(map.get(MovieListing.NOW_PLAYING_TAG));
    }

    @Test
    public void testGetListingsResponse() throws Exception {
        IMovieRepository repo = MovieRepository.create();
        HashMap<String, MovieListing> map = repo.getListings();

        MovieListing listing = map.get(MovieListing.POPULAR_TAG);
        List<Movie> movies = listing.getResults();

        assertEquals(1, listing.getPage());
        assertNotNull(movies);
    }

    @Test
    public void testGetListingPopular() throws Exception {
        IMovieRepository repo = MovieRepository.create();
        MovieListing listing = repo.getListing(MovieListing.POPULAR_TAG);

        assertNotNull(listing);
    }
}