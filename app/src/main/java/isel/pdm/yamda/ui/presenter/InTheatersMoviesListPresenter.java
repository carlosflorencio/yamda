package isel.pdm.yamda.ui.presenter;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import java.util.List;

import isel.pdm.yamda.R;
import isel.pdm.yamda.data.services.ListService;
import isel.pdm.yamda.data.services.lists.TheatersListService;
import isel.pdm.yamda.model.MovieListDetails;
import isel.pdm.yamda.ui.fragment.InTheatersMoviesListFragment;
import isel.pdm.yamda.ui.presenter.base.IPresenter;

/**
 * Presenter class for the InTheatersFragment
 */
public class InTheatersMoviesListPresenter implements IPresenter {

    private final String TAG = getClass().getSimpleName();
    private BroadcastReceiver receiver;
    private InTheatersMoviesListFragment view;

    /**
     * Constructs a new instance of InTheatersMoviesListPresenter given the fragment
     * @param fragment
     */
    public InTheatersMoviesListPresenter(InTheatersMoviesListFragment fragment) {
        view = fragment;

        this.subscribe();
        this.askForData();
    }

    /**
     * Register the broadcast receiver
     */
    private void subscribe() {
        this.receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getBooleanExtra(ListService.DATA, false)) {
                    view.setData((List<MovieListDetails>) intent.getSerializableExtra(ListService.MOVIES_PARAM));
                } else {
                    view.showError(view.getActivity().getResources().getString(
                            R.string.no_connection));
                }
            }
        };
    }

    private void askForData() {
        //this.view.showLoading(); progress bar is visible by default in the layout
        Log.v(TAG, "asked for data!");
        Intent intent = new Intent(this.view.getActivity(), TheatersListService.class);
        this.view.getActivity().startService(intent);
    }

    @Override
    public void onResume() {
        this.view.getActivity().registerReceiver(receiver, new IntentFilter(TheatersListService.NOTIFICATION));
    }

    @Override
    public void onPause() {
        this.view.getActivity().unregisterReceiver(receiver);
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}
