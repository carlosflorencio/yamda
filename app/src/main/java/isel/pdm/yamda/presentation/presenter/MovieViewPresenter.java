package isel.pdm.yamda.presentation.presenter;

import isel.pdm.yamda.model.entity.Movie;
import isel.pdm.yamda.model.repository.IMovieRepository;
import isel.pdm.yamda.presentation.creator.DataFactory;
import isel.pdm.yamda.presentation.mapper.ViewEntitiesDataMapper;
import isel.pdm.yamda.presentation.presenter.common.IPresenter;
import isel.pdm.yamda.presentation.view.activity.contract.IMovieView;
import isel.pdm.yamda.presentation.view.contract.ILoadDataView;

/**
 * Created by Nuno on 01/11/2015.
 */
public class MovieViewPresenter implements IPresenter, ILoadDataView<Movie> {

    private int id;

    private IMovieView view;

    public void setView(IMovieView v) {
        this.view = v;
    }

    @Override
    public void initialize() {
        DataFactory factory = new DataFactory();
        IMovieRepository repo = factory.getMoviesRepository();

        this.showLoading();
        repo.setMovie(this, id);
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
    public void setData(Movie data) {
        ViewEntitiesDataMapper mapper = new ViewEntitiesDataMapper();
        this.view.setItem(mapper.transform(data));
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    public void setId(int id) {
        this.id = id;
    }
}
