package isel.pdm.yamda.presentation.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import isel.pdm.yamda.model.entity.Movie;
import isel.pdm.yamda.model.repository.IMovieRepository;
import isel.pdm.yamda.presentation.ILoadDataView;
import isel.pdm.yamda.presentation.creator.DataFactory;
import isel.pdm.yamda.presentation.mapper.ViewEntitiesDataMapper;
import isel.pdm.yamda.presentation.view.activity.contract.IMoviesListView;

public class MoviesListViewPresenter implements IPresenter, ILoadDataView<List<Movie>> {

    private IMoviesListView view;
    private String type;

    public MoviesListViewPresenter(String type) {
        this.type = type;
    }

    public void initialize() {
        Log.v("DEBUG_", "initialize " + type);
        this.askForData();
    }

    private void askForData() {
        DataFactory factory = new DataFactory();
        IMovieRepository repo = factory.getMoviesRepository();

        repo.setTheatersMovies(this, 1);
    }

    /** {@inheritDoc} **/
    @Override
    public void onResume() {
        this.view.showProgress();
    }

    /** {@inheritDoc} **/
    @Override
    public void onPause() {

    }

    /** {@inheritDoc} **/
    @Override
    public void onDestroy() {

    }

    public void setView(IMoviesListView v) {
        this.view = v;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void setData(List<Movie> data) {
        ViewEntitiesDataMapper mapper = new ViewEntitiesDataMapper();
        Log.v("DEBUG_", "setData " + type);
        this.view.setItems(mapper.transform(data));
    }
}
