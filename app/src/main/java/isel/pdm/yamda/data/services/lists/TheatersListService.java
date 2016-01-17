package isel.pdm.yamda.data.services.lists;

import android.content.Intent;
import android.util.Log;

import java.util.List;

import isel.pdm.yamda.data.exception.FailedGettingDataException;
import isel.pdm.yamda.data.provider.MoviesContract;
import isel.pdm.yamda.data.repository.base.MovieRepositoryFactory;
import isel.pdm.yamda.data.services.ListService;
import isel.pdm.yamda.model.Movie;

/**
 * Class used to //TODO: comentary
 */
public class TheatersListService extends ListService {

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            int page = intent.getIntExtra(PAGE, 1);
            List<Movie> movies = MovieRepositoryFactory.getCloudRepository().getTheatersMovies(page);

            MovieRepositoryFactory.getLocalRepository(getApplication()).deleteMovies(MoviesContract.MovieEntry.TYPE_NOW);
            if(MovieRepositoryFactory.getLocalRepository(getApplication()).insertMovies(movies, MoviesContract.MovieEntry.TYPE_NOW)<=0){
                Log.d(TAG, "onHandleIntent: (Theaters) nothing has been inserted");
            }
        } catch (FailedGettingDataException e) {
            Log.d(TAG, "onHandleIntent: error loading from web");
        }
    }

}
