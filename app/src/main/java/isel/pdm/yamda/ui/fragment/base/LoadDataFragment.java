package isel.pdm.yamda.ui.fragment.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.Toast;

import isel.pdm.yamda.R;
import isel.pdm.yamda.ui.contract.ILoadDataView;

/**
 * Abstract class to simplify fragments with multiple states views, loading, error, no connection
 * @param <T>
 */
public abstract class LoadDataFragment<T> extends PresentableFragment implements ILoadDataView<T> {

    protected FrameLayout frameLayout;
    protected View mainView;
    protected View loadingView;
    protected View retryView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View viewContainer = inflater.inflate(R.layout.state_layout, container, false);

        this.frameLayout = (FrameLayout) viewContainer.findViewById(R.id.frame_view);
        ViewStub stub = (ViewStub) viewContainer.findViewById(R.id.stub_view);
        stub.setLayoutResource(this.getLayout());

        this.mainView = stub.inflate();
        this.loadingView = viewContainer.findViewById(R.id.progress_view);
        this.retryView = viewContainer.findViewById(R.id.retry_view);
        showLoading();

        return viewContainer;
    }

    protected abstract int getLayout();

    /*
    |--------------------------------------------------------------------------
    | Load data
    |--------------------------------------------------------------------------
    */
    @Override
    public void showLoading() {
        this.frameLayout.removeAllViews();
        this.frameLayout.addView(loadingView);
    }

    @Override
    public void showResults() {
        this.frameLayout.removeAllViews();
        this.frameLayout.addView(mainView);
    }

    @Override
    public void showNoConnection() {
        this.frameLayout.removeAllViews();
        this.frameLayout.addView(retryView);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }
}
