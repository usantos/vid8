package br.com.cinesky.parser;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JSONParser {

    private static final String MAIN_URL = "https://sky-exercise.herokuapp.com/api/Movies";
    public static final String TAG = "TAG";
    private static Response response;

    public static JSONArray getDataFromWeb() {
        try {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(MAIN_URL)
                    .build();
            response = client.newCall(request).execute();

            return new JSONArray(response.body().string());

        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }
}