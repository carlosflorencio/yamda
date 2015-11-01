package isel.pdm.yamda.presentation.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private static final String TITLES_KEY = "TITLES_KEY";

    /**
     * This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
     */
    CharSequence Titles[];

    /**
     * Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
     */
    int NumbOfTabs;


    /**
     * Build a Constructor and assign the passed Values to appropriate values in the class
     * @param fm         Fragment Manager
     * @param mTitles    Titles of the Tabs
     */
    public ViewPagerAdapter(FragmentManager fm, String[] mTitles) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mTitles.length;
    }

    //This method returns the fragment for every position in the View Pager
    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        ListMoviesFragment tab = new ListMoviesFragment();
        switch (position) {
            case 0:
                bundle.putString(TITLES_KEY, Titles[0].toString());
                tab.setArguments(bundle);
                break;
            case 1:
                bundle.putString(TITLES_KEY, Titles[1].toString());
                tab.setArguments(bundle);
                break;
            case 2:
                bundle.putString(TITLES_KEY, Titles[2].toString());
                tab.setArguments(bundle);
                break;
            default:
                throw new IllegalStateException();
        }
        return tab;
    }

    // This method returns the titles for the Tabs in the Tab Strip
    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method returns the Number of tabs for the tabs Strip
    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}
