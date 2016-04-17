package com.dooze.volleydemo;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by doo on 16-4-10.
 */
public abstract class VolleyInterface {

    Context context;
    public static Response.Listener<JSONObject> mLoadingListener;
    public static Response.ErrorListener mErrorListener;

    public VolleyInterface(Context context, Response.Listener<JSONObject> loadingListener, Response.ErrorListener errorListener) {
        this.context = context;
        mErrorListener = errorListener;
        mLoadingListener = loadingListener;
    }

    public abstract void onSuccess(JSONObject result);

    public abstract void onError(VolleyError err);

    public Response.Listener<JSONObject> loadingListener() {
        mLoadingListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                onSuccess(response);
            }
        };

        return mLoadingListener;
    }

    public Response.ErrorListener errorListener() {
        mErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onError(error);
            }
        };

        return mErrorListener;
    }
}
