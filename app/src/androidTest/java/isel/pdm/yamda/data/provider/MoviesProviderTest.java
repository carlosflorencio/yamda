package isel.pdm.yamda.data.provider;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.test.ProviderTestCase2;
import android.test.mock.MockContentResolver;

import java.util.Locale;

import isel.pdm.yamda.data.provider.table.GenresTable;
import isel.pdm.yamda.data.provider.table.MoviesTable;


public class MoviesProviderTest extends ProviderTestCase2<MoviesProvider> {

    // in the test case scenario, we use the MockContentResolver to make queries
    private static MockContentResolver resolve;

    public MoviesProviderTest() {
        super(MoviesProvider.class, "isel.pdm.yamda.provider");
    }

    @Override
    public void setUp() {
        try {
            super.setUp();
            resolve = this.getMockContentResolver();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test if the provider is registered correctly
     */
    public void testProviderRegistry() {
        PackageManager pm = mContext.getPackageManager();

        // We define the component name based on the package name from the context and the
        // MoviesProvider class.
        ComponentName componentName = new ComponentName(mContext.getPackageName(),
                                                        MoviesProvider.class.getName());
        try {
            // Fetch the provider info using the component name from the PackageManager
            // This throws an exception if the provider isn't registered.
            ProviderInfo providerInfo = pm.getProviderInfo(componentName, 0);

            // Make sure that the registered authority matches the authority from the Contract.
            assertEquals(providerInfo.authority, MoviesContract.CONTENT_AUTHORITY);
        } catch (PackageManager.NameNotFoundException e) {
            // I guess the provider isn't registered correctly.
            assertTrue("Error: MoviesProvider not registered at " + mContext.getPackageName(),
                       false);
        }
    }

    /**
     * Test all provider types
     */
    public void testGetType() {
        String type = resolve.getType(MoviesContract.MovieEntry.CONTENT_URI);
        assertEquals(MoviesContract.MovieEntry.CONTENT_TYPE, type);

        int movieId = 214;
        type = resolve.getType(MoviesContract.MovieEntry.buildMovieUri(movieId));
        assertEquals(MoviesContract.MovieEntry.CONTENT_ITEM_TYPE, type);

        type = resolve.getType(MoviesContract.FollowEntry.CONTENT_URI);
        assertEquals(MoviesContract.FollowEntry.CONTENT_TYPE, type);
    }

    /*
    |--------------------------------------------------------------------------
    | Insert tests
    |--------------------------------------------------------------------------
    */
    public void testInsertMovie() {
        TestUtils.Movie movie = TestUtils.defaultMovies[1];
        ContentValues values = TestUtils.createMovieContent(movie);

        Uri uri = resolve.insert(MoviesContract.MovieEntry.CONTENT_URI, values);

        assertEquals(uri, MoviesContract.MovieEntry.buildMovieUri(movie.id));

        Cursor res = resolve.query(uri, null, null, null, null);

        assertEquals(1, res.getCount());

        TestUtils.validateCursor(res, values);

        res.close();
    }

    public void testInsertBulkMovies() {
        final Uri uri = MoviesContract.MovieEntry.CONTENT_URI;
        final int len = TestUtils.defaultMovies.length;

        int inserted = this.insertMoviesInContentProvider();
        assertEquals(len, inserted);

        Cursor res = resolve.query(uri, null, null, null, null);

        assertEquals(len, res.getCount());

        int i = 0;
        while (res.moveToNext()) {
            ContentValues v = TestUtils.createMovieContent(TestUtils.defaultMovies[i++]);
            TestUtils.validateCurrentRecord(res, v);
        }

        res.close();
    }

    public void testInsertBulkGenres() {
        final Uri uri = MoviesContract.GenreEntry.CONTENT_URI;
        final int len = TestUtils.defaultGenres.length;

        int inserted = insertGenresInContentProvider();
        assertEquals(len, inserted);

        Cursor res = resolve.query(uri, null, null, null, null);

        assertEquals(len, res.getCount());

        int i = 0;
        while (res.moveToNext()) {
            ContentValues v = TestUtils.createGenreContent(TestUtils.defaultGenres[i++]);
            TestUtils.validateCurrentRecord(res, v);
        }

        res.close();
    }

    public void testAssociateMovieWithGenres() {
        TestUtils.Movie movie = TestUtils.defaultMovies[0];
        Uri movieUri = resolve.insert(MoviesContract.MovieEntry.CONTENT_URI, TestUtils.createMovieContent(movie));

        Cursor res1 = resolve.query(movieUri, null, null, null, null);
        assertTrue(res1.moveToFirst());

        ContentValues[] valuesPivot = new ContentValues[TestUtils.defaultGenres.length];
        for (int i = 0; i < valuesPivot.length; i++) {
            ContentValues v = new ContentValues();
            v.put(GenresTable.PIVOT_COLUMN_ID, TestUtils.defaultGenres[i].getId());
            v.put(GenresTable.PIVOT_COLUMN_MOVIE_ID, movie.id);
            v.put(GenresTable.PIVOT_COLUMN_LANG, Locale.getDefault().getLanguage());

            valuesPivot[i] = v;
        }

        int insertedGenres = insertGenresInContentProvider();
        assertEquals(TestUtils.defaultGenres.length, insertedGenres);

        final Uri uri = MoviesContract.GenreEntry.CONTENT_URI_PIVOT;
        int inserted = resolve.bulkInsert(uri, valuesPivot);
        assertEquals(TestUtils.defaultGenres.length, inserted);

        final Uri uri_pivot_movie = MoviesContract.GenreEntry.buildGenrePivotUri(movie.id);
        Cursor res = resolve
                .query(uri_pivot_movie, null, null, null,
                       GenresTable.PIVOT_COLUMN_ID + " ASC");

        assertEquals(TestUtils.defaultGenres.length, res.getCount());

        int i = 0;
        while (res.moveToNext()) {
            int id = res.getInt(res.getColumnIndex(GenresTable.PIVOT_COLUMN_ID));
            ContentValues v = valuesPivot[i++];
            TestUtils.validateCurrentRecord(res, v);
        }

        res.close();
        res1.close();
    }

    /*
    |--------------------------------------------------------------------------
    | Updates tests
    |--------------------------------------------------------------------------
    */
    public void testUpdateMovieWithDetails() {
        TestUtils.Movie movie = TestUtils.defaultMovies[1];
        ContentValues values = TestUtils.createMovieContent(movie);

        Uri uri = resolve.insert(MoviesContract.MovieEntry.CONTENT_URI, values);

        ContentValues addValues = new ContentValues();
        addValues.put(MoviesTable.COLUMN_DOWNLOADED, 1);
        addValues.put(MoviesTable.COLUMN_RUNTIME, 150);
        addValues.put(MoviesTable.COLUMN_OVERVIEW, "Overview");

        int updated = resolve.update(uri, addValues, null, null);

        assertEquals(1, updated);

        Cursor c = resolve.query(uri, null, null, null, null);

        assertTrue(c.moveToFirst());

        int downloaded = c.getInt(c.getColumnIndex(MoviesTable.COLUMN_DOWNLOADED));
        int runtime = c.getInt(c.getColumnIndex(MoviesTable.COLUMN_RUNTIME));
        String overview = c.getString(c.getColumnIndex(MoviesTable.COLUMN_OVERVIEW));

        assertEquals(1, downloaded);
        assertEquals(150, runtime);
        assertEquals("Overview", overview);

        c.close();
    }

    /*
    |--------------------------------------------------------------------------
    | Deletes tests
    |--------------------------------------------------------------------------
    */
    public void testDeleteMovieList() {
        final Uri uri = MoviesContract.MovieEntry.CONTENT_URI;
        int inserted = this.insertMoviesInContentProvider();

        int deleted = resolve.delete(uri, null, null);

        assertEquals(inserted, deleted);

        Cursor c = resolve.query(uri, null, null, null, null);

        assertFalse(c.moveToFirst());

        c.close();
    }

    public void testDeleteMovieById() {
        final Uri uri = MoviesContract.MovieEntry.CONTENT_URI;
        TestUtils.Movie m = TestUtils.defaultMovies[0];
        ContentValues contentValues = TestUtils.createMovieContent(m);
        Uri inserted = resolve.insert(uri, contentValues);

        Cursor c = resolve.query(inserted, null, null, null, null);

        TestUtils.validateCursor(c, contentValues);

        int rowsAffected = resolve.delete(inserted, null, null);

        assertEquals(1, rowsAffected);

        c.close();
    }

    public void testDeleteGenresList() {
        final Uri uri = MoviesContract.GenreEntry.CONTENT_URI;
        final int len = TestUtils.defaultGenres.length;

        int inserted = insertGenresInContentProvider();
        assertEquals(len, inserted);

        int deleted = resolve.delete(uri, null, null);
        assertEquals(len, deleted);

        Cursor res = resolve.query(uri, null, null, null, null);

        assertFalse(res.moveToFirst());

        res.close();
    }

    /*
    |--------------------------------------------------------------------------
    | Utils
    |--------------------------------------------------------------------------
    */
    private int insertMoviesInContentProvider() {
        final Uri uri = MoviesContract.MovieEntry.CONTENT_URI;
        final int len = TestUtils.defaultMovies.length;

        ContentValues[] values = new ContentValues[len];

        for (int i = 0; i < values.length; i++) {
            values[i] = TestUtils.createMovieContent(TestUtils.defaultMovies[i]);
        }

        return resolve.bulkInsert(uri, values);
    }

    private int insertGenresInContentProvider() {
        final Uri uri = MoviesContract.GenreEntry.CONTENT_URI;
        final int len = TestUtils.defaultGenres.length;

        ContentValues[] values = new ContentValues[len];

        for (int i = 0; i < values.length; i++) {
            values[i] = TestUtils.createGenreContent(TestUtils.defaultGenres[i]);
        }

        return resolve.bulkInsert(uri, values);
    }

}