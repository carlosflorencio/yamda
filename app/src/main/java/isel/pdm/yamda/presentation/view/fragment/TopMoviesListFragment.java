package isel.pdm.yamda.presentation.view.fragment;

import isel.pdm.yamda.presentation.presenter.TopMoviesListPresenter;
import isel.pdm.yamda.presentation.presenter.base.IPresenter;
import isel.pdm.yamda.presentation.view.fragment.common.MovieListableFragment;

public class TopMoviesListFragment extends MovieListableFragment {

    @Override
    protected IPresenter createPresenter() {
        return new TopMoviesListPresenter(this);
    }
}
