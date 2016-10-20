package com.applications.whazzup.mvpapplication.mvp.presenters;

import android.support.annotation.Nullable;

import com.applications.whazzup.mvpapplication.mvp.models.AuthModel;
import com.applications.whazzup.mvpapplication.mvp.views.IAuthView;

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
        // TODO: 20.10.2016 Показать и скрыть панель авторизации
        getView().testShowLoginCard();
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
}
