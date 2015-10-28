package isel.pdm.yamda.activities.movie;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import isel.pdm.yamda.R;
import isel.pdm.yamda.activities.home.TabFragment;

/**
 * Created by Nuno on 28/10/2015.
 */
public class MovieActivity extends AppCompatActivity{

    TextView title;
    TextView rating;
    TextView genre;
    TextView releaseYear;
    TextView overview;
    ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_layout);
        handleIntent(getIntent());
    }

    private void handleIntent(Intent intent) {
        this.title = new TextView(this);
        this.title.setText(intent.getStringExtra(TabFragment.KEY_TITLE));

        this.rating = new TextView(this);
        this.rating.setText(intent.getStringExtra(TabFragment.KEY_RATING));

        this.genre = new TextView(this);
        this.genre.setText(intent.getStringExtra(TabFragment.KEY_GENRE));

        this.releaseYear = new TextView(this);
        this.releaseYear.setText(intent.getStringExtra(TabFragment.KEY_RELEASE_YEAR));

        this.overview = new TextView(this);
        this.overview.setText(intent.getStringExtra(TabFragment.KEY_OVERVIEW));

        LinearLayout layout = (LinearLayout) findViewById(R.id.movie_layout);
        layout.addView(title);
        layout.addView(rating);
        layout.addView(genre);
        layout.addView(releaseYear);
        layout.addView(overview);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Set a call to Searchable activity from the search widget
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        return true;
    }
}
