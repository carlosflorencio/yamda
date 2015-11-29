package isel.pdm.yamda.data.entity;

/**
 * This entity represents a global configuration contract for all the apis
 */
public interface IConfiguration {

    String getImagesURI();

    String getListPosterSize();

    String getBackdropSize();

    String getDetailPosterSize();

    String getGenre(int id);
}
