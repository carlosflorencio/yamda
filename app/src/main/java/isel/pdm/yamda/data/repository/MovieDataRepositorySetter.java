package isel.pdm.yamda.data.repository;

import android.util.Log;

import java.util.List;

import isel.pdm.yamda.data.entity.tmdb.MovieListingDTO;
import isel.pdm.yamda.model.mapper.ModelEntitiesDataMapper;
import isel.pdm.yamda.data.repository.datasource.IMovieDataStore;
import isel.pdm.yamda.data.repository.datasource.MovieDataStoreFactory;
import isel.pdm.yamda.model.entity.Movie;
import isel.pdm.yamda.model.repository.IMovieRepository;
import isel.pdm.yamda.presentation.ILoadDataView;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * This class uses a factory and a mapper to retrieve the data to the model layer
 */
public class MovieDataRepositorySetter implements IMovieRepository {

    private final MovieDataStoreFactory factory;
    private final ModelEntitiesDataMapper mapper;

    public MovieDataRepositorySetter(MovieDataStoreFactory factory, ModelEntitiesDataMapper mapper) {
        this.factory = factory;
        this.mapper = mapper;
    }

    @Override
    public void setTheatersMovies(final ILoadDataView<List<Movie>> presenter, int page) {
        // Always get the online data ?
        final IMovieDataStore storage = this.factory.createCloudDataStore();

        storage.theaterMovieListEntity(page).enqueue(new Callback<MovieListingDTO>() {
            @Override
            public void onResponse(Response<MovieListingDTO> response, Retrofit retrofit) {
                List<Movie> list = mapper.transform(response.body());
                Log.v("DEBUG_", "onResponse " + list.size());
                presenter.setData(list);
            }

            @Override
            public void onFailure(Throwable t) {
                presenter.showError("Error!!");
            }
        });
    }

    @Override
    public void setSoonMovies(ILoadDataView<List<Movie>> presenter, int page) {
        // Always get the online data ?
        final IMovieDataStore storage = this.factory.createCloudDataStore();

        //return mapper.transform(storage.soonMovieListEntity(page));
    }

    @Override
    public void setTopMovies(ILoadDataView<List<Movie>> presenter, int page) {
        // Always get the online data ?
        final IMovieDataStore storage = this.factory.createCloudDataStore();

        //return mapper.transform(storage.topMovieListEntity(page));
    }

    @Override
    public void  setMovie(ILoadDataView<Movie> presenter,int id) {
        // get cached or online
        final IMovieDataStore storage = this.factory.create(id);

        //return mapper.transform(storage.movieEntityDetails(id));
    }
}
