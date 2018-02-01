package com.heady.test.constant;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Keval on 2/2/2018.
 */

public class AppPreference {

    public static final String FILE_NAME = "Heady Test";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public AppPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME , Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }





}
