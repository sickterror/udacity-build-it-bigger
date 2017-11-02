package com.timelesssoftware.jokeapp;

import android.app.Application;
import android.content.Context;


/**
 * Created by Luka on 3. 10. 2017.
 */

public class JokeTellingApp extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
