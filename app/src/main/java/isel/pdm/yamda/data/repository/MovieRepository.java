package isel.pdm.yamda.data.repository;

import java.io.IOException;
import java.util.HashMap;

import isel.pdm.yamda.data.api.TheMovieAPI;
import isel.pdm.yamda.data.entity.ConfigurationDTO;
import isel.pdm.yamda.data.entity.MovieListingDTO;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Nuno on 30/10/2015.
 */
public class MovieRepository implements IMovieRepository {

    private TheMovieAPI api;

    private ConfigurationDTO apiConfiguration;

    private HashMap<String, MovieListingDTO> listings;

    public MovieRepository(TheMovieAPI api, ConfigurationDTO apiConfiguration, HashMap<String, MovieListingDTO> listings) {
        this.api = api;
        this.apiConfiguration = apiConfiguration;
        this.listings = listings;
    }

    public MovieRepository(TheMovieAPI api) {
        this.api = api;
    }

    @Override
    public ConfigurationDTO getApiConfiguration() throws IOException {
        if(apiConfiguration == null){
            apiConfiguration = api.getConfig(TheMovieAPI.API_KEY).execute().body();
        }
        return apiConfiguration;
    }

    @Override
    public HashMap<String, MovieListingDTO> getListings() throws IOException {
        if (listings == null) {
            listings = new HashMap<>();
            if (listings.get(MovieListingDTO.NOW_PLAYING_TAG) == null) {
                listings.put(MovieListingDTO.NOW_PLAYING_TAG, api.getNowPlaying(TheMovieAPI.API_KEY, 1, "en").execute().body());
            }
            if (listings.get(MovieListingDTO.UPCOMING_TAG) == null) {
                listings.put(MovieListingDTO.UPCOMING_TAG, api.getUpcoming(TheMovieAPI.API_KEY, 1, "en").execute().body());
            }
            if (listings.get(MovieListingDTO.POPULAR_TAG) == null) {
                listings.put(MovieListingDTO.POPULAR_TAG, api.getMostPopular(TheMovieAPI.API_KEY, 1, "en").execute().body());
            }
        }
        return listings;
    }

    @Override
    public MovieListingDTO getListing(String tag) throws IOException {
        if (listings == null) {
            listings = new HashMap<>();
        }
        if(listings.get(tag) == null) {
            MovieListingDTO listing;
            switch (tag) {
                case MovieListingDTO.NOW_PLAYING_TAG:
                    listing = api.getNowPlaying(TheMovieAPI.API_KEY, 1, "en").execute().body();
                    listings.put(tag, listing);
                    break;
                case MovieListingDTO.POPULAR_TAG:
                    listing = api.getMostPopular(TheMovieAPI.API_KEY, 1, "en").execute().body();
                    listings.put(tag, listing);
                    break;
                case MovieListingDTO.UPCOMING_TAG:
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

        ConfigurationDTO apiConfiguration = api.getConfig(TheMovieAPI.API_KEY)
                .execute()
                .body();

        HashMap<String, MovieListingDTO> listings = new HashMap<>();
        listings.put(MovieListingDTO.NOW_PLAYING_TAG, api.getNowPlaying(TheMovieAPI.API_KEY, 1, "en").execute().body());
        listings.put(MovieListingDTO.UPCOMING_TAG, api.getUpcoming(TheMovieAPI.API_KEY, 1, "en").execute().body());
        listings.put(MovieListingDTO.POPULAR_TAG, api.getMostPopular(TheMovieAPI.API_KEY, 1, "en").execute().body());

        return new MovieRepository(api, apiConfiguration, listings);
    }
}
