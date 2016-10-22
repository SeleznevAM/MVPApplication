package com.applications.whazzup.mvpapplication.mvp.views;

import android.support.annotation.Nullable;

import com.applications.whazzup.mvpapplication.mvp.presenters.IAuthPresenter;
import com.applications.whazzup.mvpapplication.ui.custom_view.AuthPanel;

/**
 * Created by Alex on 20.10.2016.
 */

public interface IAuthView {
    void showMessage(String message);
    void showError(Throwable e);
    void showLoad();
    void hideLoad();

    IAuthPresenter getPresenter();

    void showLoginBtn();
    void hideLoginBtn();

    //void testShowLoginCard();
    @Nullable
    AuthPanel getAuthPanel();
}
