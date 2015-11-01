package isel.pdm.yamda.presentation.view.fragment;

import isel.pdm.yamda.presentation.presenter.MoviesListViewPresenter;

public class InTheatersMoviesListFragment extends ListMoviesFragment {

    public InTheatersMoviesListFragment() {
        this.presenter = new MoviesListViewPresenter("theater");
    }
}
