package isel.pdm.yamda.presentation.presenter;

import android.os.Handler;

import isel.pdm.yamda.YamdaApplication;
import isel.pdm.yamda.data.repository.IMovieRepository;
import isel.pdm.yamda.presentation.presenter.common.MovieListablePresenter;
import isel.pdm.yamda.presentation.view.fragment.SoonMoviesListFragment;

public class SoonMoviesListPresenter extends MovieListablePresenter {

    public SoonMoviesListPresenter(SoonMoviesListFragment fragment) {
        super(fragment.getActivity(), fragment.getListView(), fragment.getLoadingView());

        this.askForData();
    }

    private void askForData() {
        final IMovieRepository repo = ((YamdaApplication)this.activity.getApplication()).getMovieRepository();

        this.showLoading();


        new Handler().postDelayed(new Runnable() { //ONLY FOR TESTING, SHOWING THE LOADER
            public void run() {
                repo.setSoonMovies(SoonMoviesListPresenter.this, 1);
            }
        }, 1000);

    }
}
