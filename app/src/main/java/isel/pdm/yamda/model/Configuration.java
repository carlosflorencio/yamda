package isel.pdm.yamda.model;

/**
 * Created by Nuno on 29/10/2015.
 */
public class Configuration {

    public class ImageConfigurations {
        String base_url;
        String[] backdrop_sizes;
        String[] logo_sizes;
        String[] poster_sizes;
    }

    private ImageConfigurations images;

    public ImageConfigurations getImageConfigurations() {
        return images;
    }

}
