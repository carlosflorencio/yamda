package isel.pdm.yamda.ui.presenter;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import isel.pdm.yamda.data.exception.FailedGettingDataException;
import isel.pdm.yamda.data.repository.base.IMovieRepository;
import isel.pdm.yamda.data.repository.base.MovieRepositoryFactory;
import isel.pdm.yamda.model.MovieListDetails;
import isel.pdm.yamda.ui.activity.SearchableActivity;
import isel.pdm.yamda.ui.presenter.base.IPresenter;

/**
 * Search presenter
 */
public class SearchMovieViewPresenter implements IPresenter {

    protected final String TAG = getClass().getSimpleName();
    private SearchableActivity view;
    private String query;

    public SearchMovieViewPresenter(SearchableActivity activity) {
        view = activity;
    }

    /**
     * Setter for the query
     * @param query
     */
    public void setQuery(String query) {
        this.query = query;

        new LoadDataTask().execute(query);
    }

    /**
     * Download movie list in a worker thread using an AsyncTask
     * From cloud repo
     */
    private class LoadDataTask extends AsyncTask<String, Void, List<MovieListDetails>> {

        @Override
        protected List<MovieListDetails> doInBackground(String... params) {
            IMovieRepository repo = MovieRepositoryFactory.getCloudRepository();

            try {
                return repo.getMovieSearch(params[0], 1);
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
        this.view = null;
    }
}
