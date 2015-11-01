package isel.pdm.yamda.presentation.presenter.base;

import java.util.List;

import isel.pdm.yamda.model.entity.Movie;
import isel.pdm.yamda.presentation.presenter.common.IPresenter;
import isel.pdm.yamda.presentation.view.contract.ILoadDataView;
import isel.pdm.yamda.presentation.mapper.ViewEntitiesDataMapper;
import isel.pdm.yamda.presentation.view.activity.contract.IMoviesListView;

public abstract class MoviesListViewPresenter implements IPresenter, ILoadDataView<List<Movie>> {

    private IMoviesListView view;

    public void setView(IMoviesListView v) {
        this.view = v;
    }

    /*
    |--------------------------------------------------------------------------
    | ILoadDataView methods
    |--------------------------------------------------------------------------
    */
    @Override
    public void showLoading() {}

    @Override
    public void hideLoading() {}

    @Override
    public void showError(String message) {}

    @Override
    public void setData(List<Movie> data) {
        //TODO: change this to DI
        ViewEntitiesDataMapper mapper = new ViewEntitiesDataMapper();
        this.view.setItems(mapper.transform(data));
    }

    /*
    |--------------------------------------------------------------------------
    | IPresenter Methods
    |--------------------------------------------------------------------------
    */
    /** {@inheritDoc} **/
    @Override
    public void onResume() {
    }

    /** {@inheritDoc} **/
    @Override
    public void onPause() {}

    /** {@inheritDoc} **/
    @Override
    public void onDestroy() {}

}
