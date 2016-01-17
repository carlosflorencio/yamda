package isel.pdm.yamda.data.services.lists;

import android.content.Intent;

import isel.pdm.yamda.data.services.ListService;

/**
 * Class used to //TODO: comentary
 */
public class SoonListService extends ListService {

    public static final String NOTIFICATION = "isel.pdm.yamda.data.handlers.service.list.SoonListService";

    @Override
    protected void onHandleIntent(Intent intent) {
        //TODO: get from web to content provider
//        Intent newIntent = new Intent(NOTIFICATION);
//        try {
//            int page = intent.getIntExtra(PAGE, 1);
//            boolean ignoreDisk = intent.getBooleanExtra(IGNORE_DISK, false);
//            List<Movie> movies = ((YamdaApplication) getApplication()).getMovieRepository().getSoonMovies(page);
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
