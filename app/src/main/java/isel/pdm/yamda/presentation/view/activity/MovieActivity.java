package isel.pdm.yamda.presentation.view.activity;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import isel.pdm.yamda.R;
import isel.pdm.yamda.data.handlers.receiver.NotificationPublisher;
import isel.pdm.yamda.model.entity.Genre;
import isel.pdm.yamda.model.entity.MovieDetails;
import isel.pdm.yamda.presentation.presenter.MovieViewPresenter;
import isel.pdm.yamda.presentation.view.activity.base.AbstractBaseActivity;

/**
 * Activity to display the movie details
 */
public class MovieActivity extends AbstractBaseActivity {

    public static final String ID_TAG = "movie_id";

    private View movieView;
    private View loadingView;

    private boolean following = false;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    private MovieDetails movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.movie_layout3);

        this.movieView = this.findViewById(R.id.movie_view);
        this.loadingView = this.findViewById(R.id.loading_movie);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        int movieId = getIntent().getExtras().getInt(ID_TAG);
        this.presenter = new MovieViewPresenter(this, movieId);

//        this.checkFollow(findViewById(R.id.follow));

        setUpSupportActionBar();
    }

    private void checkFollow(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (following) {
                    showToastMessage("You are no longer following this movie");
                    cancelNotification();
                } else {
                    showToastMessage("You are now following this movie");
                    scheduleNotification(getNotificationReleased());
                }
                following = !following;
            }
        });
    }

    private void cancelNotification() {
        alarmManager.cancel(pendingIntent);
        pendingIntent.cancel();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void scheduleNotification(Notification notification) {
        // Receiver to be activated after a certain time to instantiate notification in argument
        Intent notificationIntent = new Intent(this, NotificationPublisher.class);

        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, movie.getId());  // Movie id
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);      // Notification (argument)

        pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_ONE_SHOT);

        //TODO: time to release date in millis
        long futureInMillis = SystemClock.elapsedRealtime() + 5000;
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);  // Set an alarm to tick at x time
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private Notification getNotificationReleased() {
        Intent intent = new Intent(this, MovieActivity.class);      // Activity instantiated after clicking notification
        intent.putExtra(MovieActivity.ID_TAG, movie.getId());       // id of movie
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        return new Notification.Builder(this)
                .setContentTitle(movie.getTitle())                              // Title of notification
                .setContentText(getResources().getString(R.string.movie_released))    // Text of notification
                .setSmallIcon(R.drawable.yamda)                                 // Icon of notification
                .setContentIntent(pIntent).build();
    }

    /**
     * Display the back button
     */
    private void setUpSupportActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Make the back button close this activity
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    public View getMovieView() {
        return movieView;
    }

    public View getLoadingView() {
        return loadingView;
    }

    public void update(MovieDetails data) {
        this.movie = data;
        updateView();
    }

    private void updateView() {
        ImageView backdropView = (ImageView) findViewById(R.id.backDropPath);
        ImageView imageView = (ImageView) findViewById(R.id.cover);
        TextView title = (TextView) findViewById(R.id.title);
        TextView genre = (TextView) findViewById(R.id.genre);
        TextView rating = (TextView) findViewById(R.id.tagline);
        //TextView voteCount = (TextView) findViewById(R.id.vote_count);
        TextView runtime = (TextView) findViewById(R.id.runtime);
        TextView releaseYear = (TextView) findViewById(R.id.release_date);
        TextView overview = (TextView) findViewById(R.id.overview);


        Picasso.with(this).load(movie.getBackdrop()).into(backdropView);
        Picasso.with(this).load(movie.getPoster()).into(imageView);
        title.setText(movie.getTitle());
        genre.setText(createGenreText(movie.getGenres()));
        rating.setText(this.getString(R.string.row_rating, movie.getRating()));
//        voteCount.setText(String.valueOf(movie.getVoteCount()));
        runtime.setText(createRuntimeText(movie.getRuntime()));
        releaseYear.setText(this.getString(R.string.row_released, movie.getRelease_date()));
        overview.setText(movie.getOverview());

//        if (movieIsAlreadyReleased(movie.getRelease_date()))
//            findViewById(R.id.follow).setVisibility(View.INVISIBLE);
//        else
//            findViewById(R.id.follow).setVisibility(View.VISIBLE);
    }

    private boolean movieIsAlreadyReleased(String release_date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(release_date);
            return Calendar.getInstance().getTime().compareTo(date) >= 0;
        } catch (ParseException e) {
            Log.v(TAG, "Failed to create a date! Message: " + e.getMessage());
        }
        return false;
    }

    /**
     * @param runtime minutes
     * @return
     */
    private String createRuntimeText(int runtime) {
        int hours = runtime / 60;
        int minutes = runtime % 60;
        return hours + "h " + minutes + "m";
    }

    private String createGenreText(Genre[] genres) {
        StringBuilder stringbuilder = new StringBuilder();
        for (int i = 0; i < genres.length; i++) {
            stringbuilder.append(genres[i].getName());
            if (i < genres.length - 1) {
                stringbuilder.append(", ");
            }
        }
        return stringbuilder.toString();
    }

    public static Intent createIntent(Context context, int id) {
        Intent intent = new Intent(context, MovieActivity.class);
        intent.putExtra(ID_TAG, id);

        return intent;
    }
}
