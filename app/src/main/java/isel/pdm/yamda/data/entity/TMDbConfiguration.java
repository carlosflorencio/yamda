package isel.pdm.yamda.data.entity;

import java.util.HashMap;

/**
 * This entity represents a TMDb configuration, now is hard coded but in the future maybe will be requested and cached
 */
public class TMDbConfiguration implements IConfiguration {

    private HashMap<Integer, String> genreNames;

    public TMDbConfiguration() {
        this.genreNames = new HashMap<>();

        this.fillTheMap(); // FIXME: 08/11/2015 change this to a request with language support
    }

    @Override
    public String getImagesURI() {
        return "http://image.tmdb.org/t/p/";
    }

    @Override
    public String getListPosterSize() {
//        return "w92";
//        return "w154";
        return "w185";
//        return "w342";
//        return "w500";
//        return "w780";
//        return "original";
    }

    @Override
    public String getBackdropSize() {
        //return "w300";
        return "w780";
        //return "w1280";
        //return "original";
    }

    @Override
    public String getDetailPosterSize() {
        return "w342";
    }

    @Override
    public String getGenre(int id) {
        return this.genreNames.get(id);
    }

    private void fillTheMap() {
        this.genreNames.put(28, "Ação");
        this.genreNames.put(12, "Aventura");
        this.genreNames.put(16, "Animação");
        this.genreNames.put(35, "Comédia");
        this.genreNames.put(80, "Crime");
        this.genreNames.put(99, "Documentário");
        this.genreNames.put(18, "Drama");
        this.genreNames.put(10751, "Família");
        this.genreNames.put(14, "Fantasia");
        this.genreNames.put(10769, "Estrangeiro");
        this.genreNames.put(36, "História");
        this.genreNames.put(27, "Terror");
        this.genreNames.put(10402, "Música");
        this.genreNames.put(9648, "Mistério");
        this.genreNames.put(10749, "Romance");
        this.genreNames.put(878, "Ficção científica");
        this.genreNames.put(10770, "Cinema TV");
        this.genreNames.put(53, "Thriller");
        this.genreNames.put(10752, "Guerra");
        this.genreNames.put(37, "Faroeste");
    }


}
