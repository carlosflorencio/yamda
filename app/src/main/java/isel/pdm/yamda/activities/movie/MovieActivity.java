package isel.pdm.yamda.activities.movie;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import isel.pdm.yamda.R;
import isel.pdm.yamda.activities.home.TabFragment;

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

        //((TextView)findViewById(R.id.rating)).setText(intent.getStringExtra(TabFragment.KEY_RATING));

        //((TextView)findViewById(R.id.genre)).setText(intent.getStringExtra(TabFragment.KEY_GENRE));

        //((TextView)findViewById(R.id.release_year)).setText(intent.getStringExtra(TabFragment.KEY_RELEASE_YEAR));

        ((TextView)findViewById(R.id.overview)).setText(intent.getStringExtra(TabFragment.KEY_OVERVIEW));
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
