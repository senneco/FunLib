package net.senneco.funlib.sample.common;

import android.content.Context;
import android.content.SharedPreferences;
import net.senneco.funlib.sample.data.User;

/**
 * Created by senneco on 18.06.2014
 */
public class UserUtils {
    private static final String ID = "id";
    private static final String LOGIN = "login";
    private static final String PUBLIC_REPOS = "public_repos";
    private static final String PUBLIC_GISTS = "public_gists";
    private static final String FOLLOWERS = "followers";
    private static final String FOLLOWING = "following";

    public static void setUser(Context context, User user) {
        SharedPreferences.Editor editor = PrefUtils.getEditor(context);
        editor.putInt(ID, user.getId());
        editor.putString(LOGIN, user.getLogin());
        editor.commit();
    }

    public static int getId(Context context) {
        return PrefUtils.getPrefs(context).getInt(ID, 0);
    }

    public static void setId(Context context, int id) {
        PrefUtils.getEditor(context).putInt(ID, id).commit();
    }

    public static String getLogin(Context context) {
        return PrefUtils.getPrefs(context).getString(LOGIN, "");
    }

    public static void setLogin(Context context, String login) {
        PrefUtils.getEditor(context).putString(LOGIN, login).commit();
    }
}
