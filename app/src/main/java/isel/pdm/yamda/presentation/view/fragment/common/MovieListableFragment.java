package isel.pdm.yamda.presentation.view.fragment.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
    protected ListView listView;
    /** Loading view showing the progress bar **/
    protected View loadingView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.viewContainer = inflater.inflate(R.layout.home_tab, container, false);
        this.listView = ((ListView) this.viewContainer.findViewById(R.id.list_view));
        this.loadingView = this.viewContainer.findViewById(R.id.loading_tab);
        this.presenter = this.createPresenter();

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
    public ListView getListView() {
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
