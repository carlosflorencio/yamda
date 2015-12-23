package isel.pdm.yamda.ui.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import isel.pdm.yamda.R;
import isel.pdm.yamda.YamdaApplication;
import isel.pdm.yamda.data.services.MovieDetailsService;
import isel.pdm.yamda.model.MovieDetails;
import isel.pdm.yamda.ui.activity.MovieActivity;
import isel.pdm.yamda.ui.presenter.base.IPresenter;

/**
 * Movie view details presenter
 */
public class MovieViewPresenter implements IPresenter {

    private final BroadcastReceiver receiver;
    private int id;
    private MovieActivity activity;

    public MovieViewPresenter(MovieActivity activity) {
        this.activity = activity;

        activity.setFollowListener(new MovieActivity.FollowListener() {
            @Override
            public void setFollow(int movieId, boolean value) {
                ((YamdaApplication) MovieViewPresenter.this.activity.getApplication())
                        .getMovieRepository().followMovie(movieId);
            }
        });

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getBooleanExtra(MovieDetailsService.DATA, false)) {
                    MovieViewPresenter.this.activity.setFollowedState(
                            intent.getBooleanExtra(MovieDetailsService.FOLLOW, false));
                    MovieViewPresenter.this.activity.setData((MovieDetails) intent
                            .getParcelableExtra(MovieDetailsService.MOVIE_PARAM));
                } else {
                    MovieViewPresenter.this.activity.showError(
                            MovieViewPresenter.this.activity.getResources()
                                                            .getString(R.string.no_connection));
                }
            }
        };
    }

    private void askForData() {
        Intent intent = new Intent(this.activity, MovieDetailsService.class);
        intent.putExtra(MovieDetailsService.ID_PARAM, id);

        activity.startService(intent);
    }

    public void setMovieId(int id) {
        this.id = id;
        this.askForData();
    }

    /*
    |--------------------------------------------------------------------------
    | Presenter Lifecycle
    |--------------------------------------------------------------------------
    */
    @Override
    public void onResume() {
        activity.registerReceiver(receiver, new IntentFilter(MovieDetailsService.NOTIFICATION));
    }

    @Override
    public void onPause() {
        activity.unregisterReceiver(receiver);
    }

    @Override
    public void onDestroy() {
        this.activity = null;
    }
}
