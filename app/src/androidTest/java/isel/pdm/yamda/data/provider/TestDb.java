package isel.pdm.yamda.data.provider;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import java.util.HashSet;

import isel.pdm.yamda.data.provider.table.FollowMoviesTable;
import isel.pdm.yamda.data.provider.table.MoviesTable;

public class TestDb extends AndroidTestCase {

    private MoviesDatabaseHelper databaseHelper;

    public void setUp() throws Exception {
        super.setUp();

        final String prefix = "test.";

        // use a different db prefixed with test.
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), prefix);
        databaseHelper = new MoviesDatabaseHelper(context);

        // Since we want each test to start with a clean slate
        mContext.deleteDatabase(prefix + MoviesDatabaseHelper.DATABASE_NAME);
    }

    /**
     * Test if tables are created successfully
     * @throws Throwable
     */
    public void testCreateDb() throws Throwable {
        final HashSet<String> tableNameHashSet = new HashSet<String>();
        tableNameHashSet.add(MoviesTable.NAME);
        tableNameHashSet.add(FollowMoviesTable.NAME);

        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        assertEquals(true, db.isOpen());

        // have we created the tables we want?
        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        // if fails the database has not been created correctly
        assertTrue(c.moveToFirst());

        do {
            tableNameHashSet.remove(c.getString(0));
        } while( c.moveToNext() );

        //if this fails, some table was not created
        assertTrue(tableNameHashSet.isEmpty());

        db.close();
    }

    /**
     * Test if movies are inserted correctly
     */
    public void testInsertMovies() {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        for (TestUtils.Movie movie : TestUtils.defaultMovies) {
            ContentValues cMovie = TestUtils.createMovieContent(movie);
            assertTrue(db.insert(MoviesTable.NAME, null, cMovie) != -1L);
        }

        // Count rows
        Cursor c = db.rawQuery("SELECT * FROM " + MoviesTable.NAME, null);
        assertEquals(TestUtils.defaultMovies.length, c.getCount());

        db.close();
    }
}
