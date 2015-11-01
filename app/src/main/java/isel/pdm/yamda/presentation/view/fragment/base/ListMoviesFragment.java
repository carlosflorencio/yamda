package isel.pdm.yamda.presentation.view.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import isel.pdm.yamda.R;
import isel.pdm.yamda.presentation.navigator.Navigator;
import isel.pdm.yamda.presentation.presenter.base.MoviesListViewPresenter;
import isel.pdm.yamda.presentation.view.activity.HomeActivity;
import isel.pdm.yamda.presentation.view.activity.contract.IMoviesListView;
import isel.pdm.yamda.presentation.view.adapter.LazyAdapter;
import isel.pdm.yamda.presentation.view.entity.MovieView;
import isel.pdm.yamda.presentation.view.component.ViewPagerAdapter;
import isel.pdm.yamda.presentation.view.fragment.common.PresentableFragment;


public class ListMoviesFragment extends PresentableFragment implements IMoviesListView, AdapterView.OnItemClickListener {

    private static final String SAVE_TAG = "movielistdetails";
    private View view;
    private ListView listView;
    private ArrayList<MovieView> items;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.home_tab, container, false);
        this.listView = ((ListView) view.findViewById(R.id.list_view));
        this.presenter = new MoviesListViewPresenter(getArguments().getString(ViewPagerAdapter.FRAGMENT_KEY));

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVE_TAG, this.items);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MoviesListViewPresenter) this.presenter).setView(this);
        ((MoviesListViewPresenter) this.presenter).initialize();

        if (savedInstanceState != null) {
            this.items = savedInstanceState.getParcelableArrayList(TAG);
        }
    }

    @Override
    public void setItems(List<MovieView> items) {
        this.listView.setAdapter(new LazyAdapter(getActivity(), items));
        this.listView.setOnItemClickListener(this);
        view.invalidate();
    }

    @Override
    public void showProgress() {
        //progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        //progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onItemClicked(MovieView movieView) {
        Navigator nav = ((HomeActivity)getActivity()).getNavigator();

        nav.navigateToMovieDetails(getActivity(), movieView);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MovieView movie = ((MovieView) parent.getAdapter().getItem(position));

        this.onItemClicked(movie);
    }
}
