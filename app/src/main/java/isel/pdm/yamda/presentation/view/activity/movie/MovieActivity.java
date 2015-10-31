package isel.pdm.yamda.presentation.view.activity.movie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import isel.pdm.yamda.R;
import isel.pdm.yamda.presentation.view.activity.home.TabFragment;

/**
 * Created by Nuno on 28/10/2015.
 */
public class MovieActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_layout);
        handleIntent(getIntent());
    }

    private void handleIntent(Intent intent) {
        ((ImageView)findViewById(R.id.cover)).setImageResource(R.drawable.cover);

        ((TextView)findViewById(R.id.title)).setText(intent.getStringExtra(TabFragment.KEY_TITLE));

//        ((TextView)findViewById(R.id.rating)).setText(intent.getStringExtra(TabFragment.KEY_RATING));
//
//        ((TextView)findViewById(R.id.genre)).setText(intent.getStringExtra(TabFragment.KEY_GENRE));

        ((TextView) findViewById(R.id.release_year)).setText(intent.getStringExtra(TabFragment.KEY_RELEASE_YEAR));

        ((TextView) findViewById(R.id.overview)).setText("Cobb, a skilled thief who commits corporate" +
                " espionage by infiltrating the subconscious of his targets is offered a chance to " +
                "regain his old life as payment for a task considered to be impossible: " +
                "\\\"inception\\\", the implantation of another person's idea into a target's" +
                " subconscious.");
    }
}
