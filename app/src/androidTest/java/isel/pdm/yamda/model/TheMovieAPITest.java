package isel.pdm.yamda.model;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import isel.pdm.yamda.model.api.TheMovieAPI;
import isel.pdm.yamda.model.entity.Configuration;
import isel.pdm.yamda.model.entity.Movie;
import isel.pdm.yamda.model.entity.MovieListing;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Nuno on 27/10/2015.
 */
public class TheMovieAPITest extends TestCase{

    private TheMovieAPI service;

    @Before
    public void setUp() {
        service = new Retrofit.Builder()
                .baseUrl(TheMovieAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TheMovieAPI.class);
    }

    @Test
    public void testGetConfiguration() throws IOException {
        Call<Configuration> configurationCall = service.getConfig(TheMovieAPI.API_KEY);
        Configuration configuration = configurationCall.execute().body();

        assertNotNull(configuration.getImageConfigurations());
    }

    @Test
    public void testGetMovie() throws Exception {
        Call<Movie> movieCall = service.getMovie(550, TheMovieAPI.API_KEY, "en", null);
        Response<Movie> response = movieCall.execute();

        Movie movie = response.body();

        assertNotNull(movie);
        assertEquals(550, movie.getId());
    }

    @Test
    public void testGetNowPlaying() throws Exception {
        Call<MovieListing> movieCall = service.getNowPlaying(TheMovieAPI.API_KEY, 1, "en");
        Response<MovieListing> response = movieCall.execute();

        MovieListing movies = response.body();

        assertNotNull(movies);

        assertEquals(1, movies.getPage());
        assertEquals(20, movies.getResults().size());
    }

    @Test
    public void testGetUpcoming() throws Exception {
        Call<MovieListing> movieCall = service.getUpcoming(TheMovieAPI.API_KEY, 1, "en");
        Response<MovieListing> response = movieCall.execute();

        MovieListing movies = response.body();

        assertNotNull(movies);

        assertEquals(1, movies.getPage());
        assertEquals(20, movies.getResults().size());
    }

    @Test
    public void testGetMostPopular() throws Exception {
        Call<MovieListing> movieCall = service.getMostPopular(TheMovieAPI.API_KEY, 1, "en");
        Response<MovieListing> response = movieCall.execute();

        MovieListing movies = response.body();

        assertNotNull(movies);

        assertEquals(1, movies.getPage());
        assertEquals(20, movies.getResults().size());
    }
}