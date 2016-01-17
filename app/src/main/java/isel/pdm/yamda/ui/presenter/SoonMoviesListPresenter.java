package isel.pdm.yamda.ui.presenter;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import isel.pdm.yamda.data.repository.base.ILocalMovieRepository;
import isel.pdm.yamda.data.repository.base.MovieRepositoryFactory;
import isel.pdm.yamda.model.Movie;
import isel.pdm.yamda.ui.contract.ILoadDataView;
import isel.pdm.yamda.ui.presenter.base.Presenter;

public class SoonMoviesListPresenter extends Presenter<List<Movie>> {


    public SoonMoviesListPresenter(
            ILoadDataView<List<Movie>> view) {
        super(view);
    }

    /**
     * Load movie list in a worker thread using an AsyncTask
     */
    private class LoadDataTask extends AsyncTask<Void, Void, List<Movie>> {
        @Override
        protected List<Movie> doInBackground(Void... params) {
            Log.d(TAG, "doInBackground: Getting movies from content provider");
            ILocalMovieRepository repo = MovieRepositoryFactory.getLocalRepository(view.getViewContext());

            return repo.getSoonMovies();
        }

        @Override
        protected void onPostExecute(List<Movie> list) {
            super.onPostExecute(list);

            view.setData(list);
        }
    }

    @Override
    public void execute() {
        new LoadDataTask().execute();
    }
}
