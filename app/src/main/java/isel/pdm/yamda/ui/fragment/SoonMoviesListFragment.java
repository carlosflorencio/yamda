package isel.pdm.yamda.ui.fragment;

import isel.pdm.yamda.ui.fragment.common.MovieListableFragment;
import isel.pdm.yamda.ui.presenter.SoonMoviesListPresenter;
import isel.pdm.yamda.ui.presenter.base.IPresenter;

/**
 * Soon movies fragment
 */
public class SoonMoviesListFragment extends MovieListableFragment {

    @Override
    protected IPresenter createPresenter() {
        return new SoonMoviesListPresenter(this);
    }
}
