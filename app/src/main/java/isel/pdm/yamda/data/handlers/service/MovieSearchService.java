package isel.pdm.yamda.data.handlers.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.Serializable;
import java.util.List;

import isel.pdm.yamda.YamdaApplication;
import isel.pdm.yamda.data.exception.ApiFailedGettingDataException;
import isel.pdm.yamda.model.entity.MovieListDetails;


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

    public static final String NOTIFICATION = "isel.pdm.yamda.data.handlers.service.MovieSearchService";

    public MovieSearchService() {
        super("MovieSearchService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Intent intent1 = new Intent(NOTIFICATION);
        try {
            String search = intent.getStringExtra(SEARCH_PARAM);
            int page = intent.getIntExtra(PAGE, 1);
            List<MovieListDetails> movies = ((YamdaApplication) getApplication()).getMovieRepository().getMovieSearch(search, page, false);
            intent1.putExtra(DATA, true);
            intent1.putExtra(SEARCH_RESULTS, (Serializable) movies);
        } catch (ApiFailedGettingDataException e) {
            intent1.putExtra(DATA, false);
            Log.v(TAG, "Exception! Message: " + e.getMessage());
        }
        sendBroadcast(intent1);
    }
}
