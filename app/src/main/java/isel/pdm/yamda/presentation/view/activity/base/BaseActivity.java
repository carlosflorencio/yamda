package isel.pdm.yamda.presentation.view.activity.base;

import android.os.Bundle;
import android.widget.Toast;

import isel.pdm.yamda.presentation.presenter.base.IPresenter;

/**
 * Base Activity class for every class in this Application
 */
public abstract class BaseActivity extends LoggingActivity {

    /**
     * The Activity presenter who should manage the display
     */
    protected IPresenter presenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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
