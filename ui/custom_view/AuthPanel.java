package com.applications.whazzup.mvpapplication.ui.custom_view;


import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.applications.whazzup.mvpapplication.R;
import com.applications.whazzup.mvpapplication.mvp.presenters.AuthPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alex on 20.10.2016.
 */

public class AuthPanel extends LinearLayout {

     static final String TAG = "AuthPanel";
     public static final int LOGIN_STATE = 0;
     public static final int IDDLE_STATE = 1;
     private int mCustomState = 1;
    private AuthPresenter mPresenter = AuthPresenter.getOurInstance();

    @BindView(R.id.auth_card)
    CardView mAuthCard;

    @BindView(R.id.login_email_et)
    EditText mEmail;

    @BindView(R.id.login_password_et)
    EditText mPassword;

    @BindView(R.id.login_btn)
    Button loginButton;

    @BindView(R.id.show_catalog_btn)
    Button mShowCatalog;

    @BindView(R.id.login_password_wrapper)
    TextInputLayout mPassworWraper;

    @BindView(R.id.login_email_wrapper)
    TextInputLayout mEmailWrapper;

    public AuthPanel(Context context, AttributeSet attrs) {

        super(context, attrs);

    }

    // TODO: 21.10.2016 validate and save state for email

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        showViewFromState();
        mEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!mPresenter.isValidateEmail(getEmail().toString())&& mCustomState==LOGIN_STATE){
                    mEmailWrapper.setError("myMail@myMail.com");
                    loginButton.setEnabled(false);
                }else{
                    loginButton.setEnabled(true);
                    mEmailWrapper.setErrorEnabled(false);
                }
            }
        });
        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!mPresenter.isValidatePassword(getPassword().toString())){
                    mPassworWraper.setError("Пароль должен быть болле 8 символов");
                    loginButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!mPresenter.isValidatePassword(getPassword().toString()) && mCustomState==LOGIN_STATE){
                    mPassworWraper.setError("Пароль должен быть болле 8 символов");
                    loginButton.setEnabled(false);
                }else{
                    loginButton.setEnabled(true);
                    mPassworWraper.setErrorEnabled(false);
                }
            }
        });
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superStat = super.onSaveInstanceState();
        SavedState savedState = new SavedState(superStat);
        savedState.state = mCustomState;
        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(state);
        setCustomState(savedState.state);
    }

    public void setCustomState(int state) {
        mCustomState = state;
        showViewFromState();
    }

    private void showLoginState(){
        mAuthCard.setVisibility(VISIBLE);
        mShowCatalog.setVisibility(GONE);
    }

    private void showIdleState(){
        mAuthCard.setVisibility(GONE);
        mShowCatalog.setVisibility(VISIBLE);
        loginButton.setEnabled(true);
    }

    private void showViewFromState(){
        if(mCustomState == LOGIN_STATE){
            showLoginState();
        }else{
            showIdleState();
        }
    }

    public String getEmail(){
       return String.valueOf(mEmail.getText());
    }

    public String getPassword(){
        return String.valueOf(mPassword.getText());
    }

    public boolean isIddle(){
        return mCustomState == IDDLE_STATE;
    }



    static class SavedState extends BaseSavedState {

        private int state;

            public SavedState(Parcelable superState) {
            super(superState);
        }

        public SavedState(Parcel in){
            super(in);
            state = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(flags);
        }

        public static final Parcelable.Creator<SavedState> CREATOR = new Creator<SavedState>() {
            @Override
            public SavedState createFromParcel(Parcel in) { return new SavedState(in);}

            @Override
            public SavedState[] newArray(int i) {return new SavedState[i];}
        };
    }


}
