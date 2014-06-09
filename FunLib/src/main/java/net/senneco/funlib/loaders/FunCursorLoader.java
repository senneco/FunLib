package net.senneco.funlib.loaders;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by senneco on 06.06.2014
 */
public abstract class FunCursorLoader extends FunDbLoader<Cursor> {

    public FunCursorLoader(Context context) {
        super(context);
    }

    protected FunCursorLoader(Context context, Uri uri) {
        super(context, uri);
    }

    public final Cursor loadData() throws Exception {
        Cursor cursor = loadCursor();

        if (cursor != null) {
            // Ensure the cursor window is filled
            cursor.getCount();
        }

        return cursor;
    }

    public abstract Cursor loadCursor() throws Exception;

    @Override
    protected void releaseResources(LoaderResult<Cursor> result) {
        if (result != null && result.getData() != null && !result.getData().isClosed()) {
            result.getData().close();
        }
    }
}
