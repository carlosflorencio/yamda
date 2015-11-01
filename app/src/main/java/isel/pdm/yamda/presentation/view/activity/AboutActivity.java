package isel.pdm.yamda.presentation.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import isel.pdm.yamda.R;
import isel.pdm.yamda.presentation.view.activity.common.BaseActivity;

/**
 * Activity to display the app credits
 */
public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_layout);
    }

}
