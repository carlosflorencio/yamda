package isel.pdm.yamda.presentation.view.fragment;

import isel.pdm.yamda.presentation.presenter.InTheatersMoviesListPresenter;
import isel.pdm.yamda.presentation.presenter.base.IPresenter;
import isel.pdm.yamda.presentation.view.fragment.common.MovieListableFragment;

/**
 * Fragment representing the movies list in theaters
 */
public class InTheatersMoviesListFragment extends MovieListableFragment {

    @Override
    protected IPresenter createPresenter() {
        return new InTheatersMoviesListPresenter(this);
    }
}
