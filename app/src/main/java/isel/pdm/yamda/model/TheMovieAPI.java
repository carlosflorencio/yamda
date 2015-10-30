package isel.pdm.yamda.model;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface TheMovieAPI {

    String BASE_URL = "http://api.themoviedb.org";

    String API_KEY = "3b4c65c3780fc1ef44ec5500b186d833";

    @GET("/3/configuration")
    Call<Configuration> getConfig(@Query("api_key") String api_key);

    /**
     *
     * @param id
     * @param api_key
     * @param lang
     * @return
     */
    @GET("/3/movie/{id}")
    Call<Movie> getMovie(@Path("id") int id,
                         @Query("api_key") String api_key,
                         @Query("language") String lang,
                         @Query("append_to_response") String response_append);

    @GET("/3/movie/now_playing")
    Call<MovieListing> getNowPlaying(@Query("api_key") String API_KEY,
                                    @Query("page") int page,
                                    @Query("language") String language);

    @GET("/3/movie/upcoming")
    Call<MovieListing> getUpcoming(@Query("api_key") String API_KEY,
                                  @Query("page") int page,
                                  @Query("language") String language);

    @GET("/3/movie/popular")
    Call<MovieListing> getMostPopular(@Query("api_key") String API_KEY,
                                     @Query("page") int page,
                                     @Query("language") String language);

    @GET("/3/movie/{id}/images")
    Call<Images> getMovieImages(@Path("id") int id,
                                @Query("api_key") String api_key,
                                @Query("language") String lang,
                                @Query("append_to_response") String response_append,
                                @Query("include_image_language") String img_lang);

}
