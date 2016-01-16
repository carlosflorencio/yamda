package isel.pdm.yamda.ui.activity.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import isel.pdm.yamda.R;
import isel.pdm.yamda.ui.contract.ILoadDataView;

/**
 * Abstract class to simplify activities with multiple states views, loading, error, no connection
 * @param <T>
 */
public abstract class LoadDataActivity<T> extends PresentableActivity implements ILoadDataView<T>, View.OnClickListener {

    protected FrameLayout frameLayout;
    protected View mainView;
    protected View loadingView;
    protected View retryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.state_layout);

        this.frameLayout = (FrameLayout) findViewById(R.id.frame_view);
        ViewStub stub = (ViewStub) findViewById(R.id.stub_view);
        stub.setLayoutResource(this.getLayout());

        this.mainView = stub.inflate();
        this.loadingView = this.findViewById(R.id.progress_view);
        this.retryView = this.findViewById(R.id.retry_view);

        Button btn = (Button) this.retryView.findViewById(R.id.retry_button);
        btn.setOnClickListener(this);

        showLoading();
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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    public void onClick(View v) {
        showLoading();
        this.presenter.execute();
    }
}
