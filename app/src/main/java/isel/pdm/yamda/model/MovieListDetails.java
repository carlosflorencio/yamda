package isel.pdm.yamda.model;

/**
 * This class is used for representing a Movie details obtained from the data layer
 * by a movies list. It contains less data than a MovieDetails
 */
public class MovieListDetails {
    /**
     * The movie id
     **/
    protected final int id;
    /**
     * The movie title
     **/
    protected final String title;
    /**
     * The movie original title
     **/
    protected final String original_title;
    /**
     * The movie release date
     **/
    protected final String release_date;
    /**
     * The movie rating
     **/
    protected final double rating;
    /**
     * The movie popularity
     **/
    protected final double popularity;
    /**
     * The movie poster image
     **/
    protected final String poster;
    /**
     * The movie backdrop
     **/
    protected final String backdrop;
    /**
     * The movie genres
     **/
    private final Genre[] genres;

    public MovieListDetails(int id, String title, String original_title, String release_date,
                            String poster, String backdrop, double rating, double popularity, Genre[] genres) {
        this.id = id;
        this.title = title;
        this.original_title = original_title;
        this.release_date = release_date;
        this.poster = poster;
        this.backdrop = backdrop;
        this.rating = rating;
        this.popularity = popularity;
        this.genres = genres;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalTitle() {
        return original_title;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public double getRating() {
        return rating;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPoster() {
        return poster;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public Genre[] getGenres() {
        return genres;
    }
}
