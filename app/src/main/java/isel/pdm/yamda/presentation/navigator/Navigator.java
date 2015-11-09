package isel.pdm.yamda.presentation.navigator;

import android.content.Context;
import android.content.Intent;

import isel.pdm.yamda.model.entity.MovieListDetails;
import isel.pdm.yamda.presentation.view.activity.MovieActivity;

public class Navigator {

    public static final String MOVIE_TAG = MovieListDetails.class.toString();

    public static final String ID_TAG = "movie_id";

    public void navigateToMovieDetails(Context context, MovieListDetails movieView) {
        if (context != null) {
            Intent intent = new Intent(context, MovieActivity.class);
            intent.putExtra(ID_TAG, movieView.getId());
            context.startActivity(intent);
        }
    }
}
