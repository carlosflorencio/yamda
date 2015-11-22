package isel.pdm.yamda.presentation.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.List;

import isel.pdm.yamda.data.handlers.MovieListService;
import isel.pdm.yamda.model.entity.MovieListDetails;
import isel.pdm.yamda.presentation.presenter.common.MovieListablePresenter;
import isel.pdm.yamda.presentation.view.fragment.TopMoviesListFragment;

public class TopMoviesListPresenter extends MovieListablePresenter {

    public static final String TOP_MOVIE_LIST_TAG = TopMoviesListPresenter.class.getSimpleName();

    private final BroadcastReceiver receiver;

    public TopMoviesListPresenter(TopMoviesListFragment fragment) {
        super(fragment.getActivity(), fragment.getListView(), fragment.getLoadingView());

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                setData((List<MovieListDetails>) intent.getSerializableExtra(MovieListService.MOVIES_PARAM));
            }
        };

        this.askForData();
    }

    private void askForData() {
        this.showLoading();

        Intent intent = new Intent(activity, MovieListService.class);
        intent.putExtra(MovieListService.ID, TOP_MOVIE_LIST_TAG);
        activity.startService(intent);
    }

    @Override
    public void onResume() {
        activity.registerReceiver(receiver, new IntentFilter(MovieListService.TOP_NOTIFICATION));
    }

    @Override
    public void onPause() {
        activity.unregisterReceiver(receiver);
    }
}
