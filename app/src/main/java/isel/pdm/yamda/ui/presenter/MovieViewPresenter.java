package isel.pdm.yamda.ui.presenter;

import android.os.AsyncTask;
import android.util.Log;

import isel.pdm.yamda.data.exception.FailedGettingDataException;
import isel.pdm.yamda.data.repository.base.ICloudMovieRepository;
import isel.pdm.yamda.data.repository.base.MovieRepositoryFactory;
import isel.pdm.yamda.model.MovieDetails;
import isel.pdm.yamda.ui.contract.ILoadDataView;
import isel.pdm.yamda.ui.fragment.MovieDetailsFragment;
import isel.pdm.yamda.ui.presenter.base.Presenter;

/**
 * Movie view details presenter
 */
public class MovieViewPresenter extends Presenter<MovieDetails> {

    private int id;

    public MovieViewPresenter(final ILoadDataView<MovieDetails> view) {
        super(view);

        ((MovieDetailsFragment)view).setFollowListener(new MovieDetailsFragment.IFollowListener() {
            @Override
            public void storeFollow(int id, boolean follow) {
                if(follow){
                    MovieRepositoryFactory.getLocalRepository(((MovieDetailsFragment) view).getActivity()).followMovie(id);
                } else {
                    MovieRepositoryFactory.getLocalRepository(((MovieDetailsFragment) view).getActivity()).unfollowMovie(id);
                }
            }
        });
    }


    public void setMovieId(int id) {
        this.id = id;
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
            ICloudMovieRepository repo = MovieRepositoryFactory.getCloudRepository();

            try {
                return repo.getMovieById(params[0]);
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
}
