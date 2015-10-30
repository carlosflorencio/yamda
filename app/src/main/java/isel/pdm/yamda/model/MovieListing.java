package isel.pdm.yamda.model;

import java.util.List;

/**
 * Created by Nuno on 29/10/2015.
 */
public class MovieListing {

    private int page;

    private List<Movie> results;

    public int getPage() {
        return page;
    }

    public List<Movie> getResults() {
        return results;
    }
}
