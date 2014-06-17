package net.senneco.funlib.sample.common;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by senneco on 30.04.2014
 */
public class PrefUtils {
    public static final String PREF_NAME = "github";
    public static final String TOKEN = "token";

    public static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        return getPrefs(context).edit();
    }

    public static String getToken(Context context) {
        return getPrefs(context).getString(TOKEN, "");
    }

    public static void setToken(Context context, String token) {
        getEditor(context).putString(TOKEN, token).commit();
    }
}
