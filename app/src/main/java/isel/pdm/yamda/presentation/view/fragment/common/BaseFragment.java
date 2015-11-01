package isel.pdm.yamda.presentation.view.fragment.common;


import android.os.Bundle;
import android.widget.Toast;

/**
 * Base Fragment class for every fragment in this application.
 */
public class BaseFragment extends LoggingFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    /**
     * Shows a {@link android.widget.Toast} message.
     *
     * @param message An string representing a message to be shown.
     */
    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
