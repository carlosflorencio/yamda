package isel.pdm.yamda.data.repository.datasource;


import isel.pdm.yamda.data.api.TheMovieDbApi;
import isel.pdm.yamda.data.entity.TMDbConfiguration;

public class MovieDataStoreFactory {

    public IMovieDataStore create(int id) {
        //Check if that id is cached and return diskStorage instead

        return createCloudDataStore();
    }

    public IMovieDataStore createCloudDataStore() {
        return new CloudMovieDataStorage(new TheMovieDbApi());
    }

}
