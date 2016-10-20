package isel.pdm.yamda.ui.presenter;


import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import isel.pdm.yamda.data.exception.FailedGettingDataException;
import isel.pdm.yamda.data.provider.MoviesContract;
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
        new LoadDataFromDatabaseTask().execute();
    }

    @Override
    public void getMoreData(int page) {
        new LoadMorePagesTask().execute(page);
    }

    @Override
    public void refresh() {
        new LoadDataTask().execute();
    }

    /**
     * Load movie list in a worker thread using an AsyncTask from Content Provider
     */
    private class LoadDataFromDatabaseTask extends AsyncTask<Void, Void, List<Movie>> {

        @Override
        protected List<Movie> doInBackground(Void... params) {
            Log.d(TAG, "doInBackground: Getting movies from content provider");
            ILocalMovieRepository repo = MovieRepositoryFactory.getLocalRepository(view.getViewContext());


            try {
                Thread.sleep(9000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return repo.getTheatersMovies();
        }

        @Override
        protected void onPostExecute(List<Movie> list) {
            super.onPostExecute(list);

            view.setData(list);
        }
    }

    /**
     * Load movie list in a worker thread using an AsyncTask from Web
     */
    private class LoadDataTask extends AsyncTask<Void, Void, List<Movie>> {

        @Override
        protected List<Movie> doInBackground(Void... params) {
            Log.d(TAG, "doInBackground: Getting movies from content provider");
            ICloudMovieRepository repo = MovieRepositoryFactory.getCloudRepository();

            try {
                return repo.getTheatersMovies(1);
            } catch (FailedGettingDataException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Movie> list) {
            super.onPostExecute(list);
            ILocalMovieRepository localRepo = MovieRepositoryFactory.getLocalRepository(view.getViewContext());

            if(list == null){
                view.showError("No Connection");
                return;
            }
            view.setData(list);
            localRepo.deleteMovies(MoviesContract.MovieEntry.TYPE_NOW);
            if(localRepo.insertMovies(list, MoviesContract.MovieEntry.TYPE_NOW) <= 0){
                Log.d(TAG, "onHandleIntent: (Theaters) nothing has been inserted");
            }
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
