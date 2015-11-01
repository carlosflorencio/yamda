package isel.pdm.yamda.data;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import isel.pdm.yamda.data.api.ITheMovieDbServiceAPI;
import isel.pdm.yamda.data.entity.tmdb.ConfigurationDTO;
import isel.pdm.yamda.data.entity.tmdb.GenresDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieListingDTO;
import isel.pdm.yamda.data.entity.tmdb.SearchMovieListDTO;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Nuno on 27/10/2015.
 */
public class TheMovieAPITest extends TestCase{

    private ITheMovieDbServiceAPI service;

    @Before
    public void setUp() {
        service = new Retrofit.Builder()
                .baseUrl(ITheMovieDbServiceAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ITheMovieDbServiceAPI.class);
    }

    @Test
    public void testGetConfiguration() throws IOException {
        Call<ConfigurationDTO> configurationCall = service.getConfig(ITheMovieDbServiceAPI.API_KEY);
        ConfigurationDTO configuration = configurationCall.execute().body();

        assertNotNull(configuration.getImageConfigurations());
    }

    @Test
    public void testGetMovie() throws Exception {
        Call<MovieDTO> movieCall = service.getMovie(550, ITheMovieDbServiceAPI.API_KEY, "en", null);
        Response<MovieDTO> response = movieCall.execute();

        MovieDTO movie = response.body();

        assertNotNull(movie);
        assertEquals(550, movie.getId());
    }

    @Test
    public void testGetNowPlaying() throws Exception {
        Call<MovieListingDTO> movieCall = service.getNowPlaying(ITheMovieDbServiceAPI.API_KEY, 1, "en");
        Response<MovieListingDTO> response = movieCall.execute();

        MovieListingDTO movies = response.body();

        assertNotNull(movies);

        assertEquals(1, movies.getPage());
        assertEquals(20, movies.getResults().size());
    }

    @Test
    public void testGetUpcoming() throws Exception {
        Call<MovieListingDTO> movieCall = service.getUpcoming(ITheMovieDbServiceAPI.API_KEY, 1, "en");
        Response<MovieListingDTO> response = movieCall.execute();

        MovieListingDTO movies = response.body();

        assertNotNull(movies);

        assertEquals(1, movies.getPage());
        assertEquals(20, movies.getResults().size());
    }

    @Test
    public void testGetMostPopular() throws Exception {
        Call<MovieListingDTO> movieCall = service.getMostPopular(ITheMovieDbServiceAPI.API_KEY, 1, "en");
        Response<MovieListingDTO> response = movieCall.execute();

        MovieListingDTO movies = response.body();

        assertNotNull(movies);

        assertEquals(1, movies.getPage());
        assertEquals(20, movies.getResults().size());
    }

    @Test
    public void testGetGenres() throws Exception {
        Call<GenresDTO> genresCall = service.getGenres(ITheMovieDbServiceAPI.API_KEY, "en");
        GenresDTO genres = genresCall.execute().body();

        assertNotNull(genres);
        assertNotNull(genres.getGenres());
        assertNotNull(genres.getGenres()[0]);
    }

    @Test
    public void testGetSearchMovie() throws Exception {
        Call<SearchMovieListDTO> searchCall = service.getSearchedMovies(ITheMovieDbServiceAPI.API_KEY, "inception", 1, "en");
        SearchMovieListDTO search = searchCall.execute().body();

        assertNotNull(search);

        assertEquals(1, search.getPage());
        assertNotNull(search.getResults());
    }
}