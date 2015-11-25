package isel.pdm.yamda.presentation.view.activity;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import isel.pdm.yamda.R;
import isel.pdm.yamda.data.image.ImageLoader;
import isel.pdm.yamda.model.entity.Genre;
import isel.pdm.yamda.model.entity.MovieDetails;
import isel.pdm.yamda.presentation.presenter.MovieViewPresenter;
import isel.pdm.yamda.presentation.view.activity.base.BaseActivity;

/**
 * Activity to display the movie details
 */
public class MovieActivity extends BaseActivity {

    public static final String ID_TAG = "movie_id";

    private boolean following = false;

    private ImageLoader imageLoader;
    private View movieView;
    private View loadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.movie_layout2);

        this.imageLoader = new ImageLoader(getApplicationContext());
        this.movieView = this.findViewById(R.id.movie_view);
        this.loadingView = this.findViewById(R.id.loading_movie);

        final int movieId = getIntent().getExtras().getInt(ID_TAG);
        this.presenter = new MovieViewPresenter(this, movieId);
        findViewById(R.id.follow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (following) {
                    MovieActivity.this.showToastMessage("You are no longer following this movie");
                    following = false;
                } else {
                    MovieActivity.this.showToastMessage("You are now following this movie");
                    following = true;
                    MovieActivity.this.setPendingIntent(movieId);
                }
                v.setSelected(true);
            }
        });

        setUpSupportActionBar();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setPendingIntent(int id) {
        Intent intent = new Intent(this, MovieActivity.class);
        intent.putExtra(MovieActivity.ID_TAG, id);

        PendingIntent pIntent = PendingIntent.getActivity(this,
                (int) System.currentTimeMillis(),
                intent,
                PendingIntent.FLAG_ONE_SHOT);

        Notification n = new Notification.Builder(this)
                .setContentTitle("Test Notification")
                .setContentText("You clicked on movie id: " + id)
                .setSmallIcon(R.drawable.yamda)
                .setContentIntent(pIntent)
                .setAutoCancel(true).build();

        NotificationManager notificationManager = ((NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE));

        notificationManager.notify(id, n);
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
            // Back Button
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

    public void updateView(MovieDetails movieView) {
        ImageView imageView = (ImageView) findViewById(R.id.cover);
        TextView title = (TextView) findViewById(R.id.title);
        TextView genre = (TextView) findViewById(R.id.genre);
        TextView rating = (TextView) findViewById(R.id.rating);
        TextView voteCount = (TextView) findViewById(R.id.vote_count);
        TextView runtime = (TextView) findViewById(R.id.runtime);
        TextView releaseYear = (TextView) findViewById(R.id.release_year);
        TextView overview = (TextView) findViewById(R.id.overview);


        imageLoader.DisplayImage(movieView.getPoster(), imageView);
        title.setText(movieView.getTitle());
        genre.setText(createGenreText(movieView.getGenres()));
        rating.setText(String.valueOf(movieView.getRating()));
        voteCount.setText(String.valueOf(movieView.getVoteCount()));
        runtime.setText(createRuntimeText(movieView.getRuntime()));
        releaseYear.setText(movieView.getRelease_date());
        overview.setText(movieView.getOverview());
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
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < genres.length; i++) {
            stringBuffer.append(genres[i].getName());
            if (i < genres.length - 1) {
                stringBuffer.append(", ");
            }
        }
        return stringBuffer.toString();
    }
}
