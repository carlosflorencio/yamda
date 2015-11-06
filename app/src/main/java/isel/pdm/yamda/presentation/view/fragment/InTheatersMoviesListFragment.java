package isel.pdm.yamda.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import isel.pdm.yamda.R;
import isel.pdm.yamda.presentation.presenter.InTheatersMoviesListPresenter;
import isel.pdm.yamda.presentation.view.fragment.common.MovieListableFragment;

public class InTheatersMoviesListFragment extends MovieListableFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.viewContainer = inflater.inflate(R.layout.home_tab, container, false);
        this.listView = ((ListView) this.viewContainer.findViewById(R.id.list_view));
        this.presenter = new InTheatersMoviesListPresenter(this);

        return viewContainer;
    }
}
