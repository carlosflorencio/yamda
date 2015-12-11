package isel.pdm.yamda.presentation.view.activity.base;

import android.support.v7.app.ActionBar;
import android.widget.Toast;

import isel.pdm.yamda.presentation.presenter.base.IPresenter;

/**
 * Base Activity class for every class in this Application
 */
public abstract class AbstractBaseActivity extends LoggingActivity {

    /**
     * The Activity presenter who should manage the display
     */
    protected IPresenter presenter;

    /**
     * Shows a {@link android.widget.Toast} message.
     *
     * @param message An string representing a message to be shown.
     */
    protected void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /*
    |--------------------------------------------------------------------------
    | Lifecycle
    |--------------------------------------------------------------------------
    */
    @Override
    public void onResume() {
        super.onResume();

        //Activity may not have a presenter
        if(this.presenter != null)
            this.presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();

        //Activity may not have a presenter
        if(this.presenter != null)
            this.presenter.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //Activity may not have a presenter
        if(this.presenter != null)
            this.presenter.onDestroy();
    }

    /**
     * Display the back button
     */
    protected void setUpSupportActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
