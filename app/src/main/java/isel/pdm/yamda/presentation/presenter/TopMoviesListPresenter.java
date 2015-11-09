package isel.pdm.yamda.presentation.presenter;

import isel.pdm.yamda.YamdaApplication;
import isel.pdm.yamda.data.repository.IMovieRepository;
import isel.pdm.yamda.presentation.presenter.common.MovieListablePresenter;
import isel.pdm.yamda.presentation.view.fragment.TopMoviesListFragment;

public class TopMoviesListPresenter extends MovieListablePresenter {

    public TopMoviesListPresenter(TopMoviesListFragment fragment) {
        super(fragment.getActivity(), fragment.getListView());

        this.askForData();
    }

    private void askForData() {
        IMovieRepository repo = ((YamdaApplication)this.activity.getApplication()).getMovieRepository();

        this.showLoading();
        repo.setTopMovies(this, 1);
    }
}
