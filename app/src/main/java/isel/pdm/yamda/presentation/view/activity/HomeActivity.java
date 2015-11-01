package isel.pdm.yamda.presentation.view.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

import isel.pdm.yamda.R;
import isel.pdm.yamda.model.entity.Movie;
import isel.pdm.yamda.presentation.presenter.HomeViewPresenter;
import isel.pdm.yamda.presentation.presenter.IPresenter;
import isel.pdm.yamda.presentation.view.activity.common.ToolbarActivity;
import isel.pdm.yamda.presentation.view.activity.contract.IHomeView;
import isel.pdm.yamda.presentation.view.fragment.ViewPagerAdapter;
import vendor.SlidingTabLayout;

public class HomeActivity extends ToolbarActivity implements IHomeView {

    private ViewPager pager;
    private ViewPagerAdapter adapter;
    private SlidingTabLayout tabs;

    private IPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.home_layout);
        this.setUpToolbar();
        this.presenter = new HomeViewPresenter(this);

        this.setTabs();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void setItems(List<Movie> items) {

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }
    @Override
    public void onItemClicked(int position) {

    }

    /**
     * Setup the tabs
     */
    private void setTabs() {
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
