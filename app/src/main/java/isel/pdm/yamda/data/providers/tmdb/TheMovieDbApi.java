package isel.pdm.yamda.data.providers.tmdb;

import java.io.IOException;

import isel.pdm.yamda.data.entity.tmdb.ConfigurationDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieListingDTO;
import isel.pdm.yamda.data.providers.IMovieApi;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Concrete implementation of the MovieApi for the TMDb Service
 */
public class TheMovieDbApi implements IMovieApi {

    private ITheMovieDbServiceAPI api;
    private String language;

    public TheMovieDbApi(String language) {
        //TODO: DI ?
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ITheMovieDbServiceAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.api = retrofit.create(ITheMovieDbServiceAPI.class);
        this.language = language;
    }

    /**
     * {@inheritDoc}
     **/
    @Override
    public ConfigurationDTO getApiConfiguration() throws IOException {
        return this.api.getConfig(ITheMovieDbServiceAPI.API_KEY).execute().body();
    }

    /**
     * {@inheritDoc}
     **/
    @Override
    public MovieListingDTO getTheatersMovies(int page) throws IOException {
        return this.api.getNowPlaying(ITheMovieDbServiceAPI.API_KEY, page, this.language).execute().body();
    }

    /**
     * {@inheritDoc}
     **/
    @Override
    public MovieListingDTO getSoonMovies(int page) throws IOException {
        return this.api.getUpcoming(ITheMovieDbServiceAPI.API_KEY, page, this.language).execute().body();
    }

    /**
     * {@inheritDoc}
     **/
    @Override
    public MovieListingDTO getTopMovies(int page) throws IOException {
        return this.api.getMostPopular(ITheMovieDbServiceAPI.API_KEY, page, this.language).execute().body();
    }

    /**
     * {@inheritDoc}
     **/
    @Override
    public MovieDTO getMovie(int id) throws IOException {
        return this.api.getMovie(id, ITheMovieDbServiceAPI.API_KEY, this.language,
                "credits") //Append credits (crew and actors)
                .execute().body();
    }

    @Override
    public MovieListingDTO getMoviesSearch(String search, int page) throws IOException {
        return this.api
                .getSearchedMovies(ITheMovieDbServiceAPI.API_KEY, search, page, this.language).execute().body();
    }
}
