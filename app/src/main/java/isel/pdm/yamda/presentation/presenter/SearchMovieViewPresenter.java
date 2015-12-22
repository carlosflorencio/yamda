package isel.pdm.yamda.presentation.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.List;

import isel.pdm.yamda.R;
import isel.pdm.yamda.data.services.MovieSearchService;
import isel.pdm.yamda.model.entity.MovieListDetails;
import isel.pdm.yamda.presentation.presenter.common.MovieListablePresenter;
import isel.pdm.yamda.presentation.view.activity.SearchableActivity;

/**
 * Search presenter
 */
public class SearchMovieViewPresenter extends MovieListablePresenter {

    private String query;

    private BroadcastReceiver receiver;

    public SearchMovieViewPresenter(SearchableActivity activity, String query) {
        super(activity, activity.getListView(), activity.getLoadingView());
        this.query = query;

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getBooleanExtra(MovieSearchService.DATA, false)) {
                    setData((List<MovieListDetails>) intent.getSerializableExtra(MovieSearchService.SEARCH_RESULTS));
                } else {
                    SearchMovieViewPresenter.this.showError(SearchMovieViewPresenter.this.activity.getResources().getString(R.string.no_connection));
                }
            }
        };

        askForData();
    }

    private void askForData() {
        this.showLoading();

        Intent intent = new Intent(this.activity, MovieSearchService.class);
        intent.putExtra(MovieSearchService.SEARCH_PARAM, query);

        this.activity.startService(intent);
    }

    /*
    |--------------------------------------------------------------------------
    | Presenter Lifecycle
    |--------------------------------------------------------------------------
    */
    @Override
    public void onResume() {
        activity.registerReceiver(receiver, new IntentFilter(MovieSearchService.NOTIFICATION));
    }

    @Override
    public void onPause() {
        activity.unregisterReceiver(receiver);
    }

    @Override
    public void onDestroy() {

    }



}
