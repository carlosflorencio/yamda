package isel.pdm.yamda.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This class is used for representing a Movie details obtained from the data layer
 * by a movies list. It contains less data than a MovieDetails
 *
 * Genres must be filled after retrieved
 */
public class Movie {
    /**
     * The movie id
     **/
    protected int id;
    /**
     * The movie title
     **/
    protected String title;
    /**
     * The movie original title
     **/
    protected String original_title;
    /**
     * The movie release date
     **/
    protected String release_date;
    /**
     * The movie rating
     **/
    protected double rating;
    /**
     * The movie popularity
     **/
    protected double popularity;
    /**
     * The movie poster image
     **/
    protected String poster;

    public Movie(int id, String title, String original_title, String release_date,
                 String poster, double rating, double popularity) {
        this.id = id;
        this.title = title;
        this.original_title = original_title;
        this.release_date = release_date;
        this.poster = poster;
        this.rating = rating;
        this.popularity = popularity;
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

    public long whenIsBeingReleased() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(this.release_date);
            return date.getTime() - Calendar.getInstance().getTime().getTime();
        } catch (ParseException e) {
            return 0;
        }
    }
}
