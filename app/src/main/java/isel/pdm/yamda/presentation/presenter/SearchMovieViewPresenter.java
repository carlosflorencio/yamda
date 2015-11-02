package isel.pdm.yamda.presentation.presenter;

import java.util.List;

import isel.pdm.yamda.model.entity.Movie;
import isel.pdm.yamda.model.repository.IMovieRepository;
import isel.pdm.yamda.presentation.creator.DataFactory;
import isel.pdm.yamda.presentation.mapper.ViewEntitiesDataMapper;
import isel.pdm.yamda.presentation.presenter.common.IPresenter;
import isel.pdm.yamda.presentation.view.contract.ILoadDataView;
import isel.pdm.yamda.presentation.view.entity.MovieView;

/**
 * Search presenter
 */
public class SearchMovieViewPresenter implements IPresenter, ILoadDataView<List<Movie>> {

    private String query;

    private ILoadDataView<List<MovieView>> view;

    public void setView(ILoadDataView<List<MovieView>> v) {
        this.view = v;
    }

    @Override
    public void initialize() {
        DataFactory factory = new DataFactory();
        IMovieRepository repo = factory.getMoviesRepository();

        this.showLoading();
        repo.setMovieSearch(this, this.query, 1);
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
        this.view.setData(mapper.transform(data));
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

    public void setQuery(String q) {
        this.query = q;
    }
}
