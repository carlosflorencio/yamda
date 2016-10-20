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

import java.util.Calendar;
import java.util.Date;

import isel.pdm.yamda.data.services.lists.SoonListService;
import isel.pdm.yamda.data.services.lists.TheatersListService;

/**
 * Singleton class (note that we have one instance per application process) that plays the role
 * of Service Locator, decoupling the remaining code base of the concrete service provider types.
 */
public class YamdaApplication extends Application {

    private AlarmManager alarmManager;

    @Override
    public void onCreate() {
        super.onCreate();

        alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        initPeriodicUpdates();

        //debug picasso
        //Picasso.with(this).setLoggingEnabled(true);
        //Picasso.with(this).setIndicatorsEnabled(true);
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

    /**
     * Register the alarms for updating lists services
     */
    public void initPeriodicUpdates() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        int inTheatersDays = Integer.parseInt(sharedPreferences.getString(getResources().getString(R.string.in_theaters_periodicity), "7"));

        setAlarm(new Intent(this, TheatersListService.class), inTheatersDays, PendingIntent.FLAG_NO_CREATE);
        setAlarmEveryThursday(new Intent(this, SoonListService.class), PendingIntent.FLAG_NO_CREATE);
    }

    /**
     * Replace alarm
     * @param days
     */
    public void refreshTheatersAlarm(int days){
        Intent intent = new Intent(this, TheatersListService.class);
        setAlarm(intent, days, PendingIntent.FLAG_CANCEL_CURRENT);
    }

    /**
     * Set a repeating intent
     * @param intent
     * @param days
     * @param flag
     */
    private void setAlarm(Intent intent, int days, int flag) {
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                                         SystemClock
                                                 .elapsedRealtime() + days * AlarmManager.INTERVAL_DAY,
                                         days * AlarmManager.INTERVAL_DAY,
                                         PendingIntent.getService(this, 0, intent, flag));
    }

    /**
     * Repeat intent every thursday
     * @param intent
     * @param flag
     */
    private void setAlarmEveryThursday(Intent intent, int flag) {
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        Date now = new Date(System.currentTimeMillis());
        if (cal.getTime().before(now)) {
            cal.add(Calendar.WEEK_OF_MONTH, 1);
        }

        long firstTime = cal.getTime().getTime();

        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                                         firstTime,
                                         7 * AlarmManager.INTERVAL_DAY,
                                         PendingIntent.getService(this, 0, intent, flag));
    }

}
