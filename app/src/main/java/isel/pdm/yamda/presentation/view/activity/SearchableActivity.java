package isel.pdm.yamda.presentation.view.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import isel.pdm.yamda.R;
import isel.pdm.yamda.presentation.presenter.SearchMovieViewPresenter;
import isel.pdm.yamda.presentation.view.activity.base.AbstractBaseActivity;

/**
 * Activity to display the movie search results
 */
public class SearchableActivity extends AbstractBaseActivity {

    private ListView listView;
    private View loadingView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.search_movies);
        this.listView = (ListView) this.findViewById(R.id.list_view_search);
        this.loadingView = this.findViewById(R.id.loading_search);

        setUpSupportActionBar();

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        Log.v("DEBUG_", "new intent: ");
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow

            Log.v("DEBUG_", "Searched: " + query);

            this.presenter = new SearchMovieViewPresenter(this, query);
        }
    }


    /**
     * Set back button
     */
    private void setUpSupportActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.search_results_title);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Set back button close the activity
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

    public ListView getListView() {
        return listView;
    }

    public View getLoadingView() {
        return loadingView;
    }
}
