package isel.pdm.yamda.presentation.presenter;

import isel.pdm.yamda.YamdaApplication;
import isel.pdm.yamda.data.repository.IMovieRepository;
import isel.pdm.yamda.presentation.presenter.common.MovieListablePresenter;
import isel.pdm.yamda.presentation.view.fragment.SoonMoviesListFragment;

public class SoonMoviesListPresenter extends MovieListablePresenter {

    public SoonMoviesListPresenter(SoonMoviesListFragment fragment) {
        super(fragment.getActivity(), fragment.getListView());

        this.askForData();
    }

    private void askForData() {
        IMovieRepository repo = ((YamdaApplication)this.activity.getApplication()).getMovieRepository();

        this.showLoading();
        repo.setSoonMovies(this, 1);
    }
}
