package isel.pdm.yamda.data.handlers.service;

import android.app.IntentService;
import android.content.Intent;

import isel.pdm.yamda.YamdaApplication;
import isel.pdm.yamda.data.exception.ApiFailedGettingDataException;
import isel.pdm.yamda.model.entity.MovieDetails;

/**
 * Class used to //TODO: comentary
 */
public class MovieDetailsService extends IntentService {

    public final String TAG = "DEBUG_" + getClass().getSimpleName();

    public static final String ID_PARAM = "id_parameter";

    public static final String MOVIE_PARAM = "movie_parameter";

    public static final String NOTIFICATION = "isel.pdm.yamda.data.handlers.service.MovieDetailsService";


    public MovieDetailsService() {
        super(MovieDetailsService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            int id = intent.getIntExtra(ID_PARAM, -1);
            MovieDetails movie = ((YamdaApplication) getApplication()).getMovieRepository().getMovieById(id);

            Intent intent1 = new Intent(NOTIFICATION);
            intent1.putExtra(MOVIE_PARAM, movie);
            sendBroadcast(intent1);
        } catch (ApiFailedGettingDataException e) {
            e.printStackTrace();
        }
    }

}
