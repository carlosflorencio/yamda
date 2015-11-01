package isel.pdm.yamda.presentation.view.fragment.common;

import android.os.Bundle;

import isel.pdm.yamda.presentation.navigator.Navigator;


public class BaseFragment extends LoggingFragment {

    /**
     * Manage navigation between Fragments
     */
    protected Navigator navigator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.navigator = new Navigator(); //make singleton? What about DI?
    }
}
