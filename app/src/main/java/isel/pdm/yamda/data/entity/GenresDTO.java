package isel.pdm.yamda.data.entity;

/**
 * Genres Data Transfer Object
 * Used to store the data of every genre in movies category of TMDb
 */
public class GenresDTO {

    private class GenreDTO {
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

    public GenreDTO[] getGenres() {
        return genres;
    }
}
