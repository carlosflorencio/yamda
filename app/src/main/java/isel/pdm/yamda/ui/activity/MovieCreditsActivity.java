package isel.pdm.yamda.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import isel.pdm.yamda.R;
import isel.pdm.yamda.ui.activity.base.ToolbarActivity;
import isel.pdm.yamda.ui.fragment.MovieCreditsFragment;

/**
 * Activity to display the movie details
 */
public class MovieCreditsActivity extends ToolbarActivity {


    public static final String ID_TAG = "movie_id";
    public static final String TITLE_TAG = "movie_title";

    private int movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        movieId = getIntent().getIntExtra(ID_TAG, 0);
        String title = getIntent().getStringExtra(TITLE_TAG);


        String t = getResources().getString(R.string.credits) + ": " + title;
        this.toolbar.setTitle(t);
        this.enableBackButton();

        // create and add the fragment
        MovieCreditsFragment firstFragment = new MovieCreditsFragment();
        Bundle b = new Bundle();
        b.putInt(ID_TAG, movieId);
        firstFragment.setArguments(b);

        getSupportFragmentManager().beginTransaction()
                                   .add(R.id.content, firstFragment).commit();
    }

    /**
     * Create the intent to navigate to this activity
     * @param context
     * @param id
     * @return
     */
    public static Intent createIntent(Context context, int id, String title) {
        Intent intent = new Intent(context, MovieCreditsActivity.class);
        intent.putExtra(ID_TAG, id);
        intent.putExtra(TITLE_TAG, title);

        return intent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Back Button
            case android.R.id.home:
                Intent up = NavUtils.getParentActivityIntent(this);
                up.putExtra(MovieActivity.ID_TAG, movieId);
                NavUtils.navigateUpTo(this, up);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
