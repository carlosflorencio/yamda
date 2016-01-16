package isel.pdm.yamda.ui.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import isel.pdm.yamda.R;
import isel.pdm.yamda.ui.activity.base.LoggingActivity;
import isel.pdm.yamda.ui.fragment.InTheatersMoviesListFragment;
import isel.pdm.yamda.ui.fragment.SoonMoviesListFragment;
import isel.pdm.yamda.ui.fragment.TopMoviesListFragment;

/**
 * Launcher activity that displays the tabs and fragments containing the movies lists
 */
public class HomeActivity extends LoggingActivity {

    protected Toolbar toolbar;
    protected SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_home);

        //set the toolbar
        this.toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);

        this.setPager();
    }

    /**
     * Setup the ViewPager with tabs using SmartTabs Lib
     */
    private void setPager() {
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(),
                FragmentPagerItems.with(this)
                                  .add(R.string.page_theaters,
                                       InTheatersMoviesListFragment.class)
                                  .add(R.string.page_soon,
                                       SoonMoviesListFragment.class)
                                  .add(R.string.page_top,
                                       TopMoviesListFragment.class)
                                  .create());

        //Set the fragments pager
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(adapter);

        //Set the tabs names pager
        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.tabs);
        viewPagerTab.setViewPager(viewPager);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Clear the search query and remove keyboard on back from the search activity
        searchView.setQuery("", false);
        searchView.clearFocus();
    }

    /**
     * Associate the search widget to the toolbar (action bar)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Set a call to Searchable activity from the search widget
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        //Expand the search widget
        searchView.setIconifiedByDefault(false);

        return true;
    }

    /**
     * Handle action bar options clicks
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_about:
                intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.preferences:
                intent = new Intent(this, PreferencesActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
