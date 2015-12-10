package isel.pdm.yamda.presentation.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.List;

import isel.pdm.yamda.R;
import isel.pdm.yamda.data.handlers.service.MovieListService;
import isel.pdm.yamda.model.entity.MovieListDetails;
import isel.pdm.yamda.presentation.presenter.common.MovieListablePresenter;
import isel.pdm.yamda.presentation.view.fragment.SoonMoviesListFragment;

public class SoonMoviesListPresenter extends MovieListablePresenter {

    public static final String SOON_MOVIE_LIST_TAG = SoonMoviesListPresenter.class.getSimpleName();

    private final BroadcastReceiver receiver;

    public SoonMoviesListPresenter(SoonMoviesListFragment fragment) {
        super(fragment.getActivity(), fragment.getListView(), fragment.getLoadingView());

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getBooleanExtra(MovieListService.DATA, false)) {
                    setData((List<MovieListDetails>) intent.getSerializableExtra(MovieListService.MOVIES_PARAM));
                } else {
                    SoonMoviesListPresenter.this.showError(SoonMoviesListPresenter.this.activity.getResources().getString(R.string.no_connection));
                }
            }
        };

        this.askForData();
    }

    private void askForData() {
        this.showLoading();

        Intent intent = new Intent(activity, MovieListService.class);
        intent.putExtra(MovieListService.ID, SOON_MOVIE_LIST_TAG);
        activity.startService(intent);
    }

    @Override
    public void onResume() {
        activity.registerReceiver(receiver, new IntentFilter(MovieListService.SOON_NOTIFICATION));
    }

    @Override
    public void onPause() {
        activity.unregisterReceiver(receiver);
    }
}
