package isel.pdm.yamda.model.entity;

/**
 * Class that represents a Movie in the model layer.
 */
public class Movie {

    private int id;

    private String title;

    private String overview;

    private String[] genres;

    private String releaseDate;

    private String director;

    private String[] writers;

    private String[] actors;

    private String status;

    private String poster;

    private String rating;

    public Movie(int id, String title, String overview, String[] genres, String releaseDate, String status, String poster) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.genres = genres;
        this.releaseDate = releaseDate;
        this.status = status;
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String[] getWriters() {
        return writers;
    }

    public void setWriters(String[] writers) {
        this.writers = writers;
    }

    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getRating() {
        return rating;
    }

    public int getId() {
        return id;
    }
}
