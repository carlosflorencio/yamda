package isel.pdm.yamda.data.entity.tmdb;

/**
 * Small Movie Data Transfer Object
 * Used to store a movie retrieved from a list of movies from TMDb
 */
public class MovieListDTO {

    private int id;
    private String title;
    private String poster_path;
    private String backdrop_path;
    private String release_date;
    private int[] genre_ids;
    private float vote_average;
    private String original_title;
    private String original_language;
    private String overview;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return poster_path;
    }

    public String getBackdropPath() {
        return backdrop_path;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public int[] getGenreIds() {
        return genre_ids;
    }

    public float getVoteAverage() {
        return vote_average;
    }

    public String getOriginalTitle() {
        return original_title;
    }

    public String getOriginalLanguage() {
        return original_language;
    }

    public String getOverview() {
        return overview;
    }
}
