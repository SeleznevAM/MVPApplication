package com.applications.whazzup.mvpapplication.mvp.models;

/**
 * Created by Alex on 20.10.2016.
 */

public class AuthModel {
    public AuthModel() {
    }

    public boolean isAuthUser(){
        // TODO: 20.10.2016 Найти токен авторизации в sharadPreferences
        return false;
    }

    public void loginUser(String email, String passord){
        // TODO: 20.10.2016 Отправить данные на сервер для авторизации
    }
}
