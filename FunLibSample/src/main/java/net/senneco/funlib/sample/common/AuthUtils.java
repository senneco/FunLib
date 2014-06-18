package net.senneco.funlib.sample.common;

import android.content.Context;

/**
 * Created by senneco on 18.06.2014
 */
public class AuthUtils {
    private static final String TOKEN = "token";

    public static String getToken(Context context) {
        return PrefUtils.getPrefs(context).getString(TOKEN, "");
    }

    public static void setToken(Context context, String token) {
        PrefUtils.getEditor(context).putString(TOKEN, token).commit();
    }
}
