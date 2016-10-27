package com.applications.whazzup.mvpapplication.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.applications.whazzup.mvpapplication.BuildConfig;
import com.applications.whazzup.mvpapplication.R;
import com.applications.whazzup.mvpapplication.mvp.presenters.AuthPresenter;
import com.applications.whazzup.mvpapplication.mvp.presenters.IAuthPresenter;
import com.applications.whazzup.mvpapplication.mvp.views.IAuthView;
import com.applications.whazzup.mvpapplication.ui.custom_view.AuthPanel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements IAuthView,View.OnClickListener{
        AuthPresenter mAuthPresenter= AuthPresenter.getOurInstance();
        ProgressDialog mProgressDialog;

@BindView(R.id.coordinator_container)
CoordinatorLayout mCoordinatorLayout;

@BindView(R.id.login_btn)
Button mLoginButton;

@BindView(R.id.auth_wrapper)
AuthPanel mAutPanel;

@BindView(R.id.show_catalog_btn)
Button mShowCatalog;

@BindView(R.id.app_name_txt)
TextView mAppName;

//region ===============Life Cycle=====================
@Override
protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        mAuthPresenter.takeView(this);
        mAuthPresenter.initView();

        Typeface CF=Typeface.createFromAsset(getAssets(),"fonts/PTBebasNeueRegular.ttf");
        mAppName.setTypeface(CF);
        mProgressDialog=new ProgressDialog(this,R.style.custom_dialog);
        mLoginButton.setOnClickListener(this);
        mShowCatalog.setOnClickListener(this);
        }

@Override
protected void onDestroy(){
        mAuthPresenter.dropView();
        super.onDestroy();
        }

//end region


@Override
public void showMessage(String message){
        Snackbar.make(mCoordinatorLayout,message,Snackbar.LENGTH_LONG).show();
        }

@Override
public void showError(Throwable e){
        if(BuildConfig.DEBUG){
        showMessage(e.getMessage());
        e.printStackTrace();
        }else{
        showMessage("Извините что то пошло нетак, попробуйте пойзже");
        }

        }

@Override
public void showLoad(){
        // TODO: 20.10.2016 Показать загрузчик
        if(mProgressDialog==null){
        mProgressDialog=new ProgressDialog(this,R.style.custom_dialog);
        mProgressDialog.setCancelable(false);
        mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mProgressDialog.show();
        mProgressDialog.setContentView(R.layout.dialog_splash);
        }
        else{
        mProgressDialog.show();
        mProgressDialog.setContentView(R.layout.dialog_splash);
        }
        }

@Override
public void hideLoad(){
        // TODO: 20.10.2016 Скрыть загрузчик
        mProgressDialog.hide();

        }

@Override
public IAuthPresenter getPresenter(){
        return mAuthPresenter;
        }

@Override
public void showLoginBtn(){
        mLoginButton.setVisibility(View.VISIBLE);

        }

@Override
public void hideLoginBtn(){
        mLoginButton.setVisibility(View.GONE);
        }
/*
    @Override
    public void testShowLoginCard() {
        mAuthCard.setVisibility(View.VISIBLE);
    }
*/
@Override
public AuthPanel getAuthPanel(){
        return mAutPanel;
        }

        @Override
        public void showCatalogScreen() {
                Intent intent  = new Intent(this, RootActivity.class);
                startActivity(intent);
        }

        @Override
public void onBackPressed(){
        if(!mAutPanel.isIddle()){
        mAutPanel.setCustomState(AuthPanel.IDDLE_STATE);
        }else{
        super.onBackPressed();
        }

        }

@Override
public void onClick(View view){
        switch(view.getId()){
        case R.id.show_catalog_btn:
        mAuthPresenter.clickOnShowCatalog();
        break;
        case R.id.login_btn:
        mAuthPresenter.clickOnLogin();
        break;
        }
        }
        }