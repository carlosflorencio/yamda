package isel.pdm.yamda.model;

/**
 * This class is used for representing a Genre
 */
public class Genre {

    private final int    id;
    private final String name;

    /**
     * Constructs a new Genre with the specified id and name
     *
     * @param id
     * @param name
     */
    public Genre(int id, String name) {
        this.id = id;

        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
