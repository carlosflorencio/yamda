package isel.pdm.yamda.model.entity;

/**
 * Created by Nuno on 31/10/2015.
 */
public class Configuration {

    private String baseUrl;

    private String[] posterSizes;

    public Configuration(String baseUrl, String[] posterSizes) {
        this.baseUrl = baseUrl;
        this.posterSizes = posterSizes;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String[] getPosterSizes() {
        return posterSizes;
    }
}
