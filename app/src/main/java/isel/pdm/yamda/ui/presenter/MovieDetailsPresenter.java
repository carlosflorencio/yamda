package isel.pdm.yamda.ui.presenter;

import android.os.AsyncTask;
import android.util.Log;

import isel.pdm.yamda.data.exception.FailedGettingDataException;
import isel.pdm.yamda.data.repository.base.ICloudMovieRepository;
import isel.pdm.yamda.data.repository.base.ILocalMovieRepository;
import isel.pdm.yamda.data.repository.base.MovieRepositoryFactory;
import isel.pdm.yamda.model.MovieDetails;
import isel.pdm.yamda.ui.contract.ILoadDataView;
import isel.pdm.yamda.ui.presenter.base.Presenter;

/**
 * Movie view details presenter
 */
public class MovieDetailsPresenter extends Presenter<MovieDetails> {

    private int id;

    public MovieDetailsPresenter(final ILoadDataView<MovieDetails> view) {
        super(view);
    }


    public void setMovieId(int id) {
        this.id = id;
    }


    public void storeFollow(boolean follow) {
        new StoreDataTask().execute(follow);
    }

    @Override
    public void execute() {
        new LoadDataTask().execute(id);
    }

    /**
     * Load movie details in a worker thread using an AsyncTask
     */
    private class LoadDataTask extends AsyncTask<Integer, Void, MovieDetails> {

        @Override
        protected MovieDetails doInBackground(Integer... params) {
            Log.d(TAG, "doInBackground: Getting movie from the api");
            ICloudMovieRepository cloudRepo = MovieRepositoryFactory.getCloudRepository();
            ILocalMovieRepository localRepo = MovieRepositoryFactory.getLocalRepository(view.getViewContext());

            try {
                MovieDetails movie = cloudRepo.getMovieById(params[0]);
                movie.setBeingFollowed(localRepo.isBeingFollowed(movie.getId()));
                return movie;
            } catch (FailedGettingDataException e) {
                Log.d(TAG, "Failed getting data! Error: " + e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(MovieDetails movie) {
            super.onPostExecute(movie);

            if(movie != null)
                view.setData(movie);
            else
                view.showNoConnection();
        }
    }

    /**
     * Store movie follow state in a worker thread using an AsyncTask
     */
    private class StoreDataTask extends AsyncTask<Boolean, Void, Void> {

        @Override
        protected Void doInBackground(Boolean... params) {
            Log.d(TAG, "doInBackground: Storing movie follow state");
            ILocalMovieRepository repo = MovieRepositoryFactory.getLocalRepository(view.getViewContext());

            //if not following, begin to follow
            if(params[0]){
                repo.followMovie(id);
            } else {
                repo.unfollowMovie(id);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
