package isel.pdm.yamda.presentation.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import isel.pdm.yamda.R;
import isel.pdm.yamda.presentation.view.entity.MovieView;
import isel.pdm.yamda.presentation.view.fragment.TabFragment;

/**
 * Created by Nuno on 28/10/2015.
 */
public class MovieActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_layout);
        handleIntent(getIntent());
        setUpSupportActionBar();
    }

    private void handleIntent(Intent intent) {
        MovieView movie = intent.getExtras().getParcelable(TabFragment.MOVIE_TAG);

        ((ImageView) findViewById(R.id.cover)).setImageResource(R.drawable.cover);

        ((TextView) findViewById(R.id.title)).setText(movie.getTitle());

        //((TextView)findViewById(R.id.rating)).setText(movie.getRating());

        //((TextView)findViewById(R.id.genre)).setText(movie.getGenres());

        ((TextView) findViewById(R.id.release_year)).setText(movie.getRelease_date());

        ((TextView) findViewById(R.id.overview)).setText("Cobb, a skilled thief who commits corporate" +
                " espionage by infiltrating the subconscious of his targets is offered a chance to " +
                "regain his old life as payment for a task considered to be impossible: " +
                "\\\"inception\\\", the implantation of another person's idea into a target's" +
                " subconscious.");
    }

    private void setUpSupportActionBar() {
        ActionBar actionBar = getSupportActionBar();
        //TODO: Set back button, destroys this activity and goes back to the other
    }
}
