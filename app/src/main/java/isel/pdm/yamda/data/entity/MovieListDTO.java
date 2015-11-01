package isel.pdm.yamda.data.entity;

/**
 * Small Movie Data Transfer Object
 * Used to store a movie retrieved from a list of movies from TMDb
 */
public class MovieListDTO {

    private int id;

    private String poster_path;

    private String release_date;

    private String title;

    private int[] genre_ids;

    private float vote_average;

    public String getTitle() {
        return title;
    }

    public int[] getGenre_ids() {
        return genre_ids;
    }

    public float getVote_average() {
        return vote_average;
    }

    public int getId() {
        return id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }
}
