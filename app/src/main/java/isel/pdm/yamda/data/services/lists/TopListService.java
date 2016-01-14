package isel.pdm.yamda.data.services.lists;

import android.content.Intent;

import isel.pdm.yamda.data.services.ListService;

/**
 * Class used to //TODO: comentary
 */
public class TopListService extends ListService {


    public static final String NOTIFICATION = "isel.pdm.yamda.data.handlers.service.list.TopListService";

    @Override
    protected void onHandleIntent(Intent intent) {
//        Intent newIntent = new Intent(NOTIFICATION);
//        try {
//            int page = intent.getIntExtra(PAGE, 1);
//            List<MovieListDetails> movies = ((YamdaApplication) getApplication()).getMovieRepository().getTopMovies(page);
//
//            newIntent.putExtra(DATA, true);
//            newIntent.putExtra(MOVIES_PARAM, (Serializable) movies);
//        } catch (FailedGettingDataException e) {
//            newIntent.putExtra(DATA, false);
//            Log.v(TAG, "Exception! Message: " + e.getMessage());
//        }
//        sendBroadcast(newIntent);
    }
}
