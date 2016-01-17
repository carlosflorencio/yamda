package isel.pdm.yamda.model;

/**
 * This class is used for representing a Movie obtained from the data layer with all the details
 */
public class MovieDetails extends Movie {

    /**
     * The movie run time
     **/
    private int runtime;
    /**
     * The movie overview description
     **/
    private String overview;
    /**
     * The movie backdrop
     **/
    protected String backdrop;
    /**
     * The movie genres, must be filled
     **/
    private Genre[] genres;
    /**
     * Set the movie is being followed
     */
    private boolean isBeingFollowed;

    public MovieDetails(int id, String title, String original_title, String release_date,
                        String poster, String backdrop, double rating, double popularity,
                        int runtime, String overview, Genre[] genres) {
        super(id, title, original_title, release_date, poster, rating, popularity);
        this.runtime = runtime;
        this.overview = overview;
        this.backdrop = backdrop;
        this.genres = genres;
        this.isBeingFollowed = false;
    }

    public void setBeingFollowed(boolean beingFollowed) {
        isBeingFollowed = beingFollowed;
    }

    public boolean isBeingFollowed() {
        return isBeingFollowed;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public Genre[] getGenres() {
        return this.genres;
    }
}
