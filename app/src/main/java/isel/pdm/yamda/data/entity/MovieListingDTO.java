package isel.pdm.yamda.data.entity;

import java.util.List;

/**
 * Created by Nuno on 29/10/2015.
 */
public class MovieListingDTO {

    public static final String NOW_PLAYING_TAG = "NOW_PLAYING";

    public static final String UPCOMING_TAG = "UPCOMING";

    public static final String POPULAR_TAG = "POPULAR";

    private int page;

    private List<MovieListDTO> results;

    public int getPage() {
        return page;
    }

    public List<MovieListDTO> getResults() {
        return results;
    }
}
