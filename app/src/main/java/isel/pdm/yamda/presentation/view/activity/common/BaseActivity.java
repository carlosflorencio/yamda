package isel.pdm.yamda.presentation.view.activity.common;

import android.os.Bundle;

import isel.pdm.yamda.presentation.navigator.Navigator;
import isel.pdm.yamda.presentation.presenter.base.IPresenter;

/**
 * Base Activity class for every class in this Application
 */
public abstract class BaseActivity extends LoggingActivity {

    /**
     * Manage navigation between Activities
     */
    protected Navigator navigator;

    /**
     * The Activity presenter who should manage the display
     */
    protected IPresenter presenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.navigator = new Navigator(); //make singleton? What about DI?
    }

    public Navigator getNavigator() {
        return this.navigator;
    }

    /*
    |--------------------------------------------------------------------------
    | Lifecycle
    |--------------------------------------------------------------------------
    */
    @Override
    public void onResume() {
        super.onResume();

        if(this.presenter != null) //Activity may not have a presenter
            this.presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if(this.presenter != null) //Activity may not have a presenter
            this.presenter.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(this.presenter != null) //Activity may not have a presenter
            this.presenter.onDestroy();
    }
}
