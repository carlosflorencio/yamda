package isel.pdm.yamda.data.entity;

/**
 * Class used to retrieve a list of movies by title
 */
public class SearchMovieListDTO {

    private int page;

    private MovieDTO[] results;

    public int getPage() {
        return page;
    }

    public MovieDTO[] getResults() {
        return results;
    }
}
