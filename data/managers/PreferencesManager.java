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
        return mSharedPreferences.getString(ConstantManager.AUTH_TOKEN_KEY, "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1NzY2ZDYxNjFkMzdhMDFjMDBlZTA2YWYiLCJmaXJzdF9uYW1lIjoi0JDQu9C10LrRgdCw0L3QtNGAIiwic2Vjb25kX25hbWUiOiLQodC10LvQtdC30L3QtdCyIiwicm9sZSI6InVzZXIiLCJpYXQiOjE0NzQxNDA4MTV9.gyImySgOhd6HvVGhBuDyPrrtdNa9NRzN_SMFV-8mPGg");
    }
}
