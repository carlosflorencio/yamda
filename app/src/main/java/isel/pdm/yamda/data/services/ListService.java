package isel.pdm.yamda.data.services;

import android.app.IntentService;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 */
public abstract class ListService extends IntentService {

    public final String TAG = "SERVICE_" + getClass().getSimpleName();

    protected static final String PAGE = ListService.class.getName() + ".id";

    public ListService() {
        super("MovieListService");
    }
}
