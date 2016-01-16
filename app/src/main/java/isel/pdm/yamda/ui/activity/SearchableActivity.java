package isel.pdm.yamda.ui.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import isel.pdm.yamda.R;
import isel.pdm.yamda.model.Movie;
import isel.pdm.yamda.ui.activity.base.LoadDataActivity;
import isel.pdm.yamda.ui.adapter.MovieRecyclerAdapter;
import isel.pdm.yamda.ui.presenter.SearchMovieViewPresenter;
import isel.pdm.yamda.ui.presenter.base.IPresenter;

/**
 * Activity to display the movie search results
 */
public class SearchableActivity extends LoadDataActivity<List<Movie>> {

    private RecyclerView listView;
    private MovieRecyclerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.listView = (RecyclerView) this.mainView;
        this.setupListView();
        this.setListViewAdapter();

        this.setUpSupportActionBar();

        handleIntent(getIntent());
    }

    @Override
    protected int getLayout() {
        return R.layout.list_movies_layout;
    }


    /**
     * Setup the RecyclerView
     */
    private void setupListView() {
        listView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(mLayoutManager);
    }

    /**
     * Setup the adapter
     */
    private void setListViewAdapter() {
        this.adapter = new MovieRecyclerAdapter(this);

        adapter.setListener(new MovieRecyclerAdapter.IClickListener() {
            @Override
            public void onItemClick(Movie movie) {
                Intent i = MovieActivity.createIntent(SearchableActivity.this, movie.getId());
                SearchableActivity.this.startActivity(i);
            }
        });

        this.listView.setAdapter(adapter);
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

            ((SearchMovieViewPresenter) this.presenter).setQuery(query);
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

    @Override
    protected IPresenter createPresenter() {
        return new SearchMovieViewPresenter(this);
    }

    /**
     * Display the back button
     */
    protected void setUpSupportActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    /*
    |--------------------------------------------------------------------------
    | DataView Methods
    |--------------------------------------------------------------------------
    */
    public void showLoading() {
        this.listView.setVisibility(View.GONE);
        this.loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showResults() {

    }

    @Override
    public void showNoConnection() {

    }

    public void hideLoading() {
        this.loadingView.setVisibility(View.GONE);
        this.listView.setVisibility(View.VISIBLE);
    }

    public void showError(String message) {
        this.hideLoading();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void setData(List<Movie> data) {
        this.hideLoading();
        this.adapter.setData(data);
    }
}
