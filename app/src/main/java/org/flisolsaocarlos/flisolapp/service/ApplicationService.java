package org.flisolsaocarlos.flisolapp.service;

import android.app.Application;

import org.flisolsaocarlos.flisolapp.provider.DatabaseHelper;

public class ApplicationService extends Application {


    private static ApplicationService instanceApp;
    private DatabaseHelper databaseHelper;

    public static synchronized ApplicationService getInstance() {
        return instanceApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instanceApp = this;
    }

    public DatabaseHelper getDatabaseHelper() {
        if (databaseHelper == null) {
            databaseHelper = new DatabaseHelper(getApplicationContext());
        }
        return databaseHelper;
    }
}
