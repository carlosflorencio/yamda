package isel.pdm.yamda.data.api.entity;

/**
 * Movie credits list Data Transfer Object
 * Used to store the credits of a movie request of TMDb
 */
public class CreditsListingDTO {

    private int id;
    private ActorDTO[] cast;
    private CrewDTO[] crew;


    public ActorDTO[] getActors() {
        return cast;
    }

    public CrewDTO[] getCrew() {
        return crew;
    }

    public int getMovieId() {
        return id;
    }

    /**
     * Crew DTO class
     */
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

    /**
     * Actor DTO Class
     */
    public static class ActorDTO {
        private String cast_id;
        private String character;
        private int id;
        private String name;
        private int order;
        private String profile_path;

        public String getCastId() {
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
