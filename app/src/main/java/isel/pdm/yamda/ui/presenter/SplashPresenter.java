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
import isel.pdm.yamda.ui.presenter.base.Presenter;

public class SplashPresenter extends Presenter<Void> {

    public SplashPresenter(ILoadDataView<Void> view) {
        super(view);
    }

    @Override
    public void execute() {
        new LoadDataTask().execute();
    }

    /**
     * Download data from the cloud if the local repo (content provider) is empty
     */
    private class LoadDataTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);

            if(success)
                view.setData(null);
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            boolean res = false;
            ILocalMovieRepository localRepo = MovieRepositoryFactory
                    .getLocalRepository(view.getViewContext());

            // TODO: 13/01/2016 check internet connection
            try {
                //Check if local repo has movies, if not download!
                if(!localRepo.hasMovies()) {
                    ICloudMovieRepository cloudRepo = MovieRepositoryFactory.getCloudRepository();
                    List<Movie> now = cloudRepo.getTheatersMovies(1);
                    List<Movie> soon = cloudRepo.getSoonMovies(1);

                    localRepo.insertMovies(now, MoviesContract.MovieEntry.TYPE_NOW);
                    localRepo.insertMovies(soon, MoviesContract.MovieEntry.TYPE_SOON);

                    Log.d(TAG, "Inserted movies list in the local repo!");
                } else {
                    Log.d(TAG, "There are already movies in the repo, no downloads!");
                }

                res = true;
            } catch (FailedGettingDataException e) {
                Log.d(TAG, "Failed getting data! Error: " + e.getMessage());
                view.showNoConnection();
            }

            // Loading data is so fast that we really want a small delay to see the splash
            try {
                Thread.sleep(2000);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            return res;
        }
    }
}
