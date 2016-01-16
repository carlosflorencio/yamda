package isel.pdm.yamda.ui.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import isel.pdm.yamda.R;
import isel.pdm.yamda.ui.activity.base.LoggingActivity;
import isel.pdm.yamda.ui.fragment.SearchMoviesListFragment;

/**
 * Activity to display the movie search results
 */
public class SearchableActivity extends LoggingActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.toolbar_content);

        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);

        toolbar.setTitle(R.string.search_results_title);
        this.setSupportActionBar(toolbar);

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        Log.v(TAG, "new search handle intent!");
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow

            Log.v(TAG, "Searched: " + query);

            // Create a new Fragment to be placed in the activity layout
            SearchMoviesListFragment firstFragment = new SearchMoviesListFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            Bundle b = new Bundle();
            b.putString("query", query);
            firstFragment.setArguments(b);

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                                       .add(R.id.content, firstFragment).commit();
        }
    }

    /**
     * Set back button close the activity
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Back Button
            case android.R.id.home:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}
