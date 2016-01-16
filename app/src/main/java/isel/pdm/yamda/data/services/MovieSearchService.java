package isel.pdm.yamda.data.services;

import android.app.IntentService;
import android.content.Intent;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 */
public class MovieSearchService extends IntentService {

    public final String TAG = "DEBUG_" + getClass().getSimpleName();

    public static final String DATA = "data_ok";

    public static final String SEARCH_PARAM = "movie_search";

    public static final String PAGE = "search_page";

    public static final String SEARCH_RESULTS = "search_results";

    public static final String NOTIFICATION = "isel.pdm.yamda.data.services.MovieSearchService";

    public MovieSearchService() {
        super("MovieSearchService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
//        Intent intent1 = new Intent(NOTIFICATION);
//        try {
//            String search = intent.getStringExtra(SEARCH_PARAM);
//            int page = intent.getIntExtra(PAGE, 1);
//            List<Movie> movies = ((YamdaApplication) getApplication()).getMovieRepository().getMovieSearch(search, page);
//            intent1.putExtra(DATA, true);
//            intent1.putExtra(SEARCH_RESULTS, (Serializable) movies);
//        } catch (FailedGettingDataException e) {
//            intent1.putExtra(DATA, false);
//            Log.v(TAG, "Exception! Message: " + e.getMessage());
//        }
//        sendBroadcast(intent1);
    }
}
