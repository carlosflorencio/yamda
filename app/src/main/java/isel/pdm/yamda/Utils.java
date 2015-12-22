package isel.pdm.yamda;

import android.content.Context;
import android.net.ConnectivityManager;

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
}
