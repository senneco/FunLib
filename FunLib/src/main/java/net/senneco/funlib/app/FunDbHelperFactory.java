package net.senneco.funlib.app;

import android.content.Context;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;

/**
 * Created by senneco on 31.05.2014
 */
public class FunDbHelperFactory {

    private static OrmLiteSqliteOpenHelper sDbHelper;

    public static OrmLiteSqliteOpenHelper getDbHelper() {
        return sDbHelper;
    }

    public static void initDbHelper(Context context, Class<? extends OrmLiteSqliteOpenHelper> dbHelperClass) {
        sDbHelper = OpenHelperManager.getHelper(context, dbHelperClass);
    }

    public static void releaseDbHelper() {
        OpenHelperManager.releaseHelper();
        sDbHelper = null;
    }
}
