package isel.pdm.yamda.ui.activity.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Abstract class that extends the AppCompatActivity class and overrides lifecycle callbacks for
 * logging various lifecycle events.
 */
public abstract class LoggingActivity extends AppCompatActivity {

    protected final String TAG = "ACTIVITY_" + getClass().getSimpleName();

    /**
     * Helper method that produces a log message with the given method name and suffix.
     * @param methodName The name of the method whose execution is being logged.
     * @param suffix The suffix to be appended to the log message.
     * @return The log message.
     */
    private String createLogMessage(String methodName, String suffix) {
        return String.format("%s() [%d] %s", methodName, hashCode(), suffix);
    }

    /**
     * Helper method that produces a log message with the given method name.
     * @param methodName The name of the method whose execution is being logged.
     * @return The log message.
     */
    private String createLogMessage(String methodName) {
        return createLogMessage(methodName, "");
    }

    /** {@inheritDoc} */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.v(TAG, createLogMessage("onCreate", String.format(": savedInstanceState is%s null",
                savedInstanceState != null ? " not" : "")));
    }

    /** {@inheritDoc} */
    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, createLogMessage("onStart"));
    }

    /** {@inheritDoc} */
    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, createLogMessage("onResume"));
    }

    /** {@inheritDoc} */
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v(TAG, createLogMessage("onRestart"));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.v(TAG, createLogMessage("onSaveInstanceState"));
    }

    /** {@inheritDoc} */
    @Override
    protected void onPause() {
        Log.v(TAG, createLogMessage("onPause"));
        super.onPause();
    }

    /** {@inheritDoc} */
    @Override
    protected void onStop() {
        Log.v(TAG, createLogMessage("onStop"));
        super.onStop();
    }

    /** {@inheritDoc} */
    @Override
    protected void onDestroy() {
        Log.v(TAG, createLogMessage("onDestroy"));
        super.onDestroy();
    }
}
