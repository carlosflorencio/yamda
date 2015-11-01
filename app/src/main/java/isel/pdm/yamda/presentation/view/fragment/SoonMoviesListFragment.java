package isel.pdm.yamda.presentation.view.fragment;

import isel.pdm.yamda.presentation.presenter.SoonMoviesListPresenter;
import isel.pdm.yamda.presentation.view.fragment.base.ListMoviesFragment;

public class SoonMoviesListFragment extends ListMoviesFragment {

    public SoonMoviesListFragment() {
        this.presenter = new SoonMoviesListPresenter();
    }
}
