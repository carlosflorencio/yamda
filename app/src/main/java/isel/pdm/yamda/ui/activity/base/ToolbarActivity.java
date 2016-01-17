package isel.pdm.yamda.ui.activity.base;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import isel.pdm.yamda.R;

/**
 * Activity with a toolbar
 */
public abstract class ToolbarActivity extends LoggingActivity {

    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(getToolbarLayout());

        toolbar = (Toolbar) this.findViewById(R.id.toolbar);

        toolbar.setTitle(R.string.loading);
        this.setSupportActionBar(toolbar);
    }

    /**
     * The layout that contains the toolbar
     * Override this method if the layout is different
     * Should contain a toolbar with an id of toolbar
     * @return
     */
    protected int getToolbarLayout() {
        return R.layout.toolbar_content;
    }

    /**
     * Show back button
     */
    protected void enableBackButton() {
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Set back button
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Back Button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
