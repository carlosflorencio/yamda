package isel.pdm.yamda.presentation.view.activity;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import isel.pdm.yamda.R;
import isel.pdm.yamda.model.repository.IMovieRepository;
import isel.pdm.yamda.presentation.creator.DataFactory;
import isel.pdm.yamda.presentation.navigator.Navigator;
import isel.pdm.yamda.presentation.presenter.SearchMovieViewPresenter;
import isel.pdm.yamda.presentation.presenter.base.MoviesListViewPresenter;
import isel.pdm.yamda.presentation.view.activity.common.BaseActivity;
import isel.pdm.yamda.presentation.view.adapter.LazyAdapter;
import isel.pdm.yamda.presentation.view.contract.ILoadDataView;
import isel.pdm.yamda.presentation.view.entity.MovieView;

/**
 * Activity to display the movie search results
 */
public class SearchableActivity extends BaseActivity implements ILoadDataView<List<MovieView>>, AdapterView.OnItemClickListener {

    private SearchMovieViewPresenter presenter;
    private List<MovieView> list;
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_movies);

        setUpSupportActionBar();

        this.listView = ((ListView) findViewById(R.id.list_view_search));
        this.presenter = new SearchMovieViewPresenter();
        this.presenter.setView(this);

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

            this.presenter.setQuery(query);
            this.presenter.initialize();
        }
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void setData(List<MovieView> data) {
        this.list = data;
        setListViewAdapter();
    }

    private void setListViewAdapter() {
        this.listView.setAdapter(new LazyAdapter(this, this.list));
        this.listView.setOnItemClickListener(this);
    }

    public void onItemClicked(MovieView movieView) {
        navigator.navigateToMovieDetails(this, movieView);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MovieView movie = ((MovieView) parent.getAdapter().getItem(position));

        this.onItemClicked(movie);
    }

    private void setUpSupportActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.search_results_title);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

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
