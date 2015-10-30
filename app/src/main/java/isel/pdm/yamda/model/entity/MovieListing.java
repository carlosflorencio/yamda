package isel.pdm.yamda.model.entity;

import java.util.List;

/**
 * Created by Nuno on 29/10/2015.
 */
public class MovieListing {

    public static final String NOW_PLAYING_TAG = "NOW_PLAYING";

    public static final String UPCOMING_TAG = "UPCOMING";

    public static final String POPULAR_TAG = "POPULAR";

    private int page;

    private List<Movie> results;

    public int getPage() {
        return page;
    }

    public List<Movie> getResults() {
        return results;
    }
}
