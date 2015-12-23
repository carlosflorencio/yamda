package isel.pdm.yamda.ui.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.List;

import isel.pdm.yamda.R;
import isel.pdm.yamda.data.services.ListService;
import isel.pdm.yamda.data.services.lists.TopListService;
import isel.pdm.yamda.model.MovieListDetails;
import isel.pdm.yamda.ui.fragment.TopMoviesListFragment;
import isel.pdm.yamda.ui.presenter.base.IPresenter;

public class TopMoviesListPresenter implements IPresenter {

    private final BroadcastReceiver receiver;
    private TopMoviesListFragment view;

    public TopMoviesListPresenter(TopMoviesListFragment fragment) {
        view = fragment;

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getBooleanExtra(ListService.DATA, false)) {
                    view.setData((List<MovieListDetails>) intent.getSerializableExtra(ListService.MOVIES_PARAM));
                } else {
                   view.showError(view.getActivity().getResources().getString(R.string.no_connection));
                }
            }
        };

        this.askForData();
    }

    private void askForData() {
        //this.view.showLoading(); progress bar is visible by default in the layout

        Intent intent = new Intent(this.view.getActivity(), TopListService.class);
        this.view.getActivity().startService(intent);
    }

    @Override
    public void onResume() {
        this.view.getActivity().registerReceiver(receiver, new IntentFilter(TopListService.NOTIFICATION));
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
