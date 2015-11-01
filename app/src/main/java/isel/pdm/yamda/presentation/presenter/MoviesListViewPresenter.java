package isel.pdm.yamda.presentation.presenter;

import android.util.Log;

import java.util.ArrayList;

import isel.pdm.yamda.presentation.view.activity.contract.IMoviesListView;
import isel.pdm.yamda.presentation.view.entity.MovieView;

public class MoviesListViewPresenter implements IPresenter {

    private IMoviesListView view;

    public MoviesListViewPresenter(IMoviesListView v) {
        this.view = v;

        this.view.setItems(createList());
    }

    /** {@inheritDoc} **/
    @Override
    public void onResume() {
        this.view.showProgress();
    }

    /** {@inheritDoc} **/
    @Override
    public void onPause() {

    }

    /** {@inheritDoc} **/
    @Override
    public void onDestroy() {

    }

    private ArrayList<MovieView> createList() {
        Log.v("DEBUG", "createList");
        ArrayList<MovieView> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new MovieView("Inception", "Released", "2010", null, new String[]{"Action", "Mystery", "Sci-Fi"}, "8.8"));
        }
        return list;
    }
}
