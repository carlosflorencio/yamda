package isel.pdm.yamda;

import android.app.Application;
import android.content.res.Configuration;

import com.squareup.picasso.Picasso;

/**
 * Singleton class (note that we have one instance per application process) that plays the role
 * of Service Locator, decoupling the remaining code base of the concrete service provider types.
 */
public class YamdaApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        //initPeriodicUpdates();

        //debug picasso
        //Picasso.with(this).setLoggingEnabled(true);
        Picasso.with(this).setIndicatorsEnabled(true);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }

    /*
    |--------------------------------------------------------------------------
    | Custom methods
    |--------------------------------------------------------------------------
    */

//    private void initPeriodicUpdates() {
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        if (!sharedPreferences.getBoolean(getResources().getString(R.string.enable_update), true)) {
//            return;
//        }
//        int          days         = Integer.parseInt(
//                sharedPreferences.getString(getResources().getString(R.string.periodicity), "7"));
//        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
//
//        Intent intent = new Intent(this, TheatersListService.class);
//        if (PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_NO_CREATE) == null) {
//            intent.putExtra(ListService.IGNORE_DISK, true);
//            alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
//                                             SystemClock
//                                                     .elapsedRealtime() + days * AlarmManager.INTERVAL_DAY,
//                                             days * AlarmManager.INTERVAL_DAY,
//                                             PendingIntent.getService(this, 0, intent,
//                                                                      PendingIntent.FLAG_UPDATE_CURRENT));
//        }
//
//        intent = new Intent(this, SoonListService.class);
//        if (PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_NO_CREATE) == null) {
//            intent.putExtra(ListService.IGNORE_DISK, true);
//            alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
//                                             SystemClock
//                                                     .elapsedRealtime() + days * AlarmManager.INTERVAL_DAY,
//                                             days * AlarmManager.INTERVAL_DAY,
//                                             PendingIntent.getService(this, 0, intent,
//                                                                      PendingIntent.FLAG_UPDATE_CURRENT));
//        }
//    }

}
