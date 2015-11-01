package isel.pdm.yamda.presentation.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import isel.pdm.yamda.R;
import isel.pdm.yamda.model.entity.Movie;
import isel.pdm.yamda.presentation.presenter.IPresenter;
import isel.pdm.yamda.presentation.presenter.MoviesListViewPresenter;
import isel.pdm.yamda.presentation.view.activity.MovieActivity;
import isel.pdm.yamda.presentation.view.activity.contract.IMoviesListView;
import isel.pdm.yamda.presentation.view.adapter.LazyAdapter;
import isel.pdm.yamda.presentation.view.entity.MovieView;
import isel.pdm.yamda.presentation.view.fragment.common.BaseFragment;


public class TabFragment extends BaseFragment {

    private static final String TAG = ListMoviesFragment.class.getSimpleName();
    public static final String MOVIE_TAG = MovieView.class.toString();

    private ArrayList<MovieView> list;
    private View view;
    private IPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.presenter = new MoviesListViewPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_tab, container, false);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(TAG, list);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            list = savedInstanceState.getParcelableArrayList(TAG);
        } else {
            list = createList();
        }
        setUpView();
    }

    private ListView setUpView() {
        ListView list = ((ListView) view.findViewById(R.id.list_view));
        list.setAdapter(new LazyAdapter(getActivity(), this.list));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MovieView movie = ((MovieView) parent.getAdapter().getItem(position));
                Intent intent = new Intent(getActivity(), MovieActivity.class);
                intent.putExtra(MOVIE_TAG, movie);
                startActivity(intent);
            }
        });
        view.invalidate();
        return list;
    }

    private ArrayList<MovieView> createList() {
        Log.v("DEBUG", "createList");
        ArrayList<MovieView> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new MovieView("Inception", "Released", "2010", null, new String[]{"Action", "Mystery", "Sci-Fi"}, "8.8"));
        }
        return list;
    }

    @Override
    public void setItems(List<Movie> items) {

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
    public void onItemClicked(int position) {

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onResume();
        presenter.onPause();
    }

    @Override
    public void onDestroy() {
        super.onResume();
        presenter.onDestroy();
    }
}
