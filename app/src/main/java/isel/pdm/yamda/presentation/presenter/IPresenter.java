package isel.pdm.yamda.presentation.presenter;

import android.view.View;

/**
 * Interface representing a Presenter in a model view presenter (MVP) pattern.
 */
public interface IPresenter {

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onResume() method.
     */
    void onResume();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onPause() method.
     */
    void onPause();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onDestroy() method.
     */
    void onDestroy();

}
