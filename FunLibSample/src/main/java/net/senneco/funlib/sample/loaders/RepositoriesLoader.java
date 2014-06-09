package net.senneco.funlib.sample.loaders;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import net.senneco.funlib.loaders.FunCursorLoader;
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
        SQLiteDatabase readableDatabase = getDbHelper().getReadableDatabase();

        assert readableDatabase != null;

        Cursor result = readableDatabase.rawQuery("" +
                "SELECT r.*, u.login, u.avatar " +
                "FROM Repository r " +
                "   JOIN User u ON r.owner = u._id", null);

        return result;
    }
}
