package isel.pdm.yamda.presentation.view.fragment;

import isel.pdm.yamda.presentation.presenter.MoviesListViewPresenter;

public class TopMoviesListFragment extends ListMoviesFragment {

    public TopMoviesListFragment() {
        this.presenter = new MoviesListViewPresenter("top");
    }
}
