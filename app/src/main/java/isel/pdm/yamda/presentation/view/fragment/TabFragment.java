package isel.pdm.yamda.presentation.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import isel.pdm.yamda.R;
import isel.pdm.yamda.presentation.view.activity.MovieActivity;
import isel.pdm.yamda.presentation.view.adapter.LazyAdapter;
import isel.pdm.yamda.presentation.view.entity.MovieView;


public class TabFragment extends Fragment {

    private static final String TAG = TabFragment.class.getSimpleName();

    public static final String MOVIE_TAG = MovieView.class.toString();

    private ArrayList<MovieView> list;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.v("DEBUG", "onCreateView");
        view = inflater.inflate(R.layout.home_tab, container, false);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.v("DEBUG", "onSaveInstanceState");
        outState.putSerializable(TAG, list);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.v("DEBUG", "onActivityCreated");
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
}
