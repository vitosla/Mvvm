package com.vitos.mvvm;

import android.app.Application;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.vitos.mvvm.di.AppComponent;
import com.vitos.mvvm.di.DaggerAppComponent;
import com.vitos.mvvm.di.modules.ContextModule;

import java.util.UUID;

/**
 * Created by Victor on 05.06.2017.
 */

public class MvvmApp extends Application {

    private static AppComponent sAppComponent;
    private static String ANDROID_ID;

    @Override
    public void onCreate() {
        super.onCreate();
        ANDROID_ID = androidID();
        sAppComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

    }

    public static AppComponent getAppComponent(){
        return sAppComponent;
    }

    public static String getAndroidID() {
        return ANDROID_ID;
    }

    // calculate current device id
    private String androidID() {

        final Uri URI = Uri.parse("content://com.google.android.gsf.gservices");
        final String ID_KEY = "android_id";

        String androidId = UUID.randomUUID().toString();

        String[] params = {ID_KEY};
        Cursor c = getContentResolver().query(URI, null, null, params, null);

        if (c != null && (!c.moveToFirst() || c.getColumnCount() < 2)) {
            c.close();
        } else if (c != null) {
            try {
                String id = Long.toHexString(Long.parseLong(c.getString(1)));
                c.close();
                androidId = id;
            } catch (NumberFormatException e) {
                Log.e("ID format error", e.getMessage(), e);
            }
        }

        return androidId;
    }
}
