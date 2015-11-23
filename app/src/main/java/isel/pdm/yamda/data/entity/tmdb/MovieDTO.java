package isel.pdm.yamda.data.entity.tmdb;

/**
 * Movie Data Transfer Object
 * Used to store the data of a movie from TMDb
 */
public class MovieDTO {

    private GenreDTO[] genres;
    private int id;
    private String imdb_id;
    private String poster_path;
    private String backdrop_path;
    private String release_date;
    private String status;
    private String title;
    private float vote_average;
    private int vote_count;
    private int runtime;
    private String overview;
    private String original_language;
    private String original_title;
    private String homepage;
    private CreditsDTO credits;

    public GenreDTO[] getGenres() {
        return genres;
    }

    public int getId() {
        return id;
    }

    public String getImdbId() {
        return imdb_id;
    }

    public String getPosterPath() {
        return poster_path;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public String getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public float getVoteAverage() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public String getOriginalLanguage() {
        return original_language;
    }

    public String getOriginalTitle() {
        return original_title;
    }

    public CreditsDTO getCredits() {
        return credits;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public int getRuntime() {
        return runtime;
    }

    public int getVoteCount() {
        return vote_count;
    }

    /*
        |--------------------------------------------------------------------------
        | Inner classes
        |--------------------------------------------------------------------------
        */
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

    public static class CreditsDTO {
        private ActorDTO[] cast;
        private CrewDTO[] crew;


        public ActorDTO[] getActors() {
            return cast;
        }

        public CrewDTO[] getCrew() {
            return crew;
        }
    }


    public static class CrewDTO {
        private String credit_id;
        private String department;
        private int id;
        private String job;
        private String name;
        private String profile_path;

        public String getCreditId() {
            return credit_id;
        }

        public String getDepartment() {
            return department;
        }

        public int getId() {
            return id;
        }

        public String getJob() {
            return job;
        }

        public String getName() {
            return name;
        }

        public String getProfilePath() {
            return profile_path;
        }
    }

    public static class ActorDTO {
        private String cast_id;
        private String character;
        private int id;
        private String name;
        private int order;
        private String profile_path;

        public String getCast_id() {
            return cast_id;
        }

        public String getCharacter() {
            return character;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getOrder() {
            return order;
        }

        public String getProfilePath() {
            return profile_path;
        }
    }
}
