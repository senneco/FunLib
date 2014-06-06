package net.senneco.funlib.loaders;

import android.content.Context;
import android.net.Uri;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import net.senneco.funlib.app.FunApp;

/**
 * Created by senneco on 06.06.2014
 */
public abstract class FunDbLoader<T> extends FunLoader<T> {

    public FunDbLoader(Context context) {
        super(context);
    }

    protected FunDbLoader(Context context, Uri uri) {
        super(context, uri);
    }

    public OrmLiteSqliteOpenHelper getDbHelper() {
        if (getContext() != null && getContext().getApplicationContext() != null) {
            return ((FunApp) getContext().getApplicationContext()).getDbHelper();
        }

        return null;
    }
}
