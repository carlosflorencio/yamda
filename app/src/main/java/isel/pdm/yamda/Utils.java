package isel.pdm.yamda;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

import isel.pdm.yamda.model.Genre;

/**
 * Utility class that provides some useful methods for the app
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

    /**
     * Create a nice time string from a runtime
     * @param runtime
     * @return
     */
    public static String createRuntimeText(int runtime) {
        if(runtime == 0) return "";

        int hours = runtime / 60;
        int minutes = runtime % 60;
        return hours + "h " + minutes + "m";
    }

    /**
     * Create a genres string from an array
     * @param genres
     * @return
     */
    public static String createGenreText(Genre[] genres) {
        StringBuilder stringbuilder = new StringBuilder();
        for (int i = 0; i < genres.length; i++) {
            stringbuilder.append(genres[i].getName());
            if (i < genres.length - 1) {
                stringbuilder.append(", ");
            }
        }
        return stringbuilder.toString();
    }
}
