package isel.pdm.yamda.ui.fragment;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import isel.pdm.yamda.R;
import isel.pdm.yamda.Utils;
import isel.pdm.yamda.data.handlers.NotificationPublisher;
import isel.pdm.yamda.model.MovieDetails;
import isel.pdm.yamda.ui.activity.MovieActivity;
import isel.pdm.yamda.ui.activity.MovieCreditsActivity;
import isel.pdm.yamda.ui.fragment.base.LoadDataFragment;
import isel.pdm.yamda.ui.presenter.MovieDetailsPresenter;
import isel.pdm.yamda.ui.presenter.base.IPresenter;

/**
 * Movie details fragment, shows all the movie details
 */
public class MovieDetailsFragment extends LoadDataFragment<MovieDetails> {

    public interface IFollowListener{
        void storeFollow(boolean follow);
    }

    private IFollowListener followListener;

    private MovieDetails movie;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View viewContainer = super.onCreateView(inflater, container, savedInstanceState);

        //ask for the movie
        if(!getArguments().isEmpty() && getArguments().getInt("movie_id") != 0) {
            Log.d(TAG, "onCreateView: we have a movie id!");
            ((MovieDetailsPresenter)this.presenter).setMovieId(getArguments().getInt("movie_id"));
            this.presenter.execute();
        }

        return viewContainer;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_movie;
    }

    @Override
    public void setData(MovieDetails data) {
        this.showResults();
        this.movie = data;

        //set toolbar title
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(data.getTitle());

        ImageView backdropView = (ImageView) this.mainView.findViewById(R.id.movie_back_drop_path);
        ImageView imageView = (ImageView) this.mainView.findViewById(R.id.movie_cover);
        TextView title = (TextView) this.mainView.findViewById(R.id.movie_title_details);
        TextView genre = (TextView) this.mainView.findViewById(R.id.movie_genre);
        TextView rating = (TextView) this.mainView.findViewById(R.id.movie_rating);
        TextView runtime = (TextView) this.mainView.findViewById(R.id.movie_runtime);
        TextView releaseYear = (TextView) this.mainView.findViewById(R.id.movie_release_date);
        TextView overview = (TextView) this.mainView.findViewById(R.id.movie_overview);
        Button creditsButton = (Button) this.mainView.findViewById(R.id.credits_button);
        final Button followButton = (Button) this.mainView.findViewById(R.id.follow_button);

        Picasso.with(getContext()).load(movie.getBackdrop()).into(backdropView);
        Picasso.with(getContext()).load(movie.getPoster()).into(imageView);
        title.setText(movie.getTitle());
        genre.setText(Utils.createGenreText(movie.getGenres()));
        rating.setText(this.getString(R.string.row_rating, movie.getRating()));
        runtime.setText(Utils.createRuntimeText(movie.getRuntime()));
        releaseYear.setText(this.getString(R.string.row_released, movie.getReleaseDate()));
        overview.setText(movie.getOverview());

        if (movie.whenIsBeingReleased() > 0) {
            this.mainView.findViewById(R.id.follow_button).setVisibility(View.VISIBLE);
            ((Checkable) followButton).setChecked(movie.isBeingFollowed());
        }

        creditsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = MovieCreditsActivity.createIntent(getActivity(), movie.getId(), movie.getTitle());
                startActivity(i);
            }
        });

        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movie.setBeingFollowed(followButton.isActivated());
            }
        });

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void scheduleNotification(Notification notification) {
        Intent notificationIntent = new Intent(getViewContext(), NotificationPublisher.class);
        notificationIntent
                .putExtra(NotificationPublisher.NOTIFICATION_ID, movie.getId());  // Movie id
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION,
                notification);      // Notification (argument)

        PendingIntent pendingIntent = PendingIntent
                .getBroadcast(getViewContext(), 0, notificationIntent, PendingIntent.FLAG_ONE_SHOT);

//        long futureInMillis = movie.whenIsBeingReleased();
        long futureInMillis = SystemClock.elapsedRealtime() + 5000;   //DEBUG PURPOSES

        ((AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE)).set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis,
                pendingIntent);  // Set an alarm to tick at x time
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private Notification getNotificationReleased() {
        Intent intent = new Intent(getViewContext(),
                MovieActivity.class);      // Activity instantiated after clicking notification
        intent.putExtra(MovieActivity.ID_TAG, movie.getId());       // id of movie

        PendingIntent pIntent = TaskStackBuilder.create(getViewContext())
                .addParentStack(MovieActivity.class)
                .addNextIntent(intent)
                .getPendingIntent(0, PendingIntent.FLAG_ONE_SHOT);

        return new NotificationCompat.Builder(getViewContext())
                .setContentTitle(
                        movie.getTitle())                                  // Title of notification
                .setContentText(getResources().getString(
                        R.string.movie_released))                                  // Text of notification
                .setSmallIcon(
                        R.drawable.yamda)                                 // Icon of notification
                .setContentIntent(pIntent).build();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(followListener != null){
            if(movie.isBeingFollowed()){
                scheduleNotification(getNotificationReleased());
            }
            followListener.storeFollow(movie.isBeingFollowed());
        }
    }

    @Override
    protected IPresenter createPresenter() {
        return new MovieDetailsPresenter(this);
    }

    public void setFollowListener(IFollowListener followListener) {
        this.followListener = followListener;
    }
}
