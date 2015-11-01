package isel.pdm.yamda.data.entity.tmdb;

/**
 * Small Movie Data Transfer Object
 * Used to store a movie retrieved from a list of movies from TMDb
 */
public class MovieListDTO {

    public int id;
    public String poster_path;
    public String release_date;
    public String title;
    public int[] genre_ids;
    public float vote_average;


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

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }
}
