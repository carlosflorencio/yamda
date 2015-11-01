package isel.pdm.yamda.presentation.view.fragment;

import isel.pdm.yamda.presentation.presenter.InTheatersMoviesListPresenter;
import isel.pdm.yamda.presentation.view.fragment.base.ListMoviesFragment;

public class InTheatersMoviesListFragment extends ListMoviesFragment {

    public InTheatersMoviesListFragment() {
        this.presenter = new InTheatersMoviesListPresenter();
    }
}
