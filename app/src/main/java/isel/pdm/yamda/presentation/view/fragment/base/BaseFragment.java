package isel.pdm.yamda.presentation.view.fragment.base;


import android.os.Bundle;

import isel.pdm.yamda.presentation.presenter.base.IPresenter;

/**
 * Base Fragment class for every fragment in this application.
 */
public class BaseFragment extends LoggingFragment {

    /**
     * Fragment Presenter
     */
    protected IPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    /*
    |--------------------------------------------------------------------------
    | Control presenter lifecycle
    |--------------------------------------------------------------------------
    */
    @Override
    public void onResume() {
        super.onResume();

        if(this.presenter != null) //Fragment may not have a presenter
            this.presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onResume();

        if(this.presenter != null) //Fragment may not have a presenter
            this.presenter.onPause();
    }

    @Override
    public void onDestroy() {
        super.onResume();

        if(this.presenter != null) //Fragment may not have a presenter
            this.presenter.onDestroy();
    }
}
