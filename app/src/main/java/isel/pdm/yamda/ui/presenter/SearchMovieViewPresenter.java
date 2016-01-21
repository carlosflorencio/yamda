package isel.pdm.yamda.ui.presenter;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import isel.pdm.yamda.data.exception.FailedGettingDataException;
import isel.pdm.yamda.data.repository.base.ICloudMovieRepository;
import isel.pdm.yamda.data.repository.base.MovieRepositoryFactory;
import isel.pdm.yamda.model.Movie;
import isel.pdm.yamda.ui.contract.ILoadDataView;
import isel.pdm.yamda.ui.presenter.base.ListablePresenter;

/**
 * Search presenter
 */
public class SearchMovieViewPresenter extends ListablePresenter<List<Movie>> {

    private String query;

    public SearchMovieViewPresenter(
            ILoadDataView<List<Movie>> view) {
        super(view);
    }

    @Override
    public void getMoreData(int page) {
        view.showError("No More Results");
    }

    @Override
    public void refresh() {
        new LoadDataTask().execute(query);
    }

    /**
     * Setter for the query
     * @param query
     */
    public void setQuery(String query) {
        this.query = query;
    }


    @Override
    public void execute() {
        if(query != null && !query.isEmpty())
            new LoadDataTask().execute(query);
    }

    /**
     * Download movie list in a worker thread using an AsyncTask
     * From cloud repo
     */
    private class LoadDataTask extends AsyncTask<String, Void, List<Movie>> {

        @Override
        protected List<Movie> doInBackground(String... params) {
            Log.d(TAG, "doInBackground: Searching movies from the api");
            ICloudMovieRepository repo = MovieRepositoryFactory.getCloudRepository();

            try {
                return repo.getMovieSearch(params[0], 1);
            } catch (FailedGettingDataException e) {
                Log.d(TAG, "Failed getting data! Error: " + e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Movie> list) {
            super.onPostExecute(list);

            view.setData(list);
        }
    }
}
