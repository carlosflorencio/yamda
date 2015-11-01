package isel.pdm.yamda.data.api;

import java.io.IOException;
import java.util.Locale;

import isel.pdm.yamda.data.api.common.IMovieApi;
import isel.pdm.yamda.data.entity.tmdb.ConfigurationDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieDTO;
import isel.pdm.yamda.data.entity.tmdb.MovieListingDTO;
import isel.pdm.yamda.data.exception.ApiFailedGettingDataException;
import retrofit.Retrofit;

/**
 * Concrete implementation of the MovieApi for the TMDb Service
 */
public class TheMovieDbApi implements IMovieApi {

    private ITheMovieDbServiceAPI api;
    private String language;

    public TheMovieDbApi() {
        //TODO: DI ?
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ITheMovieDbServiceAPI.BASE_URL).build();
        this.api = retrofit.create(ITheMovieDbServiceAPI.class);
        this.language = Locale.getDefault().getDisplayLanguage();
    }

    /** {@inheritDoc} **/
    @Override
    public ConfigurationDTO getApiConfiguration() throws ApiFailedGettingDataException {
        try {
            return this.api.getConfig(ITheMovieDbServiceAPI.API_KEY).execute().body();
        } catch (IOException e) {
            throw new ApiFailedGettingDataException(e);
        }
    }

    /** {@inheritDoc} **/
    @Override
    public MovieListingDTO getTheatersMovies(int page) throws ApiFailedGettingDataException {
        try {
            return this.api.getNowPlaying(ITheMovieDbServiceAPI.API_KEY, page, this.language).execute().body();
        } catch (IOException e) {
            throw new ApiFailedGettingDataException(e);
        }
    }

    /** {@inheritDoc} **/
    @Override
    public MovieListingDTO getSoonMovies(int page) throws ApiFailedGettingDataException {
        try {
            return this.api.getUpcoming(ITheMovieDbServiceAPI.API_KEY, page, this.language).execute().body();
        } catch (IOException e) {
            throw new ApiFailedGettingDataException(e);
        }
    }

    /** {@inheritDoc} **/
    @Override
    public MovieListingDTO getTopMovies(int page) throws ApiFailedGettingDataException {
        try {
            return this.api.getMostPopular(ITheMovieDbServiceAPI.API_KEY, page, this.language).execute().body();
        } catch (IOException e) {
            throw new ApiFailedGettingDataException(e);
        }
    }

    /** {@inheritDoc} **/
    @Override
    public MovieDTO getMovie(int id) throws ApiFailedGettingDataException {
        try {
            return this.api.getMovie(id, ITheMovieDbServiceAPI.API_KEY,  this.language).execute().body();
        } catch (IOException e) {
            throw new ApiFailedGettingDataException(e);
        }
    }
}
