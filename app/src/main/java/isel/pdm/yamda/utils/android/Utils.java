package isel.pdm.yamda.utils.android;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Class used to //TODO: comentary
 */
public class Utils {

    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}
