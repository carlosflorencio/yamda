package isel.pdm.yamda.ui.activity;

import android.os.Bundle;

import isel.pdm.yamda.R;
import isel.pdm.yamda.ui.activity.base.ToolbarActivity;

/**
 * Activity to display the app credits
 */
public class AboutActivity extends ToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_about);

        this.enableBackButton();
        toolbar.setTitle(R.string.about);
    }

}
