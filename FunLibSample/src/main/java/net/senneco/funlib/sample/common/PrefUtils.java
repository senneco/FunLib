package net.senneco.funlib.sample.common;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by senneco on 30.04.2014
 */
public class PrefUtils {
    private static final String PREF_NAME = "github";

    public static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static SharedPreferences.Editor getEditor(Context context) {
        return getPrefs(context).edit();
    }

}
