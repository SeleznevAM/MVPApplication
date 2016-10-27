package com.applications.whazzup.mvpapplication.mvp.models;

import android.content.SharedPreferences;

import com.applications.whazzup.mvpapplication.data.managers.PreferencesManager;

/**
 * Created by Alex on 20.10.2016.
 */

public class AuthModel {
    private PreferencesManager mPreferencesManager;
    public AuthModel() {
        mPreferencesManager = new PreferencesManager();
    }

    public boolean isAuthUser(){
        // TODO: 20.10.2016 Найти токен авторизации в sharadPreferences
        if(mPreferencesManager.getAuthToken().equals(null)){
            return true;
        }else
        return false;
    }

    public void loginUser(String email, String passord){
        // TODO: 20.10.2016 Отправить данные на сервер для авторизации
    }
}
