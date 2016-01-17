package isel.pdm.yamda.data.api;

import isel.pdm.yamda.data.api.entity.CreditsListingDTO;
import isel.pdm.yamda.data.api.entity.GenreListingDTO;
import isel.pdm.yamda.data.api.entity.MovieDTO;
import isel.pdm.yamda.data.api.entity.MovieListingDTO;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Retrieves information from online database through {@code Retrofit}.
 */
public interface ITMDbServiceAPI {
    //TODO: change this to a gradle property?
    String API_KEY = "3b4c65c3780fc1ef44ec5500b186d833";
    String BASE_URL = "http://api.themoviedb.org";
    String BASE_IMAGES_URL = "http://image.tmdb.org/t/p/";
    String POSTER_SIZE = "w185";
    String BACKDROP_SIZE = "w780";

    /**
     * Retrieves a specific movie through his {@code id}
     *
     * @param id      movie id
     * @param api_key api_key used to retrieve information
     * @param lang    to retrieve in this language (ISO 639-1 code)
     * @return
     */
    @GET("/3/movie/{id}")
    Call<MovieDTO> getMovie(@Path("id") int id,
                            @Query("api_key") String api_key,
                            @Query("language") String lang);

    /**
     * Retrieves a list of movies that are now on cinema.
     *
     * @param API_KEY  api_key used to retrieve information
     * @param page     page (Minimum 1, maximum 1000)
     * @param language to retrieve in this language (ISO 639-1 code)
     * @return
     */
    @GET("/3/movie/now_playing")
    Call<MovieListingDTO> getNowPlaying(@Query("api_key") String API_KEY,
                                        @Query("page") int page,
                                        @Query("language") String language);

    /**
     * Retrieves a list of movies that are to come to cinema.
     *
     * @param API_KEY  api_key used to retrieve information
     * @param page     page (Minimum 1, maximum 1000)
     * @param language to retrieve in this language (ISO 639-1 code)
     * @return
     */
    @GET("/3/movie/upcoming")
    Call<MovieListingDTO> getUpcoming(@Query("api_key") String API_KEY,
                                      @Query("page") int page,
                                      @Query("language") String language);

    /**
     * Retrieves a list of movies that are the most popular on TMDb.
     *
     * @param API_KEY  api_key used to retrieve information
     * @param page     page (Minimum 1, maximum 1000)
     * @param language to retrieve in this language (ISO 639-1 code)
     * @return
     */
    @GET("/3/movie/popular")
    Call<MovieListingDTO> getMostPopular(@Query("api_key") String API_KEY,
                                         @Query("page") int page,
                                         @Query("language") String language);

    /**
     * Retrieves a list of movies by title
     *
     * @param API_KEY api_key used to retrieve information
     * @param movie   movie name
     * @param page    page (Minimum 1, maximum 1000)
     * @param lang    to retrieve in this language (ISO 639-1 code)
     * @return
     */
    @GET("/3/search/movie")
    Call<MovieListingDTO> getSearchedMovies(@Query("api_key") String API_KEY,
                                            @Query("query") String movie,
                                            @Query("page") int page,
                                            @Query("language") String lang);

    /**
     * Retrieves a lisf of the movies genres
     *
     * @param API_KEY
     * @param language
     * @return
     */
    @GET("/3/genre/movie/list")
    Call<GenreListingDTO> getGenres(@Query("api_key") String API_KEY,
                                    @Query("language") String language);

    /**
     * Retrieves the credits of a movie
     *
     * @param movieId
     * @param API_KEY
     * @param language
     * @return
     */
    @GET("/3/movie/{movie}/credits")
    Call<CreditsListingDTO> getMovieCredits(@Path("movie") int movieId,
                                            @Query("api_key") String API_KEY,
                                            @Query("language") String language);

}
