package isel.pdm.yamda.data.handlers.service.lists;

import android.content.Intent;
import android.util.Log;

import java.io.Serializable;
import java.util.List;

import isel.pdm.yamda.YamdaApplication;
import isel.pdm.yamda.data.exception.ApiFailedGettingDataException;
import isel.pdm.yamda.data.handlers.service.ListService;
import isel.pdm.yamda.model.entity.MovieListDetails;

/**
 * Class used to //TODO: comentary
 */
public class SoonListService extends ListService {

    public static final String NOTIFICATION = "isel.pdm.yamda.data.handlers.service.list.SoonListService";

    @Override
    protected void onHandleIntent(Intent intent) {
        Intent newIntent = new Intent(NOTIFICATION);
        try {
            int page = intent.getIntExtra(PAGE, 1);
            boolean ignoreDisk = intent.getBooleanExtra(IGNORE_DISK, false);
            List<MovieListDetails> movies = ((YamdaApplication) getApplication()).getMovieRepository().getSoonMovies(page, ignoreDisk);

            newIntent.putExtra(DATA, true);
            newIntent.putExtra(MOVIES_PARAM, (Serializable) movies);
        } catch (ApiFailedGettingDataException e) {
            newIntent.putExtra(DATA, false);
            Log.v(TAG, "Exception! Message: " + e.getMessage());
        }
        sendBroadcast(newIntent);
    }

}
