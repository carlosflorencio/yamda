package isel.pdm.yamda;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

/**
 * Utility class that provides some usefull methods for the app
 */
public class Utils {

    /**
     * Checks if there is network connectivity
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    /**
     * Shows a {@link android.widget.Toast} message.
     *
     * @param activity Activity to show
     * @param message An string representing a message to be shown.
     */
    public static void showToastMessage(Activity activity, String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }
}
