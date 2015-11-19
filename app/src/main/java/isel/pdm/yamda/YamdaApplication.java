package isel.pdm.yamda;

import android.app.Application;
import android.content.res.Configuration;

import isel.pdm.yamda.data.repository.IMovieRepository;
import isel.pdm.yamda.data.repository.MovieRepository;
import isel.pdm.yamda.data.repository.datasource.MovieDataStoreFactory;
import isel.pdm.yamda.model.mapper.ModelEntitiesDataMapper;
import isel.pdm.yamda.presentation.navigator.Navigator;

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

    /**
     * Manage navigation between Activities
     */
    protected Navigator navigator;

    @Override
    public void onCreate() {
        super.onCreate();

        initLocaleConfiguration(getResources().getConfiguration());
        initMovieRepository();
        this.navigator = new Navigator(); //make singleton?
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
     * Constructs the movie repository setter
     */
    private void initMovieRepository() {
        ModelEntitiesDataMapper dataMapper = new ModelEntitiesDataMapper();
        MovieDataStoreFactory dataFactory = new MovieDataStoreFactory(language);

        this.movieRepository = new MovieRepository(dataFactory, dataMapper);
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

    public Navigator getNavigator() {
        return this.navigator;
    }
}
