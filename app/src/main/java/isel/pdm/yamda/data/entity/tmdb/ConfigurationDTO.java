package isel.pdm.yamda.data.entity.tmdb;

/**
 * TMDbConfiguration Data Transfer Object
 * Used to store data from configuration of TMDb
 */
public class ConfigurationDTO {

    public class ImageConfigurationsDTO {
        private String base_url;
        private String[] backdrop_sizes;
        private String[] logo_sizes;
        private String[] poster_sizes;
        private String[] change_keys;
    }

    private ImageConfigurationsDTO images;

}
