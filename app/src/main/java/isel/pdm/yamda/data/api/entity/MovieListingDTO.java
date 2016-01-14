package isel.pdm.yamda.data.api.entity;

import java.util.List;

/**
 * List of Movies Data Transfer Object
 * Used to retrieve a series of movies from TMDb
 */
public class MovieListingDTO {

    private int page;
    private List<MovieListDTO> results;

    public int getPage() {
        return page;
    }

    public List<MovieListDTO> getResults() {
        return results;
    }

    /**
     * Small Movie Data Transfer Object
     * Used to store a movie list retrieved from a list of movies from TMDb
     */
    public static class MovieListDTO {

        private int id;
        private String title;
        private String poster_path;
        private String backdrop_path;
        private String release_date;
        private int[] genre_ids;
        private double vote_average;
        private double popularity;
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

        public double getVoteAverage() {
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

        public double getPopularity() {
            return popularity;
        }
    }
}
