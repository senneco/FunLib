package net.senneco.funlib.sample.loaders;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import net.senneco.funlib.loaders.FunCursorLoader;
import net.senneco.funlib.sample.data.Uris;

/**
 * Created by senneco on 07.06.2014
 */
public class RepositoriesLoader extends FunCursorLoader {

    private String mLogin;

    public RepositoriesLoader(Context context) {
        super(context, Uris.REPOSITORIES);
    }

    public RepositoriesLoader forLogin(String login) {
        mLogin = login;

        return this;
    }

    @Override
    public Cursor loadCursor() throws Exception {
        SQLiteDatabase readableDatabase = getDbHelper().getReadableDatabase();

        assert readableDatabase != null;

        String sql = "SELECT r.*, u.login, u.avatar " +
                "FROM Repository r " +
                "   JOIN User u ON r.owner = u._id ";

        String[] selectionArgs = new String[0];

        if (!TextUtils.isEmpty(mLogin)) {
            sql += "WHERE " +
                    "    u.login = ? ";

            selectionArgs = new String[]{mLogin};
        }

        sql += "ORDER BY r.pushed_at DESC ";

        Cursor result = readableDatabase.rawQuery(sql, selectionArgs);

        return result;
    }
}
