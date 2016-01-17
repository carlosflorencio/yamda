package isel.pdm.yamda.ui.presenter;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import isel.pdm.yamda.data.exception.FailedGettingDataException;
import isel.pdm.yamda.data.repository.base.ICloudMovieRepository;
import isel.pdm.yamda.data.repository.base.MovieRepositoryFactory;
import isel.pdm.yamda.model.Movie;
import isel.pdm.yamda.ui.contract.ILoadDataView;
import isel.pdm.yamda.ui.presenter.base.Presenter;

public class TopMoviesListPresenter extends Presenter<List<Movie>> {

    public TopMoviesListPresenter(
            ILoadDataView<List<Movie>> view) {
        super(view);
    }

    @Override
    public void execute() {
        new LoadDataTask().execute();
    }

    /**
     * Download top movie list in a worker thread using an AsyncTask
     * From cloud repo
     */
    private class LoadDataTask extends AsyncTask<Void, Void, List<Movie>> {

        @Override
        protected List<Movie> doInBackground(Void... params) {
            Log.d(TAG, "doInBackground: Getting movies from the api");
            ICloudMovieRepository repo = MovieRepositoryFactory.getCloudRepository();

            try {
                Thread.sleep(2000);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }


            try {
                return repo.getTopMovies(1);
            } catch (FailedGettingDataException e) {
                Log.d(TAG, "Failed getting data! Error: " + e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Movie> list) {
            super.onPostExecute(list);

            if(list != null)
                view.setData(list);
            else
                view.showNoConnection();
        }
    }
}
