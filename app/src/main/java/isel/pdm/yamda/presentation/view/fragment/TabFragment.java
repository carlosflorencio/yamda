package isel.pdm.yamda.presentation.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import isel.pdm.yamda.R;
import isel.pdm.yamda.presentation.view.activity.MovieActivity;
import isel.pdm.yamda.presentation.view.adapter.LazyAdapter;


public class TabFragment extends Fragment {

    private static final String TAG = TabFragment.class.getSimpleName();

    public static final String KEY_TITLE = "title";
    public static final String KEY_RATING = "rating";
    public static final String KEY_GENRE = "genre";
    public static final String KEY_RELEASE_YEAR = "release_year";
    public static final String KEY_OVERVIEW = "overview";
    public static final String KEY_THUMB_URL = "thumb_url";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_tab, container, false);

        setUpView(view).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    HashMap map = ((HashMap) parent.getAdapter().getItem(position));
                    Intent intent = new Intent(getActivity(), MovieActivity.class);
                    intent.putExtra(KEY_TITLE, (String) map.get(KEY_TITLE));
                    intent.putExtra(KEY_RATING, (String) map.get(KEY_RATING));
                    intent.putExtra(KEY_GENRE, (String) map.get(KEY_GENRE));
                    intent.putExtra(KEY_RELEASE_YEAR, (String) map.get(KEY_RELEASE_YEAR));
                    startActivity(intent);
                }
            });
        return view;
    }

    private ListView setUpView(View view) {
        ListView list = ((ListView) view.findViewById(R.id.list_view));
        list.setAdapter(new LazyAdapter(getActivity(), createList()));
        view.invalidate();
        return list;
    }

    private ArrayList<HashMap<String, String>> createList() {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            createMap(list, i);
        }
        return list;
    }

    private void createMap(ArrayList<HashMap<String, String>> list, int i) {
        HashMap<String, String> map = new HashMap<>();
        map.put(KEY_TITLE, "Inception "+(i+1));
        map.put(KEY_GENRE, "Action, Mystery, Sci-Fi");
        map.put(KEY_RATING, "Rating: 8.8");
        map.put(KEY_RELEASE_YEAR, "2010");
        list.add(map);
    }
}
