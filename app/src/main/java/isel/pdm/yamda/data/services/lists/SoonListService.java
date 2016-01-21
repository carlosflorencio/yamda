package isel.pdm.yamda.data.services.lists;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import java.util.List;

import isel.pdm.yamda.R;
import isel.pdm.yamda.data.exception.FailedGettingDataException;
import isel.pdm.yamda.data.provider.MoviesContract;
import isel.pdm.yamda.data.repository.base.ICloudMovieRepository;
import isel.pdm.yamda.data.repository.base.ILocalMovieRepository;
import isel.pdm.yamda.data.repository.base.MovieRepositoryFactory;
import isel.pdm.yamda.data.services.ListService;
import isel.pdm.yamda.model.Movie;
import isel.pdm.yamda.ui.activity.MovieActivity;

/**
 * This service runs every thursday and its purpose is to fetch the upcoming movies
 * and save it in the local repo
 */
public class SoonListService extends ListService {

    @Override
    protected void onHandleIntent(Intent intent) {
        if(!canDownload()) return;

        try {
            ICloudMovieRepository cloudRepo = MovieRepositoryFactory.getCloudRepository();
            ILocalMovieRepository localRepo = MovieRepositoryFactory.getLocalRepository(getApplicationContext());

            int page = intent.getIntExtra(PAGE, 1);
            List<Movie> movies = cloudRepo.getSoonMovies(page);

            for (Movie movie : localRepo.getSoonMovies()) {
                checkForNotifications(movie, localRepo.isBeingFollowed(movie.getId()));
            }

           localRepo.deleteMovies(MoviesContract.MovieEntry.TYPE_SOON);
            if(localRepo.insertMovies(movies, MoviesContract.MovieEntry.TYPE_SOON) <= 0){
                Log.d(TAG, "onHandleIntent: (Soon) nothing has been inserted");
            }
        } catch (FailedGettingDataException e) {
            Log.d(TAG, "onHandleIntent: error loading from web");
        }
    }

    private void checkForNotifications(Movie movie, boolean follow){
        if(follow){
            NotificationManager notificationManager =
                    (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notification = getNotificationReleased(movie);
            notificationManager.notify(movie.getId(), notification);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private Notification getNotificationReleased(Movie movie) {
        Intent intent = new Intent(getApplicationContext(),
                MovieActivity.class);      // Activity instantiated after clicking notification
        intent.putExtra(MovieActivity.ID_TAG, movie.getId());       // id of movie

        PendingIntent pIntent = TaskStackBuilder.create(getApplicationContext())
                .addParentStack(MovieActivity.class)
                .addNextIntent(intent)
                .getPendingIntent(0, PendingIntent.FLAG_ONE_SHOT);

        return new NotificationCompat.Builder(getApplicationContext())
                .setContentTitle(
                        movie.getTitle())                                  // Title of notification
                .setContentText(getResources().getString(
                        R.string.movie_released))                                  // Text of notification
                .setSmallIcon(
                        R.drawable.yamda)                                 // Icon of notification
                .setContentIntent(pIntent).build();
    }

}
