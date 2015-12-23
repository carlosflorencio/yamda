package isel.pdm.yamda.ui.fragment.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import isel.pdm.yamda.R;
import isel.pdm.yamda.model.MovieListDetails;
import isel.pdm.yamda.ui.activity.MovieActivity;
import isel.pdm.yamda.ui.adapter.MovieRecyclerAdapter;
import isel.pdm.yamda.ui.contract.ILoadDataView;
import isel.pdm.yamda.ui.fragment.base.PresentableFragment;

/**
 * Common code for the movies list fragments
 */
public abstract class MovieListableFragment extends PresentableFragment implements
        ILoadDataView<List<MovieListDetails>> {

    protected View viewContainer;
    protected RecyclerView listView;
    protected View loadingView;
    protected MovieRecyclerAdapter adapter;

    List<MovieListDetails> data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);


        this.viewContainer = inflater.inflate(R.layout.home_tab, container, false);
        this.listView = (RecyclerView) this.viewContainer.findViewById(R.id.list_view);
        this.loadingView = this.viewContainer.findViewById(R.id.tab_progress_bar);

        this.setupListView();
        this.setListViewAdapter();

        // switching tabs will cause to onCreateView and onDestroyView to be called!!!!!
        // restored view? do we have data? set it again! and need do save the list position
        // TODO: 23/12/2015 improve this restore method
        if(data != null) {
            this.setData(data);
        }

        return viewContainer;
    }



    /**
     * Setup the RecyclerView
     */
    private void setupListView() {
        listView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        listView.setLayoutManager(mLayoutManager);
    }

    /**
     * Setup the adapter
     */
    private void setListViewAdapter() {
         this.adapter = new MovieRecyclerAdapter(this.getActivity());

        adapter.setListener(new MovieRecyclerAdapter.IClickListener() {
            @Override
            public void onItemClick(MovieListDetails movie) {
                Intent i = MovieActivity.createIntent(getActivity(), movie.getId());
                getActivity().startActivity(i);
            }
        });

        this.listView.setAdapter(adapter);
    }

    /*
    |--------------------------------------------------------------------------
    | DataView Methods
    |--------------------------------------------------------------------------
    */
    public void showLoading() {
        this.listView.setVisibility(View.GONE);
        this.loadingView.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        this.loadingView.setVisibility(View.GONE);
        this.listView.setVisibility(View.VISIBLE);
    }

    public void showError(String message) {
        this.hideLoading();
        Toast.makeText(this.getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    public void setData(List<MovieListDetails> data) {
        this.hideLoading();
        this.data = data;
        this.adapter.setData(data);
    }
}
