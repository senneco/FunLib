package net.senneco.funlib.sample.loaders;

import android.content.Context;
import android.database.Cursor;
import net.senneco.funlib.common.CursorUtils;
import net.senneco.funlib.loaders.FunCursorLoader;
import net.senneco.funlib.sample.data.Repository;
import net.senneco.funlib.sample.data.Uris;

/**
 * Created by senneco on 07.06.2014
 */
public class RepositoriesLoader extends FunCursorLoader {

    public RepositoriesLoader(Context context) {
        super(context, Uris.REPOSITORIES);
    }

    @Override
    public Cursor loadCursor() throws Exception {
        return CursorUtils.getCursor(getDbHelper(), getDbHelper().getDao(Repository.class).queryBuilder().prepare());
    }
}
