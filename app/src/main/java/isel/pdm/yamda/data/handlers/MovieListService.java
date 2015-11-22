package isel.pdm.yamda.data.handlers;

import android.app.IntentService;
import android.content.Intent;

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

    public static final String ID = MovieListService.class.getName();

    private static final String PAGE = MovieListService.class.getName() + ".id";

    public static final String MOVIES_PARAM = "movies_parameter";

    public static final String THEATERS_NOTIFICATION = "isel.pdm.yamda.data.handlers.MovieListService.theaters";

    public static final String SOON_NOTIFICATION = "isel.pdm.yamda.data.handlers.MovieListService.soon";

    public static final String TOP_NOTIFICATION = "isel.pdm.yamda.data.handlers.MovieListService.top";

    public MovieListService() {
        super("MovieListService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            try {
                String id = intent.getStringExtra(ID);
                int page = intent.getIntExtra(PAGE, 1);

                List<MovieListDetails> movies = null;
                Intent newIntent = null;
                if (id.equals(InTheatersMoviesListPresenter.THEATERS_MOVIE_LIST_TAG)) {
                    movies = ((YamdaApplication) getApplication()).getMovieRepository().getTheatersMovies(page);
                    newIntent = new Intent(THEATERS_NOTIFICATION);
                } else if (id.equals(SoonMoviesListPresenter.SOON_MOVIE_LIST_TAG)) {
                    movies = ((YamdaApplication) getApplication()).getMovieRepository().getSoonMovies(page);
                    newIntent = new Intent(SOON_NOTIFICATION);
                } else if (id.equals(TopMoviesListPresenter.TOP_MOVIE_LIST_TAG)) {
                    movies = ((YamdaApplication) getApplication()).getMovieRepository().getTopMovies(page);
                    newIntent = new Intent(TOP_NOTIFICATION);
                }
                handleAction(newIntent, movies);
            } catch (ApiFailedGettingDataException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Handle action in the provided background thread with the provided
     * parameters.
     */
    private void handleAction(Intent intent, List<MovieListDetails> movies) {
        intent.putExtra(MOVIES_PARAM, (Serializable) movies);
        sendBroadcast(intent);
    }

}
