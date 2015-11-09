package isel.pdm.yamda.data.entity.mapper;

import junit.framework.TestCase;

/**
 * Created by Nuno on 31/10/2015.
 */
public class MovieDataMapperTest extends TestCase {

//    private ITheMovieDbServiceAPI service;
//
//    MovieDataMapper movieDataMapper;
//
//    @Before
//    public void setUp() {
//        service = new Retrofit.Builder()
//                .baseUrl(ITheMovieDbServiceAPI.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(ITheMovieDbServiceAPI.class);
//
//        movieDataMapper = new MovieDataMapper();
//    }
//
//    @Test
//    public void testTransformMovieDTO() throws Exception {
//        MovieDTO dto = service.getMovie(27205, ITheMovieDbServiceAPI.API_KEY, "en", null).execute().body();
//        Movie movie = movieDataMapper.transform(dto);
//
//        assertNotNull(movie);
//        assertEquals(movie.getTitle(), dto.getTitle());
//        assertEquals(movie.getReleaseDate(), dto.getRelease_date());
//    }
//
//    @Test
//    public void testTransformMovieListingDTO() throws Exception {
//        MovieListingDTO listingDTO = service.getMostPopular(ITheMovieDbServiceAPI.API_KEY, 1, "en").execute().body();
//        List<Movie> movies = movieDataMapper.transform(listingDTO);
//
//        assertNotNull(movies);
//        assertEquals(listingDTO.getResults().size(), movies.size());
//    }

}
