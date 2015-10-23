package org.klez.maizdroide.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.klez.maizdroide.config.Config;
import org.klez.maizdroide.debug.Logcat;

import java.util.ArrayList;

abstract public class AppActivity extends AppCompatActivity {
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Logcat.initialize(getConfig().getLogcatTag(), getConfig().isLogcatEnabled());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    abstract public Config getConfig();
}
