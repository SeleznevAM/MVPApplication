package com.applications.whazzup.mvpapplication.ui.activities;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

import com.applications.whazzup.mvpapplication.BuildConfig;
import com.applications.whazzup.mvpapplication.R;
import com.applications.whazzup.mvpapplication.mvp.presenters.AuthPresenter;
import com.applications.whazzup.mvpapplication.mvp.presenters.IAuthPresenter;
import com.applications.whazzup.mvpapplication.mvp.views.IAuthView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RootActivity extends AppCompatActivity implements IAuthView, View.OnClickListener {
    AuthPresenter mAuthPresenter = AuthPresenter.getOurInstance();

    @BindView(R.id.coordinator_container)
    CoordinatorLayout mCoordinatorLayout;

    @BindView(R.id.login_btn)
    Button mLoginButton;

    @BindView(R.id.auth_card)
    CardView mAuthCard;

    @BindView(R.id.show_catalog_btn)
    Button mShowCatalog;

    //regino ===============Life Cycle=====================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);
        ButterKnife.bind(this);
        mAuthPresenter.takeView(this);
        mAuthPresenter.initView();

        mLoginButton.setOnClickListener(this);
        mShowCatalog.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        mAuthPresenter.dropView();
        super.onDestroy();
    }

    //endregion


    @Override
    public void showMessage(String message) {
        Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showError(Throwable e) {
        if(BuildConfig.DEBUG){
            showMessage(e.getMessage());
            e.printStackTrace();
        }else{
            showMessage("Извините что то пошло нетак, попробуйте пойзже");
        }

    }

    @Override
    public void showLoad() {
        // TODO: 20.10.2016 Показать загрузчик

    }

    @Override
    public void hideLoad() {
        // TODO: 20.10.2016 Скрыть загрузчик

    }

    @Override
    public IAuthPresenter getPresenter() {
        return mAuthPresenter;
    }

    @Override
    public void showLoginBtn() {
        mLoginButton.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoginBtn() {
mLoginButton.setVisibility(View.GONE);
    }

    @Override
    public void testShowLoginCard() {
        mAuthCard.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.show_catalog_btn:
                mAuthPresenter.clickOnShowCatalog();
                break;
            case R.id.login_btn:
                mAuthPresenter.clickOnLogin();
                break;
        }
    }
}
