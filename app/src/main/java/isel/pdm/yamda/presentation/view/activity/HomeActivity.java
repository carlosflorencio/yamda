package isel.pdm.yamda.presentation.view.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import isel.pdm.yamda.R;
import isel.pdm.yamda.presentation.view.activity.common.ToolbarActivity;
import isel.pdm.yamda.presentation.view.fragment.ViewPagerAdapter;
import vendor.SlidingTabLayout;

public class HomeActivity extends ToolbarActivity {

    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_layout);
        this.setUpToolbar();


        String[] titles = {
                getResources().getString(R.string.page_theaters),
                getResources().getString(R.string.page_soon),
                getResources().getString(R.string.page_top)
        };

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles for the Tabs.
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), titles);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(false); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

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
