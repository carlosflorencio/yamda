package isel.pdm.yamda.presentation.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.widget.ImageView;
import android.widget.TextView;

import isel.pdm.yamda.R;
import isel.pdm.yamda.data.image.ImageLoader;
import isel.pdm.yamda.presentation.presenter.MovieViewPresenter;
import isel.pdm.yamda.presentation.view.activity.common.BaseActivity;
import isel.pdm.yamda.presentation.view.activity.contract.IMovieView;
import isel.pdm.yamda.presentation.view.entity.MovieView;

/**
 * Activity to display the movie details
 */
public class MovieActivity extends BaseActivity implements IMovieView {

    public static final String ID_TAG = "movie_id";
    private static final String SAVE_TAG = "movie_details";

    MovieViewPresenter presenter;

    MovieView movieView;

    ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_layout);
        imageLoader = new ImageLoader(getApplicationContext());
        initPresenter(getIntent());
        setUpSupportActionBar();
    }

    private void initPresenter(Intent intent) {
        presenter = new MovieViewPresenter();
        presenter.setId(intent.getExtras().getInt(ID_TAG));
        presenter.initialize();
        this.presenter.setView(this);
    }

    @Override
    public void setItem(MovieView movieView) {
        this.movieView = movieView;
        updateView();
    }

    private void updateView() {
        ImageView imageView = (ImageView) findViewById(R.id.cover);
        TextView title = (TextView) findViewById(R.id.title);
        TextView overview = (TextView) findViewById(R.id.overview);
        TextView releaseYear = (TextView) findViewById(R.id.release_year);

        title.setText(movieView.getTitle());
        overview.setText(movieView.getOverview());
        //rating.setText("Rating: " + movieView.getRating());
        //genre.setText(movieView.getGenres());
        releaseYear.setText(movieView.getRelease_date());
        imageLoader.DisplayImage(movieView.getPoster(), imageView);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVE_TAG, movieView);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.movieView = savedInstanceState.getParcelable(SAVE_TAG);
        updateView();
    }

    private void setUpSupportActionBar() {
        ActionBar actionBar = getSupportActionBar();
        //TODO: Set back button, destroys this activity and goes back to the other
    }
}
