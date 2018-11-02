package com.bluewebspark.classified;

import android.app.Application;

import com.bluewebspark.classified.data.datahelper.UserDataHelper;


public class AppController extends Application {

    private static AppController mInstance;
    public static final String TAG = AppController.class.getSimpleName();

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        new UserDataHelper(this);
    }

}

