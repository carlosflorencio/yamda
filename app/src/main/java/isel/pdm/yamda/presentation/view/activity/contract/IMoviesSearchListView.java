package isel.pdm.yamda.presentation.view.activity.contract;

import java.util.List;

import isel.pdm.yamda.presentation.view.entity.MovieView;

public interface IMoviesSearchListView {

    /**
     * Set items to list
     * @param items
     */
    void setItems(List<MovieView> items);

    /**
     * Handle item on click
     */
    void onItemClicked(MovieView mv);
}
