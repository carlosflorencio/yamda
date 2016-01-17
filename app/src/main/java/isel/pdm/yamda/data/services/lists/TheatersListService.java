package isel.pdm.yamda.data.services.lists;

import android.content.Intent;

import isel.pdm.yamda.data.services.ListService;

/**
 * Class used to //TODO: comentary
 */
public class TheatersListService extends ListService {
    public static final String NOTIFICATION = "isel.pdm.yamda.data.handlers.service.list.TheatersListService";

    @Override
    protected void onHandleIntent(Intent intent) {
        //TODO: get from web to content provider
//        Intent newIntent = new Intent(NOTIFICATION);
//        try {
//            int page = intent.getIntExtra(PAGE, 1);
//            //Log.v("DEBUG", "Page: "+ page+", : ignoreDisk: "+ignoreDisk);     DEBUG PURPOSE
//            List<Movie> movies = ((YamdaApplication) getApplication()).getMovieRepository().getTheatersMovies(page);
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
