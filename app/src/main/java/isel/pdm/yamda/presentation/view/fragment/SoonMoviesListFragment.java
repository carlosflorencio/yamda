package isel.pdm.yamda.presentation.view.fragment;

import isel.pdm.yamda.presentation.presenter.MoviesListViewPresenter;

public class SoonMoviesListFragment extends ListMoviesFragment {

    public SoonMoviesListFragment() {
        this.presenter = new MoviesListViewPresenter("soon");
    }
}
