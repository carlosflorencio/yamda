package isel.pdm.yamda.data.provider;

import android.test.ProviderTestCase2;
import android.test.mock.MockContentResolver;

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

    public void testQuery() {

    }
}