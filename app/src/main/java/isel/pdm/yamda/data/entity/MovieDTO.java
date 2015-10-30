package isel.pdm.yamda.data.entity;

/**
 * Created by Nuno on 27/10/2015.
 */
public class MovieDTO {

    private static class GenreDTO {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
    // DTO (Data Transfer Object)

    private GenreDTO[] genres;
    private int id;
    private String imdb_id;
    private String poster_path;
    private String release_date;
    private String status;
    private String title;
    private float vote_average;

    public GenreDTO[] getGenres() {
        return genres;
    }

    public int getId() {
        return id;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public float getVote_average() {
        return vote_average;
    }

}
