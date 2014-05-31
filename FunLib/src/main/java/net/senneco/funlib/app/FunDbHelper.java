package net.senneco.funlib.app;

import android.content.Context;
import android.content.pm.PackageManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;

/**
 * Created by senneco on 31.05.2014
 */
public abstract class FunDbHelper extends OrmLiteSqliteOpenHelper {

    /*
     * Create DB with name like app package name and code like app version number
     */
    public FunDbHelper(Context context) {
        this(context, getDbName(context), getDbVersion(context));
    }

    public FunDbHelper(Context context, String databaseName, int databaseVersion) {
        super(context, databaseName, null, databaseVersion);
    }

    private static String getDbName(Context context) {
        return context.getPackageName() + ".db";
    }

    private static int getDbVersion(Context context) {
        int versionCode = 1;

        try {
            versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // pass
        }

        return versionCode;
    }
}
