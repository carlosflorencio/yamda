package isel.pdm.yamda.presentation.presenter;

import android.os.Handler;

import isel.pdm.yamda.YamdaApplication;
import isel.pdm.yamda.data.repository.IMovieRepositoryAsync;
import isel.pdm.yamda.presentation.presenter.common.MovieListablePresenter;
import isel.pdm.yamda.presentation.view.activity.SearchableActivity;

/**
 * Search presenter
 */
public class SearchMovieViewPresenter extends MovieListablePresenter {

    private String query;

    public SearchMovieViewPresenter(SearchableActivity activity, String query) {
        super(activity, activity.getListView(), activity.getLoadingView());
        this.query = query;
        askForData();
    }

    private void askForData() {
        final IMovieRepositoryAsync repo = ((YamdaApplication)this.activity.getApplication()).getMovieRepository();

        this.showLoading();
        new Handler().postDelayed(new Runnable() { //ONLY FOR TESTING, SHOWING THE LOADER
            public void run() {
                repo.setMovieSearch(SearchMovieViewPresenter.this, SearchMovieViewPresenter.this.query, 1);
            }
        }, 1000);

    }



}
