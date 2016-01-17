package isel.pdm.yamda.ui.fragment;

import isel.pdm.yamda.ui.fragment.common.MovieListableFragment;
import isel.pdm.yamda.ui.presenter.TopMoviesListPresenter;
import isel.pdm.yamda.ui.presenter.base.IPresenter;

/**
 * Top movies fragment
 */
public class TopMoviesListFragment extends MovieListableFragment {

    @Override
    protected IPresenter createPresenter() {
        return new TopMoviesListPresenter(this);
    }
}
