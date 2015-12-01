package isel.pdm.yamda.presentation.view.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import isel.pdm.yamda.R;
import isel.pdm.yamda.presentation.view.activity.base.ToolbarActivity;
import isel.pdm.yamda.presentation.view.component.ViewPagerAdapter;
import vendor.SlidingTabLayout;

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
     * Setup the ViewPager
     */
    private void setPager() {
        String[] titles = {
                getResources().getString(R.string.page_theaters),
                getResources().getString(R.string.page_soon),
                getResources().getString(R.string.page_top)
        };

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles for the Tabs.
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), titles);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        this.setTabs();
    }

    /**
     * Setup the tabs in the pager-
     */
    private void setTabs() {
        // Assiging the Sliding Tab Layout View
        SlidingTabLayout tabs = (SlidingTabLayout) findViewById(R.id.tabs);

        // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width
        tabs.setDistributeEvenly(false);

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.colorAccent);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);
    }
}
