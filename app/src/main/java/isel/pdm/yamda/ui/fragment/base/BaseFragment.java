package isel.pdm.yamda.ui.fragment.base;


import android.os.Bundle;

import isel.pdm.yamda.ui.presenter.base.IPresenter;

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

        //Fragment may not have a presenter
        if(this.presenter != null)
            this.presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onResume();

        //Fragment may not have a presenter
        if(this.presenter != null)
            this.presenter.onPause();
    }

    @Override
    public void onDestroy() {
        super.onResume();

        //Fragment may not have a presenter
        if(this.presenter != null)
            this.presenter.onDestroy();
    }
}
