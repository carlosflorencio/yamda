package isel.pdm.yamda.ui.presenter;


import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import isel.pdm.yamda.data.exception.FailedGettingDataException;
import isel.pdm.yamda.data.repository.base.ICloudMovieRepository;
import isel.pdm.yamda.data.repository.base.ILocalMovieRepository;
import isel.pdm.yamda.data.repository.base.MovieRepositoryFactory;
import isel.pdm.yamda.model.Movie;
import isel.pdm.yamda.ui.contract.ILoadDataView;
import isel.pdm.yamda.ui.fragment.common.MovieListableFragment;
import isel.pdm.yamda.ui.presenter.base.ListablePresenter;

/**
 * Presenter class for the InTheatersFragment
 */
public class InTheatersMoviesListPresenter extends ListablePresenter<List<Movie>> {

    public InTheatersMoviesListPresenter(ILoadDataView<List<Movie>> view) {
        super(view);
    }

    @Override
    public void execute() {
        new LoadDataTask().execute();
    }

    @Override
    public void getMoreData(int page) {
        new LoadMorePagesTask().execute(page);
    }

    /**
     * Load movie list in a worker thread using an AsyncTask
     */
    private class LoadDataTask extends AsyncTask<Void, Void, List<Movie>> {

        @Override
        protected List<Movie> doInBackground(Void... params) {
            Log.d(TAG, "doInBackground: Getting movies from content provider");
            ILocalMovieRepository repo = MovieRepositoryFactory.getLocalRepository(view.getViewContext());

            return repo.getTheatersMovies();
        }

        @Override
        protected void onPostExecute(List<Movie> list) {
            super.onPostExecute(list);

            view.setData(list);
        }
    }

    /**
     * Load specific page movie list and adds to the list in a worker thread using an AsyncTask
     */
    private class LoadMorePagesTask extends AsyncTask<Integer, Void, List<Movie>> {

        @Override
        protected List<Movie> doInBackground(Integer... params) {
            Log.d(TAG, "doInBackground: Getting movies from web");
            ICloudMovieRepository repo = MovieRepositoryFactory.getCloudRepository();

            try {
                return repo.getTheatersMovies(params[0]);
            } catch (FailedGettingDataException e) {
                Log.d(TAG, "doInBackground: failed getting data from web");
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Movie> list) {
            super.onPostExecute(list);

            ((MovieListableFragment)view).addMoreData(list);
        }
    }
}
