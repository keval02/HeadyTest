package com.heady.test.constant;

import android.app.Application;

import com.heady.test.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Keval on 2/2/2018.
 */

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Raleway_Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
