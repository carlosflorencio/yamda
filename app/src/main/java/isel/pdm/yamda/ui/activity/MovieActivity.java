package isel.pdm.yamda.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import isel.pdm.yamda.R;
import isel.pdm.yamda.ui.activity.base.LoggingActivity;
import isel.pdm.yamda.ui.fragment.MovieDetailsFragment;

/**
 * Activity to display the movie details
 */
public class MovieActivity extends LoggingActivity {


    public static final String ID_TAG = "movie_id";
    private int movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.toolbar_content);

        movieId = getIntent().getIntExtra(ID_TAG, 0);

        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);

        this.setSupportActionBar(toolbar);

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Create a new Fragment to be placed in the activity layout
        MovieDetailsFragment firstFragment = new MovieDetailsFragment();

        // In case this activity was started with special instructions from an
        // Intent, pass the Intent's extras to the fragment as arguments
        Bundle b = new Bundle();
        b.putInt(ID_TAG, movieId);
        firstFragment.setArguments(b);

        // Add the fragment to the 'fragment_container' FrameLayout
        getSupportFragmentManager().beginTransaction()
                                   .add(R.id.content, firstFragment).commit();
    }

    /**
     * Make the back button close this activity
     *
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

    /**
     * Create the intent to navigate to this activity
     * @param context
     * @param id
     * @return
     */
    public static Intent createIntent(Context context, int id) {
        Intent intent = new Intent(context, MovieActivity.class);
        intent.putExtra(ID_TAG, id);

        return intent;
    }
}
