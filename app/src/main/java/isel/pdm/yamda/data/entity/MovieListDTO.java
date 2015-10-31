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

}
