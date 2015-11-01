package isel.pdm.yamda.presentation.view.activity.component;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import isel.pdm.yamda.R;

/**
 * Class that handles the loading of images
 */
public class LoadImageView extends ImageView {

    private static final int imagePlaceHolderResourceId = R.drawable.placeholder2;

    public LoadImageView(Context context) {
        super(context);
    }

    public LoadImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setImageUrl(final String imageUrl) {
        if (imageUrl != null) {
            this.loadImageFromUrl(imageUrl);
        } else {
            this.loadImagePlaceHolder();
        }
    }

    private void loadImageFromUrl(final String imageUrl) {
        new Thread() {
            @Override
            public void run() {
                if (isThereInternetConnection()) {
                    final ImageDownloader imageDownloader = new ImageDownloader();
                    imageDownloader.download(imageUrl, new ImageDownloader.Callback() {
                        @Override
                        public void onImageDownloaded(Bitmap bitmap) {
                            LoadImageView.this.loadBitmap(bitmap);
                        }
                    });
                } else {
                    LoadImageView.this.loadImagePlaceHolder();
                }
            }
        }.start();
    }

    private void loadBitmap(final Bitmap bitmap) {
        ((Activity) getContext()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LoadImageView.this.setImageBitmap(bitmap);
            }
        });
    }

    private void loadImagePlaceHolder() {
        ((Activity) getContext()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LoadImageView.this.setImageResource(
                        LoadImageView.this.imagePlaceHolderResourceId);
            }
        });
    }

    private boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }

    private static class ImageDownloader {
        interface Callback {
            void onImageDownloaded(Bitmap bitmap);
        }

        ImageDownloader() {
        }

        /**
         * Download an image from an url.
         *
         * @param imageUrl The url of the image to download.
         * @param callback A callback used to be reported when the task is finished.
         */
        void download(String imageUrl, Callback callback) {
            try {
                URLConnection conn = new URL(imageUrl).openConnection();
                conn.connect();
                Bitmap bitmap = BitmapFactory.decodeStream(conn.getInputStream());
                if (callback != null) {
                    callback.onImageDownloaded(bitmap);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
