package isel.pdm.yamda.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Nuno on 30/10/2015.
 */
public class MovieRepository {

    private TheMovieAPI api;

    private Configuration apiConfiguration;

    private HashMap<String, MovieListing> listings;

    public MovieRepository(TheMovieAPI api, Configuration apiConfiguration, HashMap<String, MovieListing> listings) {
        this.api = api;
        this.apiConfiguration = apiConfiguration;
        this.listings = listings;
    }

    public MovieRepository(TheMovieAPI api) {
        this.api = api;
    }

    public Configuration getApiConfiguration() throws IOException {
        if(apiConfiguration == null){
            apiConfiguration = api.getConfig(TheMovieAPI.API_KEY).execute().body();
        }
        return apiConfiguration;
    }

    public HashMap<String, MovieListing> getListings() {
        return listings;
    }

    public static MovieRepository createAll() throws IOException {
        TheMovieAPI api = new Retrofit.Builder()
                .baseUrl(TheMovieAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TheMovieAPI.class);

        Configuration apiConfiguration = api.getConfig(TheMovieAPI.API_KEY)
                .execute()
                .body();

        HashMap<String, MovieListing> listings = new HashMap<>();
        listings.put(api.getNowPlaying(TheMovieAPI.API_KEY, 1, "en").execute().body());
        listings.add(api.getUpcoming(TheMovieAPI.API_KEY, 1, "en").execute().body());
        listings.add(api.getMostPopular(TheMovieAPI.API_KEY, 1, "en").execute().body());

        return new MovieRepository(api, apiConfiguration, listings);
    }
}
