package isel.pdm.yamda.model;

/**
 * This class is used for representing an Actor Member
 */
public final class Actor {

    /**
     * The actor cast id
     **/
    private final String cast_id;
    /**
     * The actor character name
     **/
    private final String character;
    /**
     * The actor id
     **/
    private final int    id;
    /**
     * The actor name
     **/
    private final String name;
    /**
     * The actor profile
     **/
    private final String profile_path;
    /**
     * The actor order
     **/
    private final int    order;

    /**
     * Create an Actor model with the given parameters
     *
     * @param cast_id
     * @param character
     * @param id
     * @param name
     * @param profile_path
     * @param order
     */
    public Actor(String cast_id, String character, int id, String name,
                 String profile_path, int order) {
        this.cast_id = cast_id;
        this.character = character;
        this.id = id;
        this.name = name;
        this.profile_path = profile_path;
        this.order = order;
    }

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

    public String getProfilePath() {
        return profile_path;
    }

    public int getOrder() {
        return order;
    }

}
