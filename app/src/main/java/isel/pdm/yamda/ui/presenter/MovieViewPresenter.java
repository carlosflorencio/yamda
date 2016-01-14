package isel.pdm.yamda.ui.presenter;

import android.os.AsyncTask;
import android.util.Log;

import isel.pdm.yamda.data.exception.FailedGettingDataException;
import isel.pdm.yamda.data.repository.base.ILocalMovieRepository;
import isel.pdm.yamda.data.repository.base.MovieRepositoryFactory;
import isel.pdm.yamda.model.MovieDetails;
import isel.pdm.yamda.ui.activity.MovieActivity;
import isel.pdm.yamda.ui.presenter.base.IPresenter;

/**
 * Movie view details presenter
 */
public class MovieViewPresenter implements IPresenter {

    protected final String TAG = getClass().getSimpleName();

    private int id;
    private MovieActivity activity;

    public MovieViewPresenter(MovieActivity activity) {
        this.activity = activity;
    }

    public void setMovieId(int id) {
        this.id = id;

        new LoadDataTask().execute(id);
    }

    /**
     * Load movie details in a worker thread using an AsyncTask
     */
    private class LoadDataTask extends AsyncTask<Integer, Void, MovieDetails> {

        @Override
        protected MovieDetails doInBackground(Integer... params) {
            ILocalMovieRepository repo = MovieRepositoryFactory.getLocalRepository(activity);

            try {
                return repo.getMovieById(params[0]);
            } catch (FailedGettingDataException e) {
                Log.d(TAG, "Unreachable code!");
            }

            return null;
        }

        @Override
        protected void onPostExecute(MovieDetails movie) {
            super.onPostExecute(movie);

            activity.setData(movie);
        }
    }

    /*
    |--------------------------------------------------------------------------
    | Presenter Lifecycle
    |--------------------------------------------------------------------------
    */
    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onDestroy() {
        this.activity = null;
    }
}
