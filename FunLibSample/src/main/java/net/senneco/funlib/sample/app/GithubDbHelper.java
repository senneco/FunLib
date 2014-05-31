package net.senneco.funlib.sample.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import net.senneco.funlib.app.FunDbHelper;
import net.senneco.funlib.sample.data.Repository;
import net.senneco.funlib.sample.data.User;

import java.sql.SQLException;

/**
 * Created by senneco on 31.05.2014
 */
public class GithubDbHelper extends FunDbHelper {

    public GithubDbHelper(Context context) {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, User.class);
            TableUtils.createTable(connectionSource, Repository.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        try {
            TableUtils.dropTable(connectionSource, User.class, true);
            TableUtils.dropTable(connectionSource, Repository.class, true);

            TableUtils.createTable(connectionSource, User.class);
            TableUtils.createTable(connectionSource, Repository.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
