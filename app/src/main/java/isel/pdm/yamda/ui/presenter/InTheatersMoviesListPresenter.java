package isel.pdm.yamda.ui.presenter;


import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import isel.pdm.yamda.data.repository.base.ILocalMovieRepository;
import isel.pdm.yamda.data.repository.base.MovieRepositoryFactory;
import isel.pdm.yamda.model.Movie;
import isel.pdm.yamda.ui.contract.ILoadDataView;
import isel.pdm.yamda.ui.presenter.base.Presenter;

/**
 * Presenter class for the InTheatersFragment
 */
public class InTheatersMoviesListPresenter extends Presenter<List<Movie>> {

    public InTheatersMoviesListPresenter(ILoadDataView<List<Movie>> view) {
        super(view);
    }

    @Override
    public void execute() {
        new LoadDataTask().execute();
    }

    /**
     * Load movie list in a worker thread using an AsyncTask
     */
    private class LoadDataTask extends AsyncTask<Void, Void, List<Movie>> {

        @Override
        protected List<Movie> doInBackground(Void... params) {
            Log.d(TAG, "doInBackground: Getting movies from content provider");
            ILocalMovieRepository repo = MovieRepositoryFactory.getLocalRepository(view.getViewContext());

            try {
                Thread.sleep(2000);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            return repo.getTheatersMovies();
        }

        @Override
        protected void onPostExecute(List<Movie> list) {
            super.onPostExecute(list);

            view.setData(list);
        }
    }
}
