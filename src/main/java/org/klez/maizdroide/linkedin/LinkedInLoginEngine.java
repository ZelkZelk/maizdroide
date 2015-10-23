package org.klez.maizdroide.linkedin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.linkedin.platform.AccessToken;
import com.linkedin.platform.LISessionManager;
import com.linkedin.platform.errors.LIAuthError;
import com.linkedin.platform.internals.LIAppVersion;
import com.linkedin.platform.listeners.AuthListener;

import org.klez.maizdroide.config.Persistent;
import org.klez.maizdroide.debug.Logcat;
import org.klez.maizdroide.login.LinkedInLogin;

/**
 * Created by klez on 20/10/15.
 */
public class LinkedInLoginEngine {
    private static final String LINKEDIN_STORE = "linkedin-login";
    private static final String LINKEDIN_TOKEN = "linkedin-token";
    private static final String LINKEDIN_EXPIRES = "linkedin-expires";
    private final LinkedInLogin login;
    private final Context ctx;
    private final Activity activity;
    private AuthListener authListener = new AuthListener() {
        @Override
        public void onAuthSuccess() {
            AccessToken token = LISessionManager.getInstance(ctx).getSession().getAccessToken();
            login.onLinkedInAuth(token);
        }

        @Override
        public void onAuthError(LIAuthError error) {
            Logcat.debug(error.toString());
            login.onLinkedInUnauth();
        }
    };

    public LinkedInLoginEngine(LinkedInLogin login, Activity activity){
        this.activity = activity;
        this.ctx = activity.getApplicationContext();
        this.login = login;
    }

    public void onCreate() {
        if (LIAppVersion.isLIAppCurrent(ctx)) {
            AccessToken token = LISessionManager.getInstance(ctx).getSession().getAccessToken();

            if(token != null) {
                if( ! token.isExpired()) {
                    LISessionManager.getInstance(ctx).init(token);
                    login.onLinkedInAuth(token);
                }
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        LISessionManager.getInstance(ctx).onActivityResult(activity, requestCode, resultCode, data);
    }

    public void authIntent() {
        LISessionManager.getInstance(ctx).init(activity, login.buildScope(), authListener, false);
    }
}
