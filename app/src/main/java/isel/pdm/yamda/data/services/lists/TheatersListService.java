package isel.pdm.yamda.data.services.lists;

import android.content.Intent;
import android.util.Log;

import java.io.Serializable;
import java.util.List;

import isel.pdm.yamda.YamdaApplication;
import isel.pdm.yamda.data.exception.ApiFailedGettingDataException;
import isel.pdm.yamda.data.services.ListService;
import isel.pdm.yamda.model.MovieListDetails;

/**
 * Class used to //TODO: comentary
 */
public class TheatersListService extends ListService {

    public static final String NOTIFICATION = "isel.pdm.yamda.data.handlers.service.list.TheatersListService";

    @Override
    protected void onHandleIntent(Intent intent) {
        Intent newIntent = new Intent(NOTIFICATION);
        try {
            int page = intent.getIntExtra(PAGE, 1);
            boolean ignoreDisk = intent.getBooleanExtra(IGNORE_DISK, false);
            //Log.v("DEBUG", "Page: "+ page+", : ignoreDisk: "+ignoreDisk);     DEBUG PURPOSE
            List<MovieListDetails> movies = ((YamdaApplication) getApplication()).getMovieRepository().getTheatersMovies(page);

            newIntent.putExtra(DATA, true);
            newIntent.putExtra(MOVIES_PARAM, (Serializable) movies);
        } catch (ApiFailedGettingDataException e) {
            newIntent.putExtra(DATA, false);
            Log.v(TAG, "Exception! Message: " + e.getMessage());
        }
        sendBroadcast(newIntent);
    }

}
