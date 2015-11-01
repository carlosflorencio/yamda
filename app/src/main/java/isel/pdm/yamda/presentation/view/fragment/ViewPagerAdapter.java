package isel.pdm.yamda.presentation.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    /**
     * Argument key to be passed to the MoviesListFragment
     */
    public static final String FRAGMENT_KEY = "TYPE";

    /**
     * This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
     */
    private CharSequence Titles[];

    /**
     * Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
     */
    private int NumbOfTabs;

    /**
     * Tab fragments stored here
     */
    private Fragment[] tabs;


    /**
     * Build a Constructor and assign the passed Values to appropriate values in the class
     * @param fm         Fragment Manager
     * @param mTitles    Titles of the Tabs
     */
    public ViewPagerAdapter(FragmentManager fm, String[] mTitles) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mTitles.length;

        this.setupFragments();
    }

    /**
     * This method returns the fragment for every position in the View Pager
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return this.tabs[0];
            case 1:
                return this.tabs[1];
            case 2:
                return this.tabs[2];
            default:
                throw new IllegalStateException();
        }
    }

    /**
     * This method returns the titles for the Tabs in the Tab Strip
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    /**
     * This method returns the Number of tabs for the tabs Strip
     * @return
     */
    @Override
    public int getCount() {
        return NumbOfTabs;
    }

    /**
     * Populate the fragment array with the 3 fragments
     */
    public void setupFragments() {
        this.tabs = new Fragment[] {
                createMovieTabFragment(Titles[0].toString()),
                createMovieTabFragment(Titles[1].toString()),
                createMovieTabFragment(Titles[2].toString())
        };
    }

    /**
     * Create a ListMoviesFragment of a type
     * @param type
     * @return
     */
    public Fragment createMovieTabFragment(String type) {
        Fragment f = new ListMoviesFragment();
        Bundle b = new Bundle();
        b.putString(FRAGMENT_KEY, type);
        f.setArguments(b);

        return f;
    }
}
