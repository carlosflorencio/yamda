package isel.pdm.yamda.ui.contract;

/**
 * This interface is a contract for representing a loading data view
 * @param <T>
 */
public interface ILoadDataView<T> {

    /**
     * Show a view with a progress bar indicating a loading process.
     */
    void showLoading();

    /**
     * Hide a loading view.
     */
    void hideLoading();

    /**
     * Show an error message
     *
     * @param message A string representing an error.
     */
    void showError(String message);

    /**
     * Add data
     * @param data
     */
    void setData(T data);
}
