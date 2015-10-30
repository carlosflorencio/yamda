package isel.pdm.yamda.data.entity;

/**
 * Created by Nuno on 30/10/2015.
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
