package isel.pdm.yamda.model;

/**
 * This class is used for representing The credits of a movie
 */
public class MovieCredits {

    private int movieId;
    private Crew[] crew;
    private Actor[] actors;

    public MovieCredits(int movieId, Crew[] crew, Actor[] actors) {
        this.movieId = movieId;
        this.crew = crew;
        this.actors = actors;
    }

    public Crew[] getCrew() {
        return crew;
    }

    public Actor[] getActors() {
        return actors;
    }

    public int getMovieId() {
        return movieId;
    }
}
