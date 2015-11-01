package isel.pdm.yamda.presentation.presenter;


import android.util.Log;

import java.util.ArrayList;

import isel.pdm.yamda.presentation.view.activity.contract.IMoviesListView;
import isel.pdm.yamda.presentation.view.entity.MovieView;

public class MoviesListViewPresenter implements IPresenter {

    private IMoviesListView view;
    private String type;

    public MoviesListViewPresenter(String type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     **/
    @Override
    public void onResume() {
        this.view.showProgress();
    }

    /**
     * {@inheritDoc}
     **/
    @Override
    public void onPause() {

    }

    /**
     * {@inheritDoc}
     **/
    @Override
    public void onDestroy() {

    }

    public void setView(IMoviesListView v) {
        this.view = v;
    }

    public void initialize() {
        Log.v("DEBUG_", "initialize " + type);
        this.view.setItems(createList(this.type));
    }

    private ArrayList<MovieView> createList(String type) {
        ArrayList<MovieView> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new MovieView(type, "Released", "2010", null, new String[]{"Action", "Mystery", "Sci-Fi"}, "8.8"));
        }
        return list;
    }
}
