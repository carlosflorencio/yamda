package isel.pdm.yamda.ui.presenter.base;

/**
 * Interface representing a Presenter in a model view presenter (MVP) pattern.
 */
public interface IPresenter {

    /**
     * Execute task
     */
    void execute();

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
     * Should delete the view reference for the gc work and avoid memory leaks
     * (Activity or Fragment) onDestroy() method.
     */
    void onDestroy();
}
