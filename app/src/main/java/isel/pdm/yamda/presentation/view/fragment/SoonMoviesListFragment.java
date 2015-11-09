package isel.pdm.yamda.presentation.view.fragment;

import isel.pdm.yamda.presentation.presenter.SoonMoviesListPresenter;
import isel.pdm.yamda.presentation.presenter.base.IPresenter;
import isel.pdm.yamda.presentation.view.fragment.common.MovieListableFragment;

public class SoonMoviesListFragment extends MovieListableFragment {

    @Override
    protected IPresenter createPresenter() {
        return new SoonMoviesListPresenter(this);
    }
}
