package isel.pdm.yamda.data.entity.tmdb;

/**
 * TMDbConfiguration Data Transfer Object
 * Used to store data from configuration of TMDb
 */
public class ConfigurationDTO {

    /**
     * This DTO contains the information about the images urls from the API
     */
    public static class ImageConfigurationsDTO {
        private String base_url;
        private String[] backdrop_sizes;
        private String[] logo_sizes;
        private String[] poster_sizes;
        private String[] change_keys;

        public String getBaseUrl() {
            return base_url;
        }

        public String[] getBackdropSizes() {
            return backdrop_sizes;
        }

        public String[] getLogoSizes() {
            return logo_sizes;
        }

        public String[] getPosterSizes() {
            return poster_sizes;
        }

        public String[] getChangeKeys() {
            return change_keys.clone();
        }
    }

    private ImageConfigurationsDTO images;

    public ImageConfigurationsDTO getImages() {
        return images;
    }

}
