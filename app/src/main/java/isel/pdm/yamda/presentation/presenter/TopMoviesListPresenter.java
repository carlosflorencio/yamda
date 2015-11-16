package isel.pdm.yamda.presentation.presenter;

import android.os.Handler;

import isel.pdm.yamda.YamdaApplication;
import isel.pdm.yamda.data.repository.IMovieRepositoryAsync;
import isel.pdm.yamda.presentation.presenter.common.MovieListablePresenter;
import isel.pdm.yamda.presentation.view.fragment.TopMoviesListFragment;

public class TopMoviesListPresenter extends MovieListablePresenter {

    public TopMoviesListPresenter(TopMoviesListFragment fragment) {
        super(fragment.getActivity(), fragment.getListView(), fragment.getLoadingView());

        this.askForData();
    }

    private void askForData() {
        final IMovieRepositoryAsync repo = ((YamdaApplication)this.activity.getApplication()).getMovieRepository();

        this.showLoading();
        new Handler().postDelayed(new Runnable() { //ONLY FOR TESTING, SHOWING THE LOADER
            public void run() {
                repo.setTopMovies(TopMoviesListPresenter.this, 1);
            }
        }, 1000);
    }
}
