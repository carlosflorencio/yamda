package isel.pdm.yamda.presentation.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import isel.pdm.yamda.R;
import isel.pdm.yamda.data.image.ImageLoader;
import isel.pdm.yamda.model.entity.MovieListDetails;

/**
 * Adapter that displays the movies list with images
 */
public class LazyAdapter extends BaseAdapter {

    private static final String TAG = "LazyAdapter";
    private Activity activity;
    private List<MovieListDetails> data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader;

    public LazyAdapter(Activity a, List<MovieListDetails> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader = new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return data.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        if(convertView==null)
            view = inflater.inflate(R.layout.home_tab_list_row, null);

        TextView title = (TextView)view.findViewById(R.id.title); // title
        TextView rating = (TextView)view.findViewById(R.id.rating); // rating
        TextView genre = (TextView)view.findViewById(R.id.genre); // genre
        TextView releaseYear = (TextView)view.findViewById(R.id.release_year); // release year
        ImageView thumb_image = (ImageView) view.findViewById(R.id.thumbnail); // thumb image

        MovieListDetails movie = data.get(position);

        // Setting all values in listview
        title.setText(movie.getTitle());
        rating.setText("Rating: " + movie.getRating());
        genre.setText("sad");
        releaseYear.setText(movie.getReleaseDate());

        if(movie.getPoster() == null) {
            Log.v(TAG, "Movie without image: " + movie.getTitle());
            thumb_image.setImageResource(R.drawable.placeholder);
        } else {
            imageLoader.DisplayImage(movie.getPoster(), thumb_image);
        }

        return view;
    }


}
