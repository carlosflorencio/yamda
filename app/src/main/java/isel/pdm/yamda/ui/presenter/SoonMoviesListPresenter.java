package isel.pdm.yamda.ui.presenter;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import isel.pdm.yamda.data.exception.FailedGettingDataException;
import isel.pdm.yamda.data.repository.base.ILocalMovieRepository;
import isel.pdm.yamda.data.repository.base.MovieRepositoryFactory;
import isel.pdm.yamda.model.MovieListDetails;
import isel.pdm.yamda.ui.fragment.SoonMoviesListFragment;
import isel.pdm.yamda.ui.presenter.base.IPresenter;

public class SoonMoviesListPresenter implements IPresenter {

    private final String TAG = getClass().getSimpleName();
    private SoonMoviesListFragment view;

    public SoonMoviesListPresenter(SoonMoviesListFragment v) {
        this.view = v;

        new LoadDataTask().execute();
    }

    /**
     * Load movie list in a worker thread using an AsyncTask
     */
    private class LoadDataTask extends AsyncTask<Void, Void, List<MovieListDetails>> {

        @Override
        protected List<MovieListDetails> doInBackground(Void... params) {
            ILocalMovieRepository repo = MovieRepositoryFactory.getLocalRepository(view.getContext());

            try {
                return repo.getSoonMovies(1);
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
