package isel.pdm.yamda.presentation.presenter.common;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import isel.pdm.yamda.model.entity.MovieListDetails;
import isel.pdm.yamda.presentation.presenter.base.IPresenter;
import isel.pdm.yamda.presentation.view.activity.MovieActivity;
import isel.pdm.yamda.presentation.view.adapter.MovieRecyclerAdapter;
import isel.pdm.yamda.presentation.view.contract.ILoadDataView;

public abstract class MovieListablePresenter implements IPresenter, ILoadDataView<List<MovieListDetails>>{

    protected RecyclerView listView;
    protected View         loadingView;
    protected Activity     activity;

    public MovieListablePresenter(Activity activity, RecyclerView listView, View loading) {
        this.listView = listView;
        this.activity = activity;
        this.loadingView = loading;
    }

    /*
    |--------------------------------------------------------------------------
    | DataView Methods
    |--------------------------------------------------------------------------
    */
    @Override
    public void showLoading() {
        this.listView.setVisibility(View.GONE);
        this.loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        this.loadingView.setVisibility(View.GONE);
        this.listView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String message) {
        this.hideLoading();
    }

    @Override
    public void setData(List<MovieListDetails> data) {
        this.hideLoading();
        this.setListViewAdapter(data);
    }

    /*
    |--------------------------------------------------------------------------
    | Presenter Lifecycle
    |--------------------------------------------------------------------------
    */
    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    /*
    |--------------------------------------------------------------------------
    | Modify the view
    |--------------------------------------------------------------------------
    */
    private void setListViewAdapter(List<MovieListDetails> list) {
        MovieRecyclerAdapter adapter = new MovieRecyclerAdapter(activity);
        adapter.setData(list);
        adapter.setListener(new MovieRecyclerAdapter.IClickListener() {
            @Override
            public void onItemClick(MovieListDetails movie) {
                Intent i = MovieActivity.createIntent(activity, movie.getId());
                activity.startActivity(i);
            }
        });
        this.listView.setAdapter(adapter);
    }



}
