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
    void setItems(List<MovieView> items);

    /**
     * Show progress bar when loading data
     */
    void showProgress();

    /**
     * Hide progress bar when loading data
     */
    void hideProgress();

    /**
     * Handle item on click
     */
    void onItemClicked(MovieView mv);
}
