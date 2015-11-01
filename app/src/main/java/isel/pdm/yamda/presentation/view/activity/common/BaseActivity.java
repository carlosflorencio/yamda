package isel.pdm.yamda.presentation.view.activity.common;

import android.os.Bundle;

import isel.pdm.yamda.presentation.navigator.Navigator;

/**
 * Base Activity class for every class in this Application
 */
public abstract class BaseActivity extends LoggingActivity {

    /**
     * Manage navigation between Activities
     */
    protected Navigator navigator;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.navigator = new Navigator(); //make singleton? What about DI?
    }

    public Navigator getNavigator() {
        return this.navigator;
    }
}
