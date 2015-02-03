package org.flisolsaocarlos.flisol.util;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.flisolsaocarlos.flisol.model.DatabaseContainer;

import java.io.IOException;
import java.io.InputStream;

public class JSONHandler {

    private final static String JSON_PATH = "database/flisol.json";
    private final static String TAG = JSONHandler.class.getName();


    public static DatabaseContainer parseJSON(Context context) {
        DatabaseContainer databaseContainer = new DatabaseContainer();
        String json = null;
        try {
            json = loadJSONFromAsset(context);
            Gson gson = new Gson();
            databaseContainer = gson.fromJson(json, DatabaseContainer.class);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return databaseContainer;
    }

    private static String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(JSON_PATH);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            return null;
        }
        return json;
    }
}
