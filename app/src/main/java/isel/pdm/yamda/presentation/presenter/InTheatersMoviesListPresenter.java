package isel.pdm.yamda.presentation.presenter;


import android.os.AsyncTask;
import android.os.Handler;

import java.util.List;

import isel.pdm.yamda.YamdaApplication;
import isel.pdm.yamda.data.exception.ApiFailedGettingDataException;
import isel.pdm.yamda.data.repository.IMovieRepository;
import isel.pdm.yamda.model.entity.MovieListDetails;
import isel.pdm.yamda.presentation.presenter.common.MovieListablePresenter;
import isel.pdm.yamda.presentation.view.fragment.InTheatersMoviesListFragment;

public class InTheatersMoviesListPresenter extends MovieListablePresenter {

    public InTheatersMoviesListPresenter(InTheatersMoviesListFragment fragment) {
        super(fragment.getActivity(), fragment.getListView(), fragment.getLoadingView());

        this.askForData();
    }

    private void askForData() {
        final IMovieRepository repo = ((YamdaApplication)this.activity.getApplication()).getMovieRepository();

        this.showLoading();
//        new Handler().postDelayed(new Runnable() { //ONLY FOR TESTING, SHOWING THE LOADER
//            public void run() {
//                try {
//                    InTheatersMoviesListPresenter.this.setData(repo.getTheatersMovies(1));
//                } catch (ApiFailedGettingDataException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, 1000);

        new AsyncTask<Void, Void, List<MovieListDetails>>() {
            @Override
            protected List<MovieListDetails> doInBackground(Void... params) {
                try {
                    List<MovieListDetails> data = repo.getTheatersMovies(1);

                    return data;
                } catch (ApiFailedGettingDataException e) {
                    e.printStackTrace();
                }

                return null;
            }

            protected void onPostExecute(List<MovieListDetails> result) {
                InTheatersMoviesListPresenter.this.setData(result);
            }
        }.execute();

    }

}
