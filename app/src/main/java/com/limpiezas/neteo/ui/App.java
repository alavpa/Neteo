package com.limpiezas.neteo.ui;

import android.app.Application;
import android.content.Context;

/**
 * Created by alavpa on 3/8/16.
 */
public class App extends Application {
    private static Context app;
    public static Context get(){
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
}
