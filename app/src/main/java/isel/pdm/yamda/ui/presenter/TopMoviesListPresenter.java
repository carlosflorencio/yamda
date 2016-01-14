package isel.pdm.yamda.ui.presenter;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import isel.pdm.yamda.data.exception.FailedGettingDataException;
import isel.pdm.yamda.data.repository.base.IMovieRepository;
import isel.pdm.yamda.data.repository.base.MovieRepositoryFactory;
import isel.pdm.yamda.model.MovieListDetails;
import isel.pdm.yamda.ui.fragment.TopMoviesListFragment;
import isel.pdm.yamda.ui.presenter.base.IPresenter;

public class TopMoviesListPresenter implements IPresenter {

    private final String TAG = getClass().getSimpleName();
    private TopMoviesListFragment view;

    public TopMoviesListPresenter(TopMoviesListFragment fragment) {
        view = fragment;

        new LoadDataTask().execute();
    }

    /**
     * Download top movie list in a worker thread using an AsyncTask
     * From cloud repo
     */
    private class LoadDataTask extends AsyncTask<Void, Void, List<MovieListDetails>> {

        @Override
        protected List<MovieListDetails> doInBackground(Void... params) {
            IMovieRepository repo = MovieRepositoryFactory.getCloudRepository();

            try {
                return repo.getTopMovies(1);
            } catch (FailedGettingDataException e) {
                Log.d(TAG, "Unreachable code!");
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<MovieListDetails> list) {
            super.onPostExecute(list);

            view.setData(list);
        }
    }

    /*
    |--------------------------------------------------------------------------
    | Presenter lifecycle
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
        this.view = null;
    }
}
