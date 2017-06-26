package com.borombo.mobileassignment.tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Borombo on 25/06/2017.
 *
 * Task that download and display icon from the API
 */

public class IconForecastTask extends AsyncTask<String, Void, Bitmap> {

    private final WeakReference<ImageView> imageView;
    private static final String BASE_URL = "http://openweathermap.org/img/w/";
    private static final String ICON_TYPE = ".png";

    public IconForecastTask(ImageView imageView) {
        this.imageView = new WeakReference<>(imageView);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap resultBitmap = null;
        try {
            URL imageURL = new URL(BASE_URL + params[0] + ICON_TYPE);
            HttpURLConnection connection = (HttpURLConnection) imageURL.openConnection();
            InputStream inputStream =  connection.getInputStream();
            resultBitmap = BitmapFactory.decodeStream(inputStream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultBitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        ImageView imageView = this.imageView.get();
        if (imageView != null){
            imageView.setImageBitmap(bitmap);
        }
    }
}
