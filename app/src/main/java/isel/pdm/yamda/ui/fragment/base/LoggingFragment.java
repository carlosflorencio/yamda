package isel.pdm.yamda.ui.fragment.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Abstract class that extends the Fragment class and overrides lifecycle callbacks for
 * logging various lifecycle events.
 */
public abstract class LoggingFragment extends Fragment {

    /**
     * Debugging tag used by the Android logger.
     */
    protected final String TAG = "FRAGMENT_" + getClass().getSimpleName();

    /**
     * {@inheritDoc}
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach() - the fragment is being attached to its context");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            // The fragment is being re-created.
            Log.d(TAG, "onCreate(): fragment re-created");

        } else {
            // The fragment is being created anew.
            Log.d(TAG, "onCreate(): fragment created anew");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = super.onCreateView(inflater, container, savedInstanceState);
        String saved = savedInstanceState == null ? "yes" : "no";
        Log.d(TAG,
              "onCreateView() - the fragment is creating its view; savedInstanceState is null?" + saved);
        return view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Log.d(TAG, "onDestroyView() - the fragment destroyed its view");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() - the fragment is about to become visible");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() - the fragment has become visible (it is now \"resumed\")");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() - the fragment is being hidden");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() - the fragment is no longer visible (it is now \"stopped\")");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy() - the fragment is about to be destroyed");
        super.onDestroy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach() - the fragment is being detached from its context");
    }
}
