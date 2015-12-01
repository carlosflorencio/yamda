package isel.pdm.yamda.presentation.view.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import isel.pdm.yamda.R;
import isel.pdm.yamda.presentation.view.activity.base.ToolbarActivity;
import isel.pdm.yamda.presentation.view.fragment.InTheatersMoviesListFragment;
import isel.pdm.yamda.presentation.view.fragment.SoonMoviesListFragment;
import isel.pdm.yamda.presentation.view.fragment.TopMoviesListFragment;

/**
 * Launcher activity that displays the tabs and fragments containing the movies lists
 */
public class HomeActivity extends ToolbarActivity {

    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.home_layout);
        this.setUpToolbar();

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

}
