package isel.pdm.yamda.data.entity.tmdb;

import java.util.List;

/**
 * List of Movies Data Transfer Object
 * Used to retrieve a series of movies from TMDb
 */
public class MovieListingDTO {

    private int page;
    private List<MovieListDTO> results;

    public int getPage() {
        return page;
    }

    public List<MovieListDTO> getResults() {
        return results;
    }
}
