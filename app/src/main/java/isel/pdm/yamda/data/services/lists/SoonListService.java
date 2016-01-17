package isel.pdm.yamda.data.services.lists;

import android.content.Intent;
import android.util.Log;

import java.util.List;

import isel.pdm.yamda.data.exception.FailedGettingDataException;
import isel.pdm.yamda.data.provider.MoviesContract;
import isel.pdm.yamda.data.repository.base.ICloudMovieRepository;
import isel.pdm.yamda.data.repository.base.ILocalMovieRepository;
import isel.pdm.yamda.data.repository.base.MovieRepositoryFactory;
import isel.pdm.yamda.data.services.ListService;
import isel.pdm.yamda.model.Movie;

/**
 * This service runs every thursday and its purpose is to fetch the upcoming movies
 * and save it in the local repo
 */
public class SoonListService extends ListService {

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            ICloudMovieRepository cloudRepo = MovieRepositoryFactory.getCloudRepository();
            ILocalMovieRepository localRepo = MovieRepositoryFactory.getLocalRepository(getApplicationContext());
            int page = intent.getIntExtra(PAGE, 1);
            List<Movie> movies = cloudRepo.getSoonMovies(page);

           localRepo.deleteMovies(MoviesContract.MovieEntry.TYPE_SOON);
            if(localRepo.insertMovies(movies, MoviesContract.MovieEntry.TYPE_SOON) <= 0){
                Log.d(TAG, "onHandleIntent: (Soon) nothing has been inserted");
            }
        } catch (FailedGettingDataException e) {
            Log.d(TAG, "onHandleIntent: error loading from web");
        }
    }

}
