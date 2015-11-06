package isel.pdm.yamda.presentation.presenter.common;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import isel.pdm.yamda.model.entity.Movie;
import isel.pdm.yamda.presentation.mapper.ViewEntitiesDataMapper;
import isel.pdm.yamda.presentation.navigator.Navigator;
import isel.pdm.yamda.presentation.presenter.base.IPresenter;
import isel.pdm.yamda.presentation.view.activity.HomeActivity;
import isel.pdm.yamda.presentation.view.adapter.LazyAdapter;
import isel.pdm.yamda.presentation.view.contract.ILoadDataView;
import isel.pdm.yamda.presentation.view.entity.MovieView;

public abstract class MovieListablePresenter implements IPresenter, ILoadDataView<List<Movie>>, AdapterView.OnItemClickListener {

    protected ListView listView;
    protected Activity activity;

    public MovieListablePresenter(Activity activity, ListView listView) {
        this.listView = listView;
        this.activity = activity;
    }

    /*
        |--------------------------------------------------------------------------
        | DataView Methods
        |--------------------------------------------------------------------------
        */
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
        this.setListViewAdapter(mapper.transform(data));
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
    | Modify the view
    |--------------------------------------------------------------------------
    */
    private void setListViewAdapter(List<MovieView> list) {
        this.listView.setAdapter(new LazyAdapter(this.activity, list));
        this.listView.setOnItemClickListener(this);
        //this.fragment.getViewContainer().invalidate();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MovieView movie = ((MovieView) parent.getAdapter().getItem(position));
        Navigator nav = ((HomeActivity) this.activity).getNavigator();

        nav.navigateToMovieDetails(this.activity, movie);
    }

}
