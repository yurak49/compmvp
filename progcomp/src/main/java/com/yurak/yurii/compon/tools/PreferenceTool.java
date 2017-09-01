package com.yurak.yurii.compon.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.yurak.yurii.compon.SimpleApp;

public class PreferenceTool {
    private static final String PREFERENCES_NAME = "simple_app_prefs";
    private static final String USER_KEY = "user_key";
    private static final String SESSION_COOKIE = "session_cookie";
    private static final String SESSION_TOKEN = "session_token";

    public static void setSessionToken(String token) {
        getEditor().putString(SESSION_TOKEN, token).commit();
    }

    public static String getSessionToken() {
        return getSharedPreferences().getString(SESSION_TOKEN, null);
    }

    public static void setSessionCookie(String cookie) {
        getEditor().putString(SESSION_COOKIE, cookie).commit();
    }

    public static String getSessionCookie() {
        return getSharedPreferences().getString(SESSION_COOKIE, null);
    }

    public static void setUserKey(String user_key) {
        getEditor().putString(USER_KEY, user_key).commit();
    }

    public static String getUserKey() {
        return getSharedPreferences().getString(USER_KEY, "");
    }

    //  *************************************************
    private static SharedPreferences.Editor getEditor() {
        return SimpleApp.getInstance().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).edit();
    }

    private static SharedPreferences getSharedPreferences() {
        return SimpleApp.getInstance().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }
}
