package isel.pdm.yamda.presentation.view.fragment.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import isel.pdm.yamda.R;
import isel.pdm.yamda.presentation.presenter.base.IPresenter;
import isel.pdm.yamda.presentation.view.fragment.base.BaseFragment;

public abstract class MovieListableFragment extends BaseFragment {

    /** View Container for holding the view components **/
    protected View viewContainer;
    /** List view for holding the list **/
    protected RecyclerView listView;
    /** Loading view showing the progress bar **/
    protected View loadingView;

    private RecyclerView.Adapter       mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.viewContainer = inflater.inflate(R.layout.home_tab, container, false);
        this.listView = (RecyclerView) this.viewContainer.findViewById(R.id.list_view);
        this.loadingView = this.viewContainer.findViewById(R.id.loading_tab);
        this.presenter = this.createPresenter();

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        listView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        listView.setLayoutManager(mLayoutManager);

        return viewContainer;
    }

    /**
     * Method that return the presenter for the fragment
     * @return
     */
    protected abstract IPresenter createPresenter();

    /**
     * Get the view container
     * @return
     */
    public View getViewContainer() {
        return viewContainer;
    }

    /**
     * Return the list view
     * @return
     */
    public RecyclerView getListView() {
        return this.listView;
    }

    /**
     * Return the loading view
     * @return
     */
    public View getLoadingView() {
        return loadingView;
    }
}
