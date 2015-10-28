package isel.pdm.yamda.model;

import junit.framework.TestCase;

import org.junit.Test;

import retrofit.Call;
import retrofit.Retrofit;

/**
 * Created by Nuno on 27/10/2015.
 */
public class TheMovieAPITest extends TestCase{

    @Test
    public void testGetMovie() throws Exception {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TheMovieAPI.BASE_URL)
                .build();

        TheMovieAPI service = retrofit.create(TheMovieAPI.class);

        Call<Movie> movie = service.getMovie("550", TheMovieAPI.API_KEY, null, null);

        System.out.println(movie.execute().body().toString());

    }

    @Test
    public void testGetNowPlaying() throws Exception {

    }

    @Test
    public void testGetUpcoming() throws Exception {

    }

    @Test
    public void testGetMostPopular() throws Exception {

    }
}