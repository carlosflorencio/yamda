package isel.pdm.yamda.data.entity.mapper;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import isel.pdm.yamda.data.api.TheMovieAPI;
import isel.pdm.yamda.data.entity.MovieDTO;
import isel.pdm.yamda.data.entity.MovieListingDTO;
import isel.pdm.yamda.model.entity.Movie;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Nuno on 31/10/2015.
 */
public class MovieDataMapperTest extends TestCase {

    private TheMovieAPI service;

    MovieDataMapper movieDataMapper;

    @Before
    public void setUp() {
        service = new Retrofit.Builder()
                .baseUrl(TheMovieAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TheMovieAPI.class);

        movieDataMapper = new MovieDataMapper();
    }

    @Test
    public void testTransformMovieDTO() throws Exception {
        MovieDTO dto = service.getMovie(27205, TheMovieAPI.API_KEY, "en", null).execute().body();
        Movie movie = movieDataMapper.transform(dto);

        assertNotNull(movie);
        assertEquals(movie.getTitle(), dto.getTitle());
        assertEquals(movie.getReleaseDate(), dto.getRelease_date());
    }

    @Test
    public void testTransformMovieListingDTO() throws Exception {
        MovieListingDTO listingDTO = service.getMostPopular(TheMovieAPI.API_KEY, 1, "en").execute().body();
        List<Movie> movies = movieDataMapper.transform(listingDTO);

        assertNotNull(movies);
        assertEquals(listingDTO.getResults().size(), movies.size());
    }

}
