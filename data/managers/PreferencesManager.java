package com.applications.whazzup.mvpapplication.data.managers;

import android.content.SharedPreferences;

import com.applications.whazzup.mvpapplication.util.ConstantManager;
import com.applications.whazzup.mvpapplication.util.MvpApp;

/**
 * Created by Alex on 22.10.2016.
 */

public class PreferencesManager {
    private SharedPreferences mSharedPreferences;

    public PreferencesManager() {
        mSharedPreferences = MvpApp.getSharedPreferences();
    }

    public void saveAuthToken(String authToken) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(ConstantManager.AUTH_TOKEN_KEY, authToken);
        editor.apply();
    }

    public String getAuthToken() {
        return mSharedPreferences.getString(ConstantManager.AUTH_TOKEN_KEY, "null");
    }
}
