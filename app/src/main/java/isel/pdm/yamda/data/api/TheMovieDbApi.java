package isel.pdm.yamda.data.api;

import android.app.Application;
import android.util.Log;

import java.util.Locale;

import isel.pdm.yamda.YamdaApplication;
import isel.pdm.yamda.data.api.common.IMovieApi;
import isel.pdm.yamda.data.entity.tmdb.ConfigurationDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieListingDTO;
import isel.pdm.yamda.data.exception.ApiFailedGettingDataException;
import retrofit.Call;
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

    /** {@inheritDoc} **/
    @Override
    public Call<ConfigurationDTO> getApiConfiguration() {
        return this.api.getConfig(ITheMovieDbServiceAPI.API_KEY);
    }

    /** {@inheritDoc} **/
    @Override
    public Call<MovieListingDTO> getTheatersMovies(int page) {
       return this.api.getNowPlaying(ITheMovieDbServiceAPI.API_KEY, page, this.language);
    }

    /** {@inheritDoc} **/
    @Override
    public Call<MovieListingDTO> getSoonMovies(int page) {
        return this.api.getUpcoming(ITheMovieDbServiceAPI.API_KEY, page, this.language);
    }

    /** {@inheritDoc} **/
    @Override
    public Call<MovieListingDTO> getTopMovies(int page) {
        return this.api.getMostPopular(ITheMovieDbServiceAPI.API_KEY, page, this.language);
    }

    /** {@inheritDoc} **/
    @Override
    public Call<MovieDTO> getMovie(int id) {
        return this.api.getMovie(id, ITheMovieDbServiceAPI.API_KEY,  this.language);
    }

    @Override
    public Call<MovieListingDTO> getMoviesSearch(String search, int page) {
        return this.api.getSearchedMovies(ITheMovieDbServiceAPI.API_KEY, search, page, this.language);
    }
}
