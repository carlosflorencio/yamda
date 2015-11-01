package isel.pdm.yamda.presentation.presenter;

import isel.pdm.yamda.presentation.view.activity.contract.IMoviesListView;

public class MoviesListViewPresenter implements IPresenter {

    private IMoviesListView view;

    public MoviesListViewPresenter(IMoviesListView v) {
        this.view = v;
    }

    /** {@inheritDoc} **/
    @Override
    public void onResume() {
        this.view.showProgress();
    }

    /** {@inheritDoc} **/
    @Override
    public void onPause() {

    }

    /** {@inheritDoc} **/
    @Override
    public void onDestroy() {

    }
}
