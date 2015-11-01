package isel.pdm.yamda.presentation.view.fragment;

import isel.pdm.yamda.presentation.presenter.TopMoviesListPresenter;
import isel.pdm.yamda.presentation.view.fragment.base.ListMoviesFragment;

public class TopMoviesListFragment extends ListMoviesFragment {

    public TopMoviesListFragment() {
        this.presenter = new TopMoviesListPresenter();
    }
}
