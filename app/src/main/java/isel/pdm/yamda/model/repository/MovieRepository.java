package isel.pdm.yamda.model.repository;

import java.io.IOException;
import java.util.HashMap;

import isel.pdm.yamda.model.api.TheMovieAPI;
import isel.pdm.yamda.model.entity.Configuration;
import isel.pdm.yamda.model.entity.MovieListing;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Nuno on 30/10/2015.
 */
public class MovieRepository implements IMovieRepository {

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

    @Override
    public Configuration getApiConfiguration() throws IOException {
        if(apiConfiguration == null){
            apiConfiguration = api.getConfig(TheMovieAPI.API_KEY).execute().body();
        }
        return apiConfiguration;
    }

    @Override
    public HashMap<String, MovieListing> getListings() throws IOException {
        if (listings == null) {
            listings = new HashMap<>();
            if (listings.get(MovieListing.NOW_PLAYING_TAG) == null) {
                listings.put(MovieListing.NOW_PLAYING_TAG, api.getNowPlaying(TheMovieAPI.API_KEY, 1, "en").execute().body());
            }
            if (listings.get(MovieListing.UPCOMING_TAG) == null) {
                listings.put(MovieListing.UPCOMING_TAG, api.getUpcoming(TheMovieAPI.API_KEY, 1, "en").execute().body());
            }
            if (listings.get(MovieListing.POPULAR_TAG) == null) {
                listings.put(MovieListing.POPULAR_TAG, api.getMostPopular(TheMovieAPI.API_KEY, 1, "en").execute().body());
            }
        }
        return listings;
    }

    @Override
    public MovieListing getListing(String tag) throws IOException {
        if (listings == null) {
            listings = new HashMap<>();
        }
        if(listings.get(tag) == null) {
            MovieListing listing;
            switch (tag) {
                case MovieListing.NOW_PLAYING_TAG:
                    listing = api.getNowPlaying(TheMovieAPI.API_KEY, 1, "en").execute().body();
                    listings.put(tag, listing);
                    break;
                case MovieListing.POPULAR_TAG:
                    listing = api.getMostPopular(TheMovieAPI.API_KEY, 1, "en").execute().body();
                    listings.put(tag, listing);
                    break;
                case MovieListing.UPCOMING_TAG:
                    listing = api.getUpcoming(TheMovieAPI.API_KEY, 1, "en").execute().body();
                    listings.put(tag, listing);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
        return listings.get(tag);
    }

    public static MovieRepository create() throws IOException {
        return new MovieRepository(new Retrofit.Builder()
                .baseUrl(TheMovieAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TheMovieAPI.class));
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
        listings.put(MovieListing.NOW_PLAYING_TAG, api.getNowPlaying(TheMovieAPI.API_KEY, 1, "en").execute().body());
        listings.put(MovieListing.UPCOMING_TAG, api.getUpcoming(TheMovieAPI.API_KEY, 1, "en").execute().body());
        listings.put(MovieListing.POPULAR_TAG, api.getMostPopular(TheMovieAPI.API_KEY, 1, "en").execute().body());

        return new MovieRepository(api, apiConfiguration, listings);
    }
}
