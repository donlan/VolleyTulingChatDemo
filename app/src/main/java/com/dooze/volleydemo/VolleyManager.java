package com.dooze.volleydemo;

import android.graphics.Bitmap;
import android.util.LruCache;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;


/**
 * Created by doo on 16-4-10.
 */
public class VolleyManager {

    public static void GET(String url, String tag, VolleyInterface volleyInterface) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                volleyInterface.loadingListener(), volleyInterface.errorListener());
        App.getHttpQuenu().cancelAll(tag);
        request.setTag(tag);
        App.getHttpQuenu().add(request);
        App.getHttpQuenu().start();
    }


    public static void ImageRequest(String url, final ImageView imageView)
    {
        ImageRequest request = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imageView.setImageBitmap(response);
            }
        }, 0, 0, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        request.setTag("IMG");
        App.getHttpQuenu().cancelAll("IMG");
        App.getHttpQuenu().add(request);
        App.getHttpQuenu().start();
    }


    public static void IamgeLoader(String url,ImageView imageView,int defaultImage,int errImage)
    {

        ImageLoader loader  = new ImageLoader(App.getHttpQuenu(),new BitmapCache());
        ImageLoader.ImageListener imageListener  = ImageLoader.getImageListener(imageView,defaultImage,errImage);
        loader.get(url,imageListener);
    }
    public static void POST() {

    }
}





class BitmapCache implements ImageLoader.ImageCache {

    public LruCache<String,Bitmap> cache;

    private int MAX_SIZE = 10 * 1024 * 1024;

    public BitmapCache() {
        cache = new LruCache<String,Bitmap>(MAX_SIZE){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return cache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        cache.put(url,bitmap);
    }
}

