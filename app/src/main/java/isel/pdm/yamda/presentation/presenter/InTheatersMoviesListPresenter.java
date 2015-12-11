package isel.pdm.yamda.presentation.presenter;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.List;

import isel.pdm.yamda.R;
import isel.pdm.yamda.data.handlers.service.ListService;
import isel.pdm.yamda.data.handlers.service.lists.TheatersListService;
import isel.pdm.yamda.model.entity.MovieListDetails;
import isel.pdm.yamda.presentation.presenter.common.MovieListablePresenter;
import isel.pdm.yamda.presentation.view.fragment.InTheatersMoviesListFragment;

/**
 * Presenter class for the InTheatersFragment
 */
public class InTheatersMoviesListPresenter extends MovieListablePresenter {

    private final BroadcastReceiver receiver;

    /**
     * Constructs a new instance of InTheatersMoviesListPresenter given the fragment
     * @param fragment
     */
    public InTheatersMoviesListPresenter(InTheatersMoviesListFragment fragment) {
        super(fragment.getActivity(), fragment.getListView(), fragment.getLoadingView());

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getBooleanExtra(ListService.DATA, false)) {
                    setData((List<MovieListDetails>) intent.getSerializableExtra(ListService.MOVIES_PARAM));
                } else {
                    InTheatersMoviesListPresenter.this.showError(InTheatersMoviesListPresenter.this.activity.getResources().getString(R.string.no_connection));
                }
            }
        };

        this.askForData();
    }

    private void askForData() {
        this.showLoading();

        Intent intent = new Intent(activity, TheatersListService.class);
        activity.startService(intent);
    }

    @Override
    public void onResume() {
        activity.registerReceiver(receiver, new IntentFilter(TheatersListService.NOTIFICATION));
    }

    @Override
    public void onPause() {
        activity.unregisterReceiver(receiver);
    }
}
