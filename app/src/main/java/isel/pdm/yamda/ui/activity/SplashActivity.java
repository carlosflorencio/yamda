package isel.pdm.yamda.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import isel.pdm.yamda.R;
import isel.pdm.yamda.data.exception.FailedGettingDataException;
import isel.pdm.yamda.data.provider.table.MoviesTable;
import isel.pdm.yamda.data.repository.base.ILocalMovieRepository;
import isel.pdm.yamda.data.repository.base.IMovieRepository;
import isel.pdm.yamda.data.repository.base.MovieRepositoryFactory;
import isel.pdm.yamda.model.MovieListDetails;
import isel.pdm.yamda.ui.activity.base.LoggingActivity;

/**
 * This activity must be the launcher one, is purpose is to download data if the content provider
 * is empty
 */
public class SplashActivity extends LoggingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new LoadDataTask().execute();
    }

    /**
     * Download data from the cloud if the local repo (content provider) is empty
     */
    private class LoadDataTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            //launch home activity
            Intent i = new Intent(SplashActivity.this, HomeActivity.class);
            startActivity(i);
            Log.d(TAG, "onPostExecute: Starting home activity");
        }

        @Override
        protected Void doInBackground(Void... params) {
            ILocalMovieRepository localRepo = MovieRepositoryFactory
                    .getLocalRepository(SplashActivity.this);

            // TODO: 13/01/2016 check internet connection
            try {
                //Check if local repo has movies, if not download!
                if(!localRepo.hasMovies()) {
                    IMovieRepository cloudRepo = MovieRepositoryFactory.getCloudRepository();
                    List<MovieListDetails> now = cloudRepo.getTheatersMovies(1);
                    List<MovieListDetails> soon = cloudRepo.getSoonMovies(1);

                    localRepo.insertMovies(now, MoviesTable.TYPE_NOW);
                    localRepo.insertMovies(soon, MoviesTable.TYPE_SOON);
                    Log.d(TAG, "Inserted movies list in the local repo!");
                } else {
                    Log.d(TAG, "There are already movies in the repo, no downloads!");
                }

            } catch (FailedGettingDataException e) {
                Log.d(TAG, "Failed getting data! Error: " + e.getMessage());
            }

            // Loading data is so fast that we really want a small delay to see the splash
            try {
                Thread.sleep(2000);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            return null;
        }
    }
}
