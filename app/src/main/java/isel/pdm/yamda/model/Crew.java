package isel.pdm.yamda.model;

/**
 * This class is used for representing a Crew Member like a director, producer, etc
 */
public final class Crew {

    /**
     * The crew credit id
     **/
    private final String credit_id;
    /**
     * The crew department
     **/
    private final String department;
    /**
     * The crew id
     **/
    private final int id;
    /**
     * The crew job
     **/
    private final String job;
    /**
     * The crew name
     **/
    private final String name;
    /**
     * The crew profile
     **/
    private final String profile_path;

    /**
     * Constructs a new Crew Object with the specified fields
     *
     * @param credit_id
     * @param department
     * @param id
     * @param job
     * @param name
     * @param profile_path
     */
    public Crew(String credit_id, String department, int id, String job, String name,
                String profile_path) {
        this.credit_id = credit_id;
        this.department = department;
        this.id = id;
        this.job = job;
        this.name = name;
        this.profile_path = profile_path;
    }

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
