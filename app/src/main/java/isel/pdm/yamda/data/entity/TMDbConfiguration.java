package isel.pdm.yamda.data.entity;

/**
 * This entity represents a TMDb configuration, now is hard coded but in the future maybe will be requested and cached
 */
public class TMDbConfiguration implements IConfiguration {

    @Override
    public String getImagesURI() {
        return "http://image.tmdb.org/t/p/";
    }

    @Override
    public String getPosterSize() {
        return "w92";
    }

}
