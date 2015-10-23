package org.klez.maizdroide.login;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.login.LoginResult;

public interface FacebookLogin {
    public void onFacebookAuth(AccessToken token);
    public void onFacebookUnauth();
}
