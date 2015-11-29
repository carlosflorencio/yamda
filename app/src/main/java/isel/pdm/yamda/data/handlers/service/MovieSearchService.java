package isel.pdm.yamda.data.handlers.service;

import android.app.IntentService;
import android.content.Intent;

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

    public static final String SEARCH_PARAM = "movie_search";

    public static final String PAGE = "search_page";

    public static final String SEARCH_RESULTS = "search_results";

    public static final String NOTIFICATION = "isel.pdm.yamda.data.handlers.service.MovieSearchService";

    public MovieSearchService() {
        super("MovieSearchService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            try {
                String search = intent.getStringExtra(SEARCH_PARAM);
                int page = intent.getIntExtra(PAGE, 1);
                List<MovieListDetails> movies = ((YamdaApplication) getApplication()).getMovieRepository().getMovieSearch(search, page);
                handleAction(movies);
            } catch (ApiFailedGettingDataException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleAction(List<MovieListDetails> movies) {
        Intent intent = new Intent(NOTIFICATION);
        intent.putExtra(SEARCH_RESULTS, (Serializable) movies);
        sendBroadcast(intent);
    }
}
