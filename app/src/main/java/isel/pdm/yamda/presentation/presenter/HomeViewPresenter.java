package isel.pdm.yamda.presentation.presenter;

import isel.pdm.yamda.presentation.view.activity.contract.IHomeView;

public class HomeViewPresenter implements IPresenter {

    private IHomeView view;

    public HomeViewPresenter(IHomeView v) {
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
