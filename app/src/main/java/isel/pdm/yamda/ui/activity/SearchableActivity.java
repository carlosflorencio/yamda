package isel.pdm.yamda.ui.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import isel.pdm.yamda.R;
import isel.pdm.yamda.ui.activity.base.AbstractBaseActivity;
import isel.pdm.yamda.ui.presenter.SearchMovieViewPresenter;

/**
 * Activity to display the movie search results
 */
public class SearchableActivity extends AbstractBaseActivity {

    private RecyclerView listView;
    private View         loadingView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_tab);
        this.listView = (RecyclerView) this.findViewById(R.id.list_view);
        this.loadingView = this.findViewById(R.id.loading_tab);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        listView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(mLayoutManager);

        this.setUpSupportActionBar();

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

    public RecyclerView getListView() {
        return listView;
    }

    public View getLoadingView() {
        return loadingView;
    }
}
