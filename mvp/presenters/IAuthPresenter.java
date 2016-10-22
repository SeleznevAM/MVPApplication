package com.applications.whazzup.mvpapplication.mvp.presenters;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.applications.whazzup.mvpapplication.mvp.views.IAuthView;

/**
 * Created by Alex on 20.10.2016.
 */

public interface IAuthPresenter {
    void takeView(IAuthView authView);

    void dropView();

    void initView();

    @Nullable
    IAuthView getView();

    void clickOnLogin();
    void clickOnFb();
    void clickOnVk();
    void clickOnTwitter();
    void clickOnShowCatalog();
    boolean checkUserAuth();
    boolean isValidateEmail(String s);
    boolean isValidatePassword(String s);
}
