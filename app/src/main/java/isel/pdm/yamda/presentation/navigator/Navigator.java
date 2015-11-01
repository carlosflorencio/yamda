package isel.pdm.yamda.presentation.navigator;

import android.content.Context;
import android.content.Intent;

import isel.pdm.yamda.presentation.view.activity.MovieActivity;
import isel.pdm.yamda.presentation.view.entity.MovieView;

public class Navigator {

    public static final String MOVIE_TAG = MovieView.class.toString();

    public static final String ID_TAG = "movie_id";

    public void navigateToMovieDetails(Context context, MovieView movieView) {
        if (context != null) {
            Intent intent = new Intent(context, MovieActivity.class);
            intent.putExtra(ID_TAG, movieView.getId());
            context.startActivity(intent);
        }
    }
}
