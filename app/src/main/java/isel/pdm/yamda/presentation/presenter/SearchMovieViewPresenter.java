package isel.pdm.yamda.presentation.presenter;

import isel.pdm.yamda.YamdaApplication;
import isel.pdm.yamda.model.repository.IMovieRepository;
import isel.pdm.yamda.presentation.presenter.common.MovieListablePresenter;
import isel.pdm.yamda.presentation.view.activity.SearchableActivity;

/**
 * Search presenter
 */
public class SearchMovieViewPresenter extends MovieListablePresenter {

    private String query;

    public SearchMovieViewPresenter(SearchableActivity activity, String query) {
        super(activity, activity.getListView());
        this.query = query;
        askForData();
    }

    private void askForData() {
        IMovieRepository repo = ((YamdaApplication)this.activity.getApplication()).getMovieRepository();

        this.showLoading();
        repo.setMovieSearch(this, this.query, 1);
    }



}
