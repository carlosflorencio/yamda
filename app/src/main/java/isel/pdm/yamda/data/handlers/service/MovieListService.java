package isel.pdm.yamda.data.handlers.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.Serializable;
import java.util.List;

import isel.pdm.yamda.YamdaApplication;
import isel.pdm.yamda.data.exception.ApiFailedGettingDataException;
import isel.pdm.yamda.model.entity.MovieListDetails;
import isel.pdm.yamda.presentation.presenter.InTheatersMoviesListPresenter;
import isel.pdm.yamda.presentation.presenter.SoonMoviesListPresenter;
import isel.pdm.yamda.presentation.presenter.TopMoviesListPresenter;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 */
public class MovieListService extends IntentService {

    public final String TAG = "DEBUG_" + getClass().getSimpleName();

    public static final String ID = MovieListService.class.getName();

    private static final String PAGE = MovieListService.class.getName() + ".id";

    public static final String DATA = "data_ok";

    public static final String MOVIES_PARAM = "movies_parameter";

    public static final String THEATERS_NOTIFICATION = "isel.pdm.yamda.data.handlers.service.MovieListService.theaters";

    public static final String SOON_NOTIFICATION = "isel.pdm.yamda.data.handlers.service.MovieListService.soon";

    public static final String TOP_NOTIFICATION = "isel.pdm.yamda.data.handlers.service.MovieListService.top";

    public MovieListService() {
        super("MovieListService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Intent newIntent = null;
        try {
            String id = intent.getStringExtra(ID);
            int page = intent.getIntExtra(PAGE, 1);

            List<MovieListDetails> movies = null;
            if (id.equals(InTheatersMoviesListPresenter.THEATERS_MOVIE_LIST_TAG)) {
                newIntent = new Intent(THEATERS_NOTIFICATION);
                movies = ((YamdaApplication) getApplication()).getMovieRepository().getTheatersMovies(page);
            } else if (id.equals(SoonMoviesListPresenter.SOON_MOVIE_LIST_TAG)) {
                newIntent = new Intent(SOON_NOTIFICATION);
                movies = ((YamdaApplication) getApplication()).getMovieRepository().getSoonMovies(page);
            } else if (id.equals(TopMoviesListPresenter.TOP_MOVIE_LIST_TAG)) {
                newIntent = new Intent(TOP_NOTIFICATION);
                movies = ((YamdaApplication) getApplication()).getMovieRepository().getTopMovies(page);
            } else {
                throw new ApiFailedGettingDataException("Error getting data");
            }
            newIntent.putExtra(DATA, true);
            newIntent.putExtra(MOVIES_PARAM, (Serializable) movies);
        } catch (ApiFailedGettingDataException e) {
            if (newIntent != null) {
                newIntent.putExtra(DATA, false);
            }
            Log.v(TAG, "Exception! Message: " + e.getMessage());
        }
        sendBroadcast(newIntent);
    }

}
