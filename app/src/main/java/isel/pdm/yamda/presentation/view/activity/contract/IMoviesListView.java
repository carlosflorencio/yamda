package isel.pdm.yamda.presentation.view.activity.contract;

import java.util.List;

import isel.pdm.yamda.presentation.view.entity.MovieView;

/**
 * Homeview contract
 */
public interface IMoviesListView {

    /**
     * Set items to list
     * @param items
     */
    public void setItems(List<MovieView> items);

    /**
     * Show progress bar when loading data
     */
    public void showProgress();

    /**
     * Hide progress bar when loading data
     */
    public void hideProgress();

    /**
     * Handle item on click
     */
    public void onItemClicked(MovieView mv);
}
