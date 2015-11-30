package isel.pdm.yamda.data.entity;

/**
 * This entity represents a global configuration contract for all the apis
 */
public interface IConfiguration {

    /**
     * Get the images url from the api
     * @return
     */
    String getImagesURI();

    /**
     * Get the list with the poster sizes
     * @return
     */
    String getListPosterSize();

    /**
     * Get the backdrop image size
     * @return
     */
    String getBackdropSize();

    /**
     * Get the detail poster size
     * @return
     */
    String getDetailPosterSize();

    /**
     * Get the genre name for a given id
     * @param id
     * @return
     */
    String getGenre(int id);
}
