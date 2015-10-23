package org.klez.maizdroide.facebook;

import android.content.Context;
import android.content.Intent;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.klez.maizdroide.debug.Logcat;
import org.klez.maizdroide.login.FacebookLogin;

/**
 * Created by klez on 20/10/15.
 */
public class FacebookLoginEngine {
    private CallbackManager callbackManager;
    private FacebookCallback<LoginResult> callback;
    private AccessTokenTracker tokenTracker;
    private final FacebookLogin login;
    private final Context ctx;

    public FacebookLoginEngine(FacebookLogin login,Context ctx){
        this.ctx = ctx;
        this.login = login;
    }

    public void onCreate() {
        FacebookSdk.sdkInitialize(ctx);

        callbackManager = CallbackManager.Factory.create();
        callback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Logcat.debug("Facebook onSuccess()");
            }

            @Override
            public void onCancel() {
                Logcat.debug("Facebook onCancel()");
                login.onFacebookUnauth();
            }

            @Override
            public void onError(FacebookException exception) {
                Logcat.exception("Facebook onError()", exception);
                login.onFacebookUnauth();
            }
        };

        tokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken newAccessToken) {
                updateToken(newAccessToken);
            }
        };
    }

    private void updateToken(AccessToken token) {
        if (token != null) {
            login.onFacebookAuth(token);
        }
        else {
            login.onFacebookUnauth();
        }
    }

    public void onDestroy() {
        tokenTracker.stopTracking();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void authIntent(LoginButton loginButton) {
        loginButton.registerCallback(callbackManager, callback);
    }
}
