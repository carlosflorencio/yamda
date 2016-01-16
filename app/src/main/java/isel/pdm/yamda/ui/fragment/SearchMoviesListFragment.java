package isel.pdm.yamda.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import isel.pdm.yamda.ui.fragment.common.MovieListableFragment;
import isel.pdm.yamda.ui.presenter.SearchMovieViewPresenter;
import isel.pdm.yamda.ui.presenter.base.IPresenter;

/**
 * Fragment representing the movies list after search
 */
public class SearchMoviesListFragment extends MovieListableFragment {

    private SearchMovieViewPresenter searchPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View viewContainer = super.onCreateView(inflater, container, savedInstanceState);

        this.searchPresenter = (SearchMovieViewPresenter) this.presenter;

        if(!getArguments().isEmpty()) {
            Log.d(TAG, "onCreateView: temos argumentos! execute presenter!");
            String query = getArguments().getString("query");
            this.searchPresenter.setQuery(query);
            this.searchPresenter.execute();
        } else {
            Log.d(TAG, "onCreateView: nao temos argumentos ainda e o data ta vazio? " + (data == null ? "sim" : "nao"));
        }

        return viewContainer;
    }

    @Override
    protected IPresenter createPresenter() {
        return new SearchMovieViewPresenter(this);
    }
}
