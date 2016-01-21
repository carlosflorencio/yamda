package isel.pdm.yamda.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import isel.pdm.yamda.R;
import isel.pdm.yamda.model.Movie;

/**
 * Adapter used to show a list of Movie
 */
public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder> {

    private List<Movie> data;
    private Context                context;
    private IClickListener         listener;

    /**
     * Constructs a new MovieRecyclerAdapter with a context
     *
     * @param ctx
     */
    public MovieRecyclerAdapter(Context ctx) {
        this.context = ctx;
    }

    /**
     * Replace data in the adapter
     *
     * @param data
     */
    public void setData(List<Movie> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    /**
     * Sets the click listener
     * @param listener
     */
    public void setListener(IClickListener listener) {
        this.listener = listener;
    }

    /**
     * Clear all data from adapter
     */
    public void clearData() {
        data.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.list_movies_row_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = data.get(position);

        holder.title.setText(movie.getTitle());
        holder.title_original.setText(movie.getOriginalTitle());
        holder.rating.setText(context.getString(R.string.row_rating, movie.getRating()));
        holder.releaseYear.setText(context.getString(R.string.row_released, movie.getReleaseDate()));

        //Debug only
        //Picasso.with(context).setIndicatorsEnabled(true);
        //Picasso.with(context).setLoggingEnabled(true);
        Picasso.with(context).load(movie.getPoster()).placeholder(R.drawable.placeholder2)
               .into(holder.thumb_image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * Interface for click listeners for this adapter
     */
    public interface IClickListener {
        void onItemClick(Movie movie);
    }

    /**
     * List row members
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView  title;
        public TextView  title_original;
        public TextView  rating;
        public TextView  releaseYear;
        public ImageView thumb_image;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.movie_title_details);
            title_original = (TextView) itemView.findViewById(R.id.title_original);
            rating = (TextView) itemView.findViewById(R.id.rating);
            releaseYear = (TextView) itemView.findViewById(R.id.release_year);
            thumb_image = (ImageView) itemView.findViewById(R.id.thumbnail);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(data.get(getAdapterPosition()));
        }
    }
}
