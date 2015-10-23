package org.klez.maizdroide.config;

import android.content.Context;
import android.content.SharedPreferences;

import org.klez.maizdroide.debug.Logcat;

/**
 * Created by klez on 20/10/15.
 */
public class Persistent {
    public static PersistentImpl with(Context ctx,String name){
        return new PersistentImpl(ctx,name);
    }

    public static class PersistentImpl{
        private Context ctx;
        private String name;

        public PersistentImpl(Context ctx, String name){
            this.ctx = ctx;
            this.name = name;
        }

        public void write(String key, String val){
            SharedPreferences prefs = getPreferences();
            SharedPreferences.Editor edit = prefs.edit();
            edit.putString(key, val);

            if(val == null) {
                Logcat.debug("Write Config: " + key + "= null (hardcoded log)");
            }
            else {
                Logcat.debug("Write Config: " + key + "=" + val);
            }

            if(edit.commit() == false){
                Logcat.debug("Write Config Failed");
            }
        }

        public void write(String key, long val){
            SharedPreferences prefs = getPreferences();
            SharedPreferences.Editor edit = prefs.edit();
            edit.putLong(key, val);

            Logcat.debug("Write Config: " + key + "=" + val);

            if(edit.commit() == false){
                Logcat.debug("Write Config Failed");
            }

        }

        public long readLong(String key){
            SharedPreferences prefs = getPreferences();
            long val = prefs.getLong(key, 0);
            Logcat.debug("Read Config: " + key + "=" + val);

            return val;
        }

        public String read(String key){
            SharedPreferences prefs = getPreferences();
            String val = prefs.getString(key,null);

            if(val == null) {
                Logcat.debug("Read Config: " + key + "= null (hardcoded log)");
            }
            else{
                Logcat.debug("Read Config: " + key + "=" + val);

            }

            return val;
        }

        private SharedPreferences getPreferences(){
            return ctx.getSharedPreferences(name, Context.MODE_PRIVATE);
        }
    }
}
