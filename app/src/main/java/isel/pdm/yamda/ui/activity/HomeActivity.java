package isel.pdm.yamda.ui.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import isel.pdm.yamda.R;
import isel.pdm.yamda.ui.activity.base.ToolbarActivity;
import isel.pdm.yamda.ui.fragment.InTheatersMoviesListFragment;
import isel.pdm.yamda.ui.fragment.SoonMoviesListFragment;
import isel.pdm.yamda.ui.fragment.TopMoviesListFragment;

/**
 * Launcher activity that displays the tabs and fragments containing the movies lists
 */
public class HomeActivity extends ToolbarActivity implements ViewPager.OnPageChangeListener {

    protected SearchView searchView;
    protected ViewPager viewPager;
    protected int tabPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setPager();
    }

    @Override
    protected int getToolbarLayout() {
        return R.layout.activity_home;
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
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(adapter);

        //Set the tabs names pager
        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.tabs);
        viewPagerTab.setViewPager(viewPager);
        viewPagerTab.setOnPageChangeListener(this);
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

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d(TAG, "onPageSelected: " + position);
        tabPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onDestroy() {


        super.onDestroy();
    }

    /*
   |--------------------------------------------------------------------------
   | Save state
   |--------------------------------------------------------------------------
   */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        int pos = savedInstanceState.getInt("tab_pos");
        //this.viewPager.setCurrentItem(pos);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //Clear the search query and remove keyboard on back from the search activity
        searchView.setQuery("", false);
        searchView.clearFocus();

        outState.putInt("tab_pos", tabPosition);

        super.onSaveInstanceState(outState);
    }
}
