package isel.pdm.yamda.presentation.view.fragment.base;


import android.os.Bundle;
import android.widget.Toast;

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

    /**
     * Shows a {@link android.widget.Toast} message.
     *
     * @param message An string representing a message to be shown.
     */
    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
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
