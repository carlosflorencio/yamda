package isel.pdm.yamda.data.repository.datasource;



/**
 * This factory contains all the data store's types of our application
 * cloud, memory, disk (content provider)
 * !!Coupled to the api!!
 */
public class MovieDataStoreFactory {

    private CloudMovieDataStorage cloud;

    /**
     * Creates a new instance of MovieDataStoreFactory with
     */
    public MovieDataStoreFactory(CloudMovieDataStorage cloud) {
        this.cloud = cloud;
    }

    /**
     * Get the Cloud Movie Data Store witch obtains data from api
     *
     * @return
     */
    public CloudMovieDataStorage getCloudMovieDataStore() {
        return this.cloud;
    }
}
