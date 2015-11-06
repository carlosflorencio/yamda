package isel.pdm.yamda.presentation.view.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class LoggingFragment extends Fragment {

    protected final String TAG = "DEBUG_" + getClass().getSimpleName();

    /**
     * Helper method that produces a log message with the given method name and suffix.
     *
     * @param methodName The name of the method whose execution is being logged.
     * @param suffix     The suffix to be appended to the log message.
     * @return The log message.
     */
    private String createLogMessage(String methodName, String suffix) {
        return String.format("%s() [%d] %s", methodName, hashCode(), suffix);
    }

    /**
     * Helper method that produces a log message with the given method name.
     *
     * @param methodName The name of the method whose execution is being logged.
     * @return The log message.
     */
    private String createLogMessage(String methodName) {
        return createLogMessage(methodName, "");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.v(TAG, createLogMessage("onCreate", String.format(": savedInstanceState is%s null",
                savedInstanceState != null ? " not" : "")));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v(TAG, createLogMessage("onCreateView"));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.v(TAG, createLogMessage("onActivityCreated"));
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.v(TAG, createLogMessage("onStart"));
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v(TAG, createLogMessage("onResume"));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.v(TAG, createLogMessage("onSaveInstanceState"));
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.v(TAG, createLogMessage("onPause"));
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.v(TAG, createLogMessage("onStop"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(TAG, createLogMessage("onDestroy"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.v(TAG, createLogMessage("onDestroyView"));
    }
}
