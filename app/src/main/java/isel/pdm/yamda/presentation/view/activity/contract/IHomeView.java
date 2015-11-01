package isel.pdm.yamda.presentation.view.activity.contract;

import java.util.List;

import isel.pdm.yamda.model.entity.Movie;

/**
 * Homeview contract
 */
public interface IHomeView {

    /**
     * Set items to list
     * @param items
     */
    public void setItems(List<Movie> items);

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
     * @param position
     */
    public void onItemClicked(int position);
}
