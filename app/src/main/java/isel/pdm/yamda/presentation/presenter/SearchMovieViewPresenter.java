package isel.pdm.yamda.presentation.presenter;

import isel.pdm.yamda.model.repository.IMovieRepository;
import isel.pdm.yamda.presentation.creator.DataFactory;
import isel.pdm.yamda.presentation.presenter.common.MovieListablePresenter;
import isel.pdm.yamda.presentation.view.activity.SearchableActivity;

/**
 * Search presenter
 */
public class SearchMovieViewPresenter extends MovieListablePresenter {

    private String query;
    private SearchableActivity activity;

    public SearchMovieViewPresenter(SearchableActivity activity, String query) {
        super(activity, activity.getListView());
        this.query = query;
        askForData();
    }

    private void askForData() {
        DataFactory factory = new DataFactory();
        IMovieRepository repo = factory.getMoviesRepository();

        this.showLoading();
        repo.setMovieSearch(this, this.query, 1);
    }



}
