package isel.pdm.yamda.presentation.view.activity;

import android.os.Bundle;
import android.view.MenuItem;

import isel.pdm.yamda.presentation.view.activity.base.AbstractBaseActivity;
import isel.pdm.yamda.presentation.view.fragment.PreferencesFragment;

/**
 * Class used to store shared preferences of some details of the aplication
 */
public class PreferencesActivity extends AbstractBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setUpSupportActionBar();
        getFragmentManager().beginTransaction().replace(android.R.id.content, new PreferencesFragment()).commit();
    }

    /**
     * Make the back button close this activity
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}
