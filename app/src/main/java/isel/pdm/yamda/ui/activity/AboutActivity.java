package isel.pdm.yamda.ui.activity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import isel.pdm.yamda.R;
import isel.pdm.yamda.ui.activity.base.ToolbarActivity;

/**
 * Activity to display the app credits
 */
public class AboutActivity extends ToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.enableBackButton();
        toolbar.setTitle(R.string.about);

        ((TextView)findViewById(R.id.linkedin_carlos)).setMovementMethod(LinkMovementMethod.getInstance());
        ((TextView)findViewById(R.id.linkedin_nuno)).setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    protected int getToolbarLayout() {
        return R.layout.activity_about;
    }
}
