package isel.pdm.yamda.presentation.view.activity;

import android.os.Bundle;

import isel.pdm.yamda.presentation.view.activity.base.AbstractBaseActivity;
import isel.pdm.yamda.presentation.view.fragment.PreferencesFragment;

/**
 * Class used to store shared preferences of some details of the aplication
 */
public class PreferencesActivity extends AbstractBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content, new PreferencesFragment()).commit();
    }

}
