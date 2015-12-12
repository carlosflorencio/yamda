package isel.pdm.yamda.presentation.presenter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;
import android.preference.PreferenceManager;

import java.util.List;

import isel.pdm.yamda.R;
import isel.pdm.yamda.data.handlers.service.ListService;
import isel.pdm.yamda.data.handlers.service.lists.SoonListService;
import isel.pdm.yamda.model.entity.MovieListDetails;
import isel.pdm.yamda.presentation.presenter.common.MovieListablePresenter;
import isel.pdm.yamda.presentation.view.fragment.SoonMoviesListFragment;

public class SoonMoviesListPresenter extends MovieListablePresenter {

    private final BroadcastReceiver receiver;

    public SoonMoviesListPresenter(SoonMoviesListFragment fragment) {
        super(fragment.getActivity(), fragment.getListView(), fragment.getLoadingView());

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getBooleanExtra(ListService.DATA, false)) {
                    setData((List<MovieListDetails>) intent.getSerializableExtra(ListService.MOVIES_PARAM));
                } else {
                    SoonMoviesListPresenter.this.showError(SoonMoviesListPresenter.this.activity.getResources().getString(R.string.no_connection));
                }
            }
        };

        this.askForData();
    }

    private void askForData() {
        this.showLoading();

        Intent intent = new Intent(activity, SoonListService.class);
        activity.startService(intent);

        int days = Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(this.activity).getString("periodicity", "7"));

        //TODO: KNOW THAT IS GOING TO GET FROM WEB
        intent.putExtra(ListService.IGNORE_DISK, true);
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + days * AlarmManager.INTERVAL_DAY,
                days * AlarmManager.INTERVAL_DAY,
                PendingIntent.getService(this.activity, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT));
    }

    @Override
    public void onResume() {
        activity.registerReceiver(receiver, new IntentFilter(SoonListService.NOTIFICATION));
    }

    @Override
    public void onPause() {
        activity.unregisterReceiver(receiver);
    }
}
