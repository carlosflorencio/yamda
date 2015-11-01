package isel.pdm.yamda.presentation.view.fragment.common;

import isel.pdm.yamda.presentation.presenter.common.IPresenter;

public abstract class PresentableFragment extends BaseFragment {

    protected IPresenter presenter;

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onResume();
        presenter.onPause();
    }

    @Override
    public void onDestroy() {
        super.onResume();
        presenter.onDestroy();
    }
}
