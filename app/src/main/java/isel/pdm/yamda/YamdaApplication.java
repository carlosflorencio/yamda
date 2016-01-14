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

import isel.pdm.yamda.data.services.ListService;
import isel.pdm.yamda.data.services.lists.SoonListService;
import isel.pdm.yamda.data.services.lists.TheatersListService;

/**
 * Singleton class (note that we have one instance per application process) that plays the role
 * of Service Locator, decoupling the remaining code base of the concrete service provider types.
 */
public class YamdaApplication extends Application {

    /**
     * System language (ex: pt, en)
     */
    private String language;


    @Override
    public void onCreate() {
        super.onCreate();

        initLocaleConfiguration(getResources().getConfiguration());
        initPeriodicUpdates();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        initLocaleConfiguration(newConfig);
    }

    /*
    |--------------------------------------------------------------------------
    | Custom methods
    |--------------------------------------------------------------------------
    */

    /**
     * Initializes the locale dependent fields (language)
     */
    private void initLocaleConfiguration(Configuration config) {
        language = config.locale.getLanguage();
    }


    private void initPeriodicUpdates() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (!sharedPreferences.getBoolean(getResources().getString(R.string.enable_update), true)) {
            return;
        }
        int          days         = Integer.parseInt(
                sharedPreferences.getString(getResources().getString(R.string.periodicity), "7"));
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, TheatersListService.class);
        if (PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_NO_CREATE) == null) {
            intent.putExtra(ListService.IGNORE_DISK, true);
            alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                                             SystemClock
                                                     .elapsedRealtime() + days * AlarmManager.INTERVAL_DAY,
                                             days * AlarmManager.INTERVAL_DAY,
                                             PendingIntent.getService(this, 0, intent,
                                                                      PendingIntent.FLAG_UPDATE_CURRENT));
        }

        intent = new Intent(this, SoonListService.class);
        if (PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_NO_CREATE) == null) {
            intent.putExtra(ListService.IGNORE_DISK, true);
            alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                                             SystemClock
                                                     .elapsedRealtime() + days * AlarmManager.INTERVAL_DAY,
                                             days * AlarmManager.INTERVAL_DAY,
                                             PendingIntent.getService(this, 0, intent,
                                                                      PendingIntent.FLAG_UPDATE_CURRENT));
        }
    }

    public String getLanguage() {
        return language;
    }

}
