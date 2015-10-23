package org.klez.maizdroide.login;

import com.linkedin.platform.AccessToken;
import com.linkedin.platform.listeners.AuthListener;
import com.linkedin.platform.utils.Scope;

public interface LinkedInLogin {
    public void onLinkedInAuth(AccessToken token);
    public void onLinkedInUnauth();
    public Scope buildScope();
}
