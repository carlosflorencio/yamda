package isel.pdm.yamda.data.entity.tmdb;

/**
 * Movie Data Transfer Object
 * Used to store the data of a movie from TMDb
 */
public class MovieDTO {

    public String getOverview() {
        return overview;
    }

    public static class GenreDTO {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    private GenreDTO[] genres;

    private int id;

    private String imdb_id;

    private String poster_path;

    private String release_date;

    private String status;

    private String title;

    private float vote_average;

    private String overview;

    public String[] getGenres() {
        String[] strings = new String[genres.length];
        for (int i = 0; i < genres.length; i++) {
            strings[i] = genres[i].name;
        }
        return strings;
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
