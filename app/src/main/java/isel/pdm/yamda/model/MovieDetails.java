package isel.pdm.yamda.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
     * The movie crew team
     **/
    private Crew[] crew;
    /**
     * The movie Cast team
     **/
    private Actor[] actors;
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
                        int runtime, String overview, Genre[] genres,
                        Crew[] crew, Actor[] actors) {
        super(id, title, original_title, release_date, poster, rating, popularity);
        this.runtime = runtime;
        this.overview = overview;
        this.crew = crew;
        this.actors = actors;
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

    public long whenIsBeingReleased() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(this.release_date);
            return date.getTime() - Calendar.getInstance().getTime().getTime();
        } catch (ParseException e) {
            return 0;
        }
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

    public Crew[] getCrew() {
        return crew;
    }

    public Actor[] getActors() {
        return actors;
    }
}
