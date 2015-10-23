package org.klez.maizdroide.config;

/**
 * Created by klez on 20/10/15.
 */
public class Config {
    private String LogcatTag;
    private boolean LogcatEnabled;

    public String getLogcatTag() {
        return LogcatTag;
    }

    public void setLogcatTag(String logcatTag) {
        LogcatTag = logcatTag;
    }

    public boolean isLogcatEnabled() {
        return LogcatEnabled;
    }

    public void setLogcatEnabled(boolean logcatEnabled) {
        LogcatEnabled = logcatEnabled;
    }
}
