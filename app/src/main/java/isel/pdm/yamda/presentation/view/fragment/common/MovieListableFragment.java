package isel.pdm.yamda.presentation.view.fragment.common;

import android.view.View;
import android.widget.ListView;

import isel.pdm.yamda.presentation.view.fragment.base.BaseFragment;

public abstract class MovieListableFragment extends BaseFragment {

    /**
     * View Container for holding the view components
     */
    protected View viewContainer;

    /**
     * List view for holding the list
     */
    protected ListView listView;

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
}
