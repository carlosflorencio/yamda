package isel.pdm.yamda.ui.presenter;


import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import isel.pdm.yamda.data.exception.FailedGettingDataException;
import isel.pdm.yamda.data.repository.base.ILocalMovieRepository;
import isel.pdm.yamda.data.repository.base.MovieRepositoryFactory;
import isel.pdm.yamda.model.MovieListDetails;
import isel.pdm.yamda.ui.fragment.InTheatersMoviesListFragment;
import isel.pdm.yamda.ui.presenter.base.IPresenter;

/**
 * Presenter class for the InTheatersFragment
 */
public class InTheatersMoviesListPresenter implements IPresenter {

    private final String TAG = getClass().getSimpleName();
    private InTheatersMoviesListFragment view;

    /**
     * Constructs a new instance of InTheatersMoviesListPresenter given the fragment
     * @param fragment
     */
    public InTheatersMoviesListPresenter(InTheatersMoviesListFragment fragment) {
        view = fragment;

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
                return repo.getTheatersMovies(1);
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
