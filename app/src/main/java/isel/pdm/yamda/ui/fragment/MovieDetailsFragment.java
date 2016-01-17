package isel.pdm.yamda.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import isel.pdm.yamda.R;
import isel.pdm.yamda.Utils;
import isel.pdm.yamda.model.MovieDetails;
import isel.pdm.yamda.ui.fragment.base.LoadDataFragment;
import isel.pdm.yamda.ui.presenter.MovieViewPresenter;
import isel.pdm.yamda.ui.presenter.base.IPresenter;

public class MovieDetailsFragment extends LoadDataFragment<MovieDetails> {

    private MovieDetails movie;
    private boolean isBeingFollowed;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View viewContainer = super.onCreateView(inflater, container, savedInstanceState);

        //ask for the movie
        if(!getArguments().isEmpty() && getArguments().getInt("movie_id") != 0) {
            Log.d(TAG, "onCreateView: we have a movie id!");
            ((MovieViewPresenter)this.presenter).setMovieId(getArguments().getInt("movie_id"));
            this.presenter.execute();
        }

        return viewContainer;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_movie;
    }

    @Override
    public void setData(MovieDetails data) {
        this.showResults();
        this.movie = data;

        //set toolbar title
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(data.getTitle());

        ImageView backdropView = (ImageView) this.mainView.findViewById(R.id.movie_back_drop_path);
        ImageView imageView = (ImageView) this.mainView.findViewById(R.id.movie_cover);
        TextView title = (TextView) this.mainView.findViewById(R.id.movie_title_details);
        TextView genre = (TextView) this.mainView.findViewById(R.id.movie_genre);
        TextView rating = (TextView) this.mainView.findViewById(R.id.movie_rating);
        TextView runtime = (TextView) this.mainView.findViewById(R.id.movie_runtime);
        TextView releaseYear = (TextView) this.mainView.findViewById(R.id.movie_release_date);
        TextView overview = (TextView) this.mainView.findViewById(R.id.movie_overview);

        Picasso.with(getContext()).load(movie.getBackdrop()).into(backdropView);
        Picasso.with(getContext()).load(movie.getPoster()).into(imageView);
        title.setText(movie.getTitle());
        genre.setText(Utils.createGenreText(movie.getGenres()));
        rating.setText(this.getString(R.string.row_rating, movie.getRating()));
        runtime.setText(Utils.createRuntimeText(movie.getRuntime()));
        releaseYear.setText(this.getString(R.string.row_released, movie.getReleaseDate()));
        overview.setText(movie.getOverview());

        if (movie.whenIsBeingReleased() > 0) {
            this.mainView.findViewById(R.id.movie_follow).setVisibility(View.VISIBLE);
            ((Checkable) this.mainView.findViewById(R.id.movie_follow)).setChecked(isBeingFollowed);
        }
    }

    @Override
    protected IPresenter createPresenter() {
        return new MovieViewPresenter(this);
    }
}
