package org.klez.maizdroide.activity;

import android.content.Intent;
import android.os.Bundle;
import org.klez.maizdroide.facebook.FacebookLoginEngine;
import org.klez.maizdroide.linkedin.LinkedInLoginEngine;
import org.klez.maizdroide.login.FacebookLogin;
import org.klez.maizdroide.login.LinkedInLogin;

public abstract class LoginActivity extends AppActivity{
    private FacebookLoginEngine facebook;
    private LinkedInLoginEngine linkedin;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(this instanceof FacebookLogin){
            facebook.onDestroy();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(this instanceof FacebookLogin) {
            facebook.onActivityResult(requestCode, resultCode, data);
        }

        if(this instanceof LinkedInLogin) {
            linkedin.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(this instanceof FacebookLogin){
            facebook = new FacebookLoginEngine((FacebookLogin) this,getApplicationContext());
            facebook.onCreate();
        }

        if(this instanceof LinkedInLogin) {
            linkedin = new LinkedInLoginEngine((LinkedInLogin) this,this);
            linkedin.onCreate();
        }
    }

    public FacebookLoginEngine getFacebook() {
        return facebook;
    }

    public LinkedInLoginEngine getLinkedin() {
        return linkedin;
    }
}
