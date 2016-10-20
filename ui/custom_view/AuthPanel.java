package com.applications.whazzup.mvpapplication.ui.custom_view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.applications.whazzup.mvpapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alex on 20.10.2016.
 */

public class AuthPanel extends LinearLayout {

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

    public AuthPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }


}
