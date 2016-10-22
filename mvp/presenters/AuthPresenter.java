package com.applications.whazzup.mvpapplication.mvp.presenters;

import android.media.MediaCodec;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Patterns;

import com.applications.whazzup.mvpapplication.mvp.models.AuthModel;
import com.applications.whazzup.mvpapplication.mvp.views.IAuthView;
import com.applications.whazzup.mvpapplication.ui.custom_view.AuthPanel;

/**
 * Created by Alex on 20.10.2016.
 */

public class AuthPresenter implements IAuthPresenter {
    private static AuthPresenter ourInstance = new AuthPresenter();
    private AuthModel mAuthModel;
    private IAuthView mAuthView;


    private AuthPresenter() {
        mAuthModel = new AuthModel();
    }

    public static AuthPresenter getOurInstance() {
        return ourInstance;
    }

    @Override
    public void takeView(IAuthView authView) {
        mAuthView = authView;

    }


    @Override
    public void dropView() {
        mAuthView = null;

    }

    @Override
    public void initView() {
        if (getView() != null) {
            if (checkUserAuth()) {
                getView().hideLoginBtn();
            } else {
                getView().showLoginBtn();
            }
        }


    }

    @Nullable
    @Override
    public IAuthView getView() {
        return mAuthView;
    }

    @Override
    public void clickOnLogin() {
        if(getView()!=null && getView().getAuthPanel()!=null){
            if(getView().getAuthPanel().isIddle()){
                getView().getAuthPanel().setCustomState(AuthPanel.LOGIN_STATE);
            }else {
                // TODO: 21.10.2016 auth user
                mAuthModel.loginUser(getView().getAuthPanel().getEmail(), getView().getAuthPanel().getPassword());
                getView().showMessage("request for user auth");
            }
        }
        // TODO: 20.10.2016 Показать и скрыть панель авторизации

    }

    @Override
    public void clickOnFb() {
        if(getView()!=null){
            getView().showMessage("Вы регистрируетесь ччерез facebook");
        }
    }

    @Override
    public void clickOnVk() {
        if(getView()!=null){
            getView().showMessage("Вы регистрируетесь ччерез VK");
        }
    }

    @Override
    public void clickOnTwitter() {
        if(getView()!=null){
            getView().showMessage("Вы регистрируетесь ччерез Twitter");
        }
    }

    @Override
    public void clickOnShowCatalog() {
        if(getView()!=null){
            getView().showMessage("Вы вошли в каталог");
        }

    }

    @Override
    public boolean checkUserAuth() {
        return mAuthModel.isAuthUser();
    }

    @Override
    public boolean isValidateEmail(String s) {


        return !s.isEmpty() && s.matches(String.valueOf(Patterns.EMAIL_ADDRESS));

    }

    @Override
    public boolean isValidatePassword(String s) {

        return !s.isEmpty() && s.trim().length()>=8;
    }
}
