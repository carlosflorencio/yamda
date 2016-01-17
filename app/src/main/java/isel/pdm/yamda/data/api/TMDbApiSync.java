package isel.pdm.yamda.data.api;

import java.io.IOException;
import java.util.Locale;

import isel.pdm.yamda.data.api.entity.CreditsListingDTO;
import isel.pdm.yamda.data.api.entity.GenreListingDTO;
import isel.pdm.yamda.data.api.entity.MovieDTO;
import isel.pdm.yamda.data.api.entity.MovieListingDTO;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Concrete implementation of TMDb Service API
 * Used to get Movie Data DTO's from HTTP Requests Synchronously
 * !!Coupled to Retrofit!!
 */
public class TMDbApiSync {

    private ITMDbServiceAPI api;
    private String language;

    public TMDbApiSync() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ITMDbServiceAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.api = retrofit.create(ITMDbServiceAPI.class);
        this.language = Locale.getDefault().getLanguage();
    }

    /**
     * Get the movies in the theaters right now
     * Synchronously
     *
     * @throws IOException
     */
    public MovieListingDTO getTheatersMovies(int page) throws IOException {
        return this.api.getNowPlaying(ITMDbServiceAPI.API_KEY, page, this.language).execute()
                       .body();
    }

    /**
     * Get next movies getting into theaters
     *
     * @throws IOException
     */
    public MovieListingDTO getSoonMovies(int page) throws IOException {
        return this.api.getUpcoming(ITMDbServiceAPI.API_KEY, page, this.language).execute().body();
    }

    /**
     * Get the top movies in theaters
     * Synchronously
     *
     * @throws IOException
     */
    public MovieListingDTO getTopMovies(int page) throws IOException {
        return this.api.getMostPopular(ITMDbServiceAPI.API_KEY, page, this.language).execute()
                       .body();
    }

    /**
     * Get a movie details
     * Synchronously
     *
     * @throws IOException
     */
    public MovieDTO getMovie(int id) throws IOException {
        return this.api.getMovie(id, ITMDbServiceAPI.API_KEY, this.language)
                       .execute().body();
    }

    /**
     * Get the search result from the api given a search query and page
     * Synchronously
     *
     * @throws IOException
     */
    public MovieListingDTO getMoviesSearch(String search, int page) throws IOException {
        return this.api
                .getSearchedMovies(ITMDbServiceAPI.API_KEY, search, page, this.language).execute()
                .body();
    }

    /**
     * Get the genres list
     *
     * @return
     * @throws IOException
     */
    public GenreListingDTO getGenres() throws IOException {
        return this.api.getGenres(ITMDbServiceAPI.API_KEY, this.language).execute().body();
    }

    /**
     * Get the credits of a movie
     *
     * @param movieId
     * @return
     * @throws IOException
     */
    public CreditsListingDTO getMovieCredits(int movieId) throws IOException {
        return this.api.getMovieCredits(movieId, ITMDbServiceAPI.API_KEY, this.language).execute()
                       .body();
    }
}
