package isel.pdm.yamda;

import android.app.Application;
import android.content.res.Configuration;

import isel.pdm.yamda.data.repository.MovieDataRepositorySetter;
import isel.pdm.yamda.data.repository.datasource.MovieDataStoreFactory;
import isel.pdm.yamda.model.mapper.ModelEntitiesDataMapper;
import isel.pdm.yamda.model.repository.IMovieRepository;

/**
 * Singleton class (note that we have one instance per application process) that plays the role
 * of Service Locator, decoupling the remaining code base of the concrete service provider types.
 */
public class YamdaApplication extends Application {

    /**
     * System language (ex: pt, en)
     */
    private String language;

    /**
     * Instance of movie repository
     */
    private IMovieRepository movieRepository;

    @Override
    public void onCreate() {
        super.onCreate();

        initLocaleConfiguration(getResources().getConfiguration());
        initMovieRepository();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        initLocaleConfiguration(newConfig);
    }

    /*
    |--------------------------------------------------------------------------
    | Custom methods
    |--------------------------------------------------------------------------
    */
    /**
     * Constructs the movie repostory setter
     */
    private void initMovieRepository() {
        ModelEntitiesDataMapper dataMapper = new ModelEntitiesDataMapper();
        MovieDataStoreFactory dataFactory = new MovieDataStoreFactory(language);

        this.movieRepository = new MovieDataRepositorySetter(dataFactory, dataMapper);
    }

    /**
     * Initializes the locale dependent fields (language)
     */
    private void initLocaleConfiguration(Configuration config) {
        language = config.locale.getLanguage();
        initMovieRepository(); //Language changed? yes? create a new repo.. no? create anyway :D
    }

    public String getLanguage() {
        return language;
    }

    public IMovieRepository getMovieRepository() {
        return movieRepository;
    }
}
