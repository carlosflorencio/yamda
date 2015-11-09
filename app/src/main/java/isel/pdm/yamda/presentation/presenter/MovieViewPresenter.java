package isel.pdm.yamda.presentation.presenter;

import android.widget.ImageView;
import android.widget.TextView;

import isel.pdm.yamda.R;
import isel.pdm.yamda.YamdaApplication;
import isel.pdm.yamda.data.image.ImageLoader;
import isel.pdm.yamda.data.repository.IMovieRepository;
import isel.pdm.yamda.model.entity.MovieDetails;
import isel.pdm.yamda.presentation.presenter.base.IPresenter;
import isel.pdm.yamda.presentation.view.activity.MovieActivity;
import isel.pdm.yamda.presentation.view.contract.ILoadDataView;

/**
 * Movie view details presenter
 */
public class MovieViewPresenter implements IPresenter, ILoadDataView<MovieDetails> {

    private int id;
    private MovieActivity activity;
    private ImageLoader imageLoader;

    public MovieViewPresenter(MovieActivity activity, int movieId) {
        this.activity = activity;
        this.id = movieId;
        this.imageLoader = new ImageLoader(this.activity.getApplicationContext());

        this.askForData();
    }

    private void askForData() {
        IMovieRepository repo = ((YamdaApplication)this.activity.getApplication()).getMovieRepository();

        this.showLoading();
        repo.setMovie(this, id);
    }

    /*
    |--------------------------------------------------------------------------
    | DataView Methods
    |--------------------------------------------------------------------------
    */
    @Override
    public void showLoading() {}

    @Override
    public void hideLoading() {}

    @Override
    public void showError(String message) {
        this.hideLoading();
    }

    @Override
    public void setData(MovieDetails data) {
        this.hideLoading();
        this.updateView(data);
    }

    /*
    |--------------------------------------------------------------------------
    | Presenter Lifecycle
    |--------------------------------------------------------------------------
    */
    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    /*
    |--------------------------------------------------------------------------
    | Modify the activity
    |--------------------------------------------------------------------------
    */
    private void updateView(MovieDetails movieView) {
        ImageView imageView = (ImageView) this.activity.findViewById(R.id.cover);
        TextView title = (TextView) this.activity.findViewById(R.id.title);
        TextView overview = (TextView) this.activity.findViewById(R.id.overview);
        TextView releaseYear = (TextView) this.activity.findViewById(R.id.release_year);

        title.setText(movieView.getTitle());
        overview.setText(movieView.getOverview());
        //rating.setText("Rating: " + movieView.getRating());
        //genre.setText(movieView.getGenres());
        releaseYear.setText(movieView.getRelease_date());
        imageLoader.DisplayImage(movieView.getPoster(), imageView);
    }

}
