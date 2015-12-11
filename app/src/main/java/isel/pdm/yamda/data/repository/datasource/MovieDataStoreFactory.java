package isel.pdm.yamda.data.repository.datasource;


import isel.pdm.yamda.data.providers.IMovieApi;
import isel.pdm.yamda.data.providers.tmdb.TheMovieDbApi;

public class MovieDataStoreFactory {

    private String language;

    public MovieDataStoreFactory(String language) {
        this.language = language;
    }

    public IMovieApi create(int id) {
        //Check if that id is cached and return diskStorage instead

        return createCloudDataStore();
    }


    public IMovieApi createCloudDataStore() {
        return new CloudMovieDataStorage(new TheMovieDbApi(this.language));
    }

    public String getLanguage() {
        return language;
    }
}
