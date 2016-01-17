package isel.pdm.yamda.data.repository;

import android.test.ProviderTestCase2;

import java.util.LinkedList;
import java.util.List;

import isel.pdm.yamda.data.exception.FailedGettingDataException;
import isel.pdm.yamda.data.mapper.CursorModelEntitiesDataMapper;
import isel.pdm.yamda.data.provider.MoviesContract;
import isel.pdm.yamda.data.provider.MoviesProvider;
import isel.pdm.yamda.model.Movie;

/**
 * Test the LocalMovieRepository implementation
 * The database is brand new when running all single tests
 */
public class LocalMovieRepositoryTest extends ProviderTestCase2<MoviesProvider> {

    private static LocalMovieRepository repo;

    public LocalMovieRepositoryTest() {
        super(MoviesProvider.class, "isel.pdm.yamda.provider");
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        repo = new LocalMovieRepository(getMockContext(), new CursorModelEntitiesDataMapper());
    }

    public void testInsertMoviesOfAType() throws FailedGettingDataException {
        List<Movie> movies = getMovieList1();

        int inserted = repo.insertMovies(movies, MoviesContract.MovieEntry.TYPE_NOW);
        assertEquals(movies.size(), inserted);
    }

    public void testRetrieveMoviesOfAType() {
        List<Movie> movies = getMovieList1();
        List<Movie> movies2 = getMovieList2();

        int inserted = repo.insertMovies(movies, MoviesContract.MovieEntry.TYPE_NOW);
        int inserted2 = repo.insertMovies(movies2, MoviesContract.MovieEntry.TYPE_SOON);
        assertEquals(movies.size(), inserted);
        assertEquals(movies2.size(), inserted2);

        List<Movie> soon = repo.getSoonMovies();
        assertEquals(movies2.size(), soon.size());

        compareListMovies(movies2, soon);
    }

    public void testIfThereAreMoviesWhenRepoIsEmpty() {
        assertFalse(repo.hasMovies());
    }

    public void testIfThereAreMoviesWhenThereIs() {
        List<Movie> movies = getMovieList1();

        repo.insertMovies(movies, MoviesContract.MovieEntry.TYPE_NOW);

        assertTrue(repo.hasMovies());
    }

    public void testDeleteMoviesWithOneTypeInContentProvider(){
        List<Movie> movies = getMovieList1();
        repo.insertMovies(movies, MoviesContract.MovieEntry.TYPE_NOW);
        assertTrue(repo.hasMovies());

        int rows = repo.deleteMovies(MoviesContract.MovieEntry.TYPE_NOW);
        assertEquals(movies.size(), rows);
    }

    public void testDeleteWithSeveralTypes(){
        List<Movie> movies = getMovieList1();
        repo.insertMovies(movies, MoviesContract.MovieEntry.TYPE_NOW);
        repo.insertMovies(getMovieList2(), MoviesContract.MovieEntry.TYPE_SOON);
        assertTrue(repo.hasMovies());

        int rows = repo.deleteMovies(MoviesContract.MovieEntry.TYPE_NOW);
        assertEquals(movies.size(), rows);
    }

    public void testFollowMovieAndUnfollow() {
        List<Movie> movies = getMovieList1();
        repo.insertMovies(movies, MoviesContract.MovieEntry.TYPE_SOON);

        repo.followMovie(movies.get(1).getId());

        assertTrue(repo.isBeingFollowed(movies.get(1).getId()));

        repo.unfollowMovie(movies.get(1).getId());

        assertFalse(repo.isBeingFollowed(movies.get(1).getId()));
    }

    /*
    |--------------------------------------------------------------------------
    | Utils
    |--------------------------------------------------------------------------
    */
    private List<Movie> getMovieList1() {
        List<Movie> movies = new LinkedList<>();
        // popularity order is important, should be desc
        movies.add(new Movie(1, "title", "original", "2015-12-05", "poster", 7, 9.6));
        movies.add(new Movie(2, "title2", "original2", "2015-12-05", "poster", 7.5, 8.8));
        movies.add(new Movie(3, "title3", "original3", "2015-12-06", "poster", 8.2, 8.2));
        movies.add(new Movie(4, "title4", "original4", "2015-12-07", "poster", 6, 6.1));
        movies.add(new Movie(5, "title5", "original5", "2015-12-08", "poster", 5.0, 6));

        return movies;
    }

    private List<Movie> getMovieList2() {
        List<Movie> movies = new LinkedList<>();
        // popularity order is important, should be desc
        movies.add(new Movie(100, "title 100", "original", "2015-12-05", "poster", 7, 9.6));
        movies.add(new Movie(101, "title 101", "original 2", "2015-12-05", "poster", 7.5, 8.8));
        movies.add(new Movie(102, "title 102", "original 3", "2015-12-06", "poster", 8.2, 8.2));
        movies.add(new Movie(103, "title 103", "original 4", "2015-12-07", "poster", 6, 6.1));

        return movies;
    }

    private void compareListMovies(List<Movie> list1, List<Movie> list2) {
        assertEquals(list1.size(), list2.size());

        for (int i = 0; i < list1.size(); i++) {
            Movie movie1 = list1.get(i);
            Movie movie2 = list2.get(i);

            compareMovies(movie1, movie2);
        }
    }

    private void compareMovies(Movie movie1, Movie movie2) {
        assertEquals(movie1.getId(), movie2.getId());
        assertEquals(movie1.getOriginalTitle(), movie2.getOriginalTitle());
        assertEquals(movie1.getPopularity(), movie2.getPopularity());
        assertEquals(movie1.getPoster(), movie2.getPoster());
        assertEquals(movie1.getRating(), movie2.getRating());
        assertEquals(movie1.getReleaseDate(), movie2.getReleaseDate());
        assertEquals(movie1.getTitle(), movie2.getTitle());
    }
}