package isel.pdm.yamda.ui.fragment.base;


import android.os.Bundle;

import isel.pdm.yamda.ui.presenter.base.IPresenter;

/**
 * Presentable Fragment class for every fragment with a presenter in this application.
 */
public abstract class PresentableFragment extends LoggingFragment {

    /**
     * Fragment Presenter
     */
    protected IPresenter presenter;

    /**
     * Method that return the presenter for the fragment
     * Must be called in the concrete implementation
     */
    protected abstract IPresenter createPresenter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (this.presenter == null)
            this.presenter = this.createPresenter();
    }

    public IPresenter getPresenter() {
        return this.presenter;
    }

    /*
    |--------------------------------------------------------------------------
    | Control presenter lifecycle
    |--------------------------------------------------------------------------
    */
    @Override
    public void onResume() {
        super.onResume();

        this.presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();

        this.presenter.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        this.presenter.onDestroy();
        this.presenter = null;
    }
}
