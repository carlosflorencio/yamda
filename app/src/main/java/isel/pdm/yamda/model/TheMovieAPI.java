package isel.pdm.yamda.model;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface TheMovieAPI {

    String BASE_URL = "https://api.themovieadb.org";

    String API_KEY = "3b4c65c3780fc1ef44ec5500b186d833";

    @GET("/movie/{id}")
    Call<Movie> getMovie(@Path("id") String id,
                         @Query("api_key") String API_KEY,
                         @Query("language") String lang,
                         @Query("append_to_response") String response_append);

    @GET("/movie/now_playing")
    Call<List<Movie>> getNowPlaying(@Query("api_key") String API_KEY,
                                    @Query("page") String page,
                                    @Query("language") String language);

    @GET("/movie/upcoming")
    Call<List<Movie>> getUpcoming(@Query("api_key") String API_KEY,
                                  @Query("page") String page,
                                  @Query("language") String language);

    @GET("/movie/popular")
    Call<List<Movie>> getMostPopular(@Query("api_key") String API_KEY,
                                     @Query("page") String page,
                                     @Query("language") String language);

}
