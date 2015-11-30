package isel.pdm.yamda.presentation.view.activity.base;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.SearchView;

import isel.pdm.yamda.R;
import isel.pdm.yamda.presentation.view.activity.AboutActivity;

public abstract class ToolbarActivity extends AbstractBaseActivity {

    protected Toolbar toolbar;
    protected ProgressBar progressBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Call this method at onCreate at the activity
     * Setup the toolbar
     */
    protected void setUpToolbar() {
        this.toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);

        //toolbar.setLogo(R.mipmap.ic_launcher);
        //toolbar.setTitle(R.string.app_name);

        //this.progressBar = (ProgressBar) findViewById(R.id.progress_spinner);
        //progressBar.setVisibility(View.INVISIBLE);
    }

    /**
     * Associate the search widget to the toolbar (action bar)
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Set a call to Searchable activity from the search widget
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        //Expand the search widget
        searchView.setIconifiedByDefault(false);

        return true;
    }

    /**
     * Handle action bar options clicks
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {   // New options to menu
            case R.id.action_about:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
