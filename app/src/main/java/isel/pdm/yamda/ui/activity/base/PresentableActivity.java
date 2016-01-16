package isel.pdm.yamda.ui.activity.base;

import android.os.Bundle;

import isel.pdm.yamda.ui.presenter.base.IPresenter;

/**
 * Presentable Activity class for every class in this Application with a presenter
 */
public abstract class PresentableActivity extends LoggingActivity {

    /**
     * The Activity presenter for handling data
     */
    protected IPresenter presenter;

    /**
     * Method that return the presenter for the activity
     * Must be called in the concrete implementation
     */
    protected abstract IPresenter createPresenter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (this.presenter == null)
            this.presenter = this.createPresenter();
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
