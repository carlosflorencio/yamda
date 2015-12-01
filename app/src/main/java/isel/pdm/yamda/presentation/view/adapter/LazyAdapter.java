package isel.pdm.yamda.presentation.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import isel.pdm.yamda.R;
import isel.pdm.yamda.model.entity.MovieListDetails;

/**
 * Adapter that displays the movies list with images
 */
public class LazyAdapter extends BaseAdapter {

    private static final String TAG = "LazyAdapter";
    private Activity activity;
    private List<MovieListDetails> data;
    private static LayoutInflater inflater=null;

    public LazyAdapter(Activity a, List<MovieListDetails> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        genre.setText(movie.getGenresTogether());
        releaseYear.setText(movie.getReleaseDate());

        //Debug only
        Picasso.with(activity).setIndicatorsEnabled(true);
        Picasso.with(activity).load(movie.getPoster()).placeholder(R.drawable.placeholder)
               .into(thumb_image);

//        if(movie.getPoster() == null) {
//            Log.v(TAG, "Movie without image: " + movie.getTitle());
//            thumb_image.setImageResource(R.drawable.placeholder);
//        } else {
//            Picasso.with(activity).load(movie.getPoster()).into(thumb_image);
//        }

        return view;
    }


}
