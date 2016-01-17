package isel.pdm.yamda;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.SystemClock;
import android.preference.PreferenceManager;

import com.squareup.picasso.Picasso;

import isel.pdm.yamda.data.services.lists.SoonListService;
import isel.pdm.yamda.data.services.lists.TheatersListService;

/**
 * Singleton class (note that we have one instance per application process) that plays the role
 * of Service Locator, decoupling the remaining code base of the concrete service provider types.
 */
public class YamdaApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        initPeriodicUpdates();

        //debug picasso
        Picasso.with(this).setLoggingEnabled(true);
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

    public void initPeriodicUpdates() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        long soonDays = Integer.parseInt(sharedPreferences.getString(getResources().getString(R.string.soon_periodicity), "7"));
        long inTheatersDays = Integer.parseInt(sharedPreferences.getString(getResources().getString(R.string.in_theaters_periodicity), "7"));

        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, TheatersListService.class);
        if (PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_NO_CREATE) == null) {
            setAlarm(alarmManager, intent, inTheatersDays);
        }

        intent = new Intent(this, SoonListService.class);
        if (PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_NO_CREATE) == null) {
            setAlarm(alarmManager, intent, soonDays);
        }
    }

    private void setAlarm(AlarmManager alarmManager, Intent intent, long days) {
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                                         SystemClock
                                                 .elapsedRealtime() + days * AlarmManager.INTERVAL_DAY,
                                         days * AlarmManager.INTERVAL_DAY,
                                         PendingIntent.getService(this, 0, intent,
                                                                  PendingIntent.FLAG_UPDATE_CURRENT));
    }

}
