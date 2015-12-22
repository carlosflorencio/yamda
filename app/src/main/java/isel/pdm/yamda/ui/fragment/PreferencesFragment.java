package isel.pdm.yamda.ui.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import isel.pdm.yamda.R;

/**
 * Class used to //TODO: comentary
 */
public class PreferencesFragment extends PreferenceFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

}
