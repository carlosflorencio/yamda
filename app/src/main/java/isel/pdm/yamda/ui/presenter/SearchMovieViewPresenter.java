package isel.pdm.yamda.ui.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.List;

import isel.pdm.yamda.R;
import isel.pdm.yamda.data.services.MovieSearchService;
import isel.pdm.yamda.model.MovieListDetails;
import isel.pdm.yamda.ui.activity.SearchableActivity;
import isel.pdm.yamda.ui.presenter.base.IPresenter;

/**
 * Search presenter
 */
public class SearchMovieViewPresenter implements IPresenter {

    private SearchableActivity view;
    private String query;

    private BroadcastReceiver receiver;

    public SearchMovieViewPresenter(SearchableActivity activity) {
        view = activity;

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getBooleanExtra(MovieSearchService.DATA, false)) {
                    view.setData((List<MovieListDetails>) intent.getSerializableExtra(MovieSearchService.SEARCH_RESULTS));
                } else {
                   view.showError(view.getResources().getString(R.string.no_connection));
                }
            }
        };
    }

    private void askForData() {
        //this.view.showLoading(); progress bar is visible by default in the layout

        Intent intent = new Intent(this.view, MovieSearchService.class);
        intent.putExtra(MovieSearchService.SEARCH_PARAM, query);

        this.view.startService(intent);
    }

    /*
    |--------------------------------------------------------------------------
    | Presenter Lifecycle
    |--------------------------------------------------------------------------
    */
    @Override
    public void onResume() {
        this.view.registerReceiver(receiver, new IntentFilter(MovieSearchService.NOTIFICATION));
    }

    @Override
    public void onPause() {
        this.view.unregisterReceiver(receiver);
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    /**
     * Setter for the query
     * @param query
     */
    public void setQuery(String query) {
        this.query = query;
        this.askForData();
    }
}
