package net.senneco.funlib.sample.app;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import net.senneco.funlib.app.FunApp;

/**
 * Created by senneco on 29.05.2014
 */
public class GithubApp extends FunApp<GithubApi> {

    @Override
    protected String initRestUrl() {
        return "https://api.github.com";
    }

    @Override
    protected Class initApiClass() {
        return GithubApi.class;
    }

    @Override
    protected Class<? extends OrmLiteSqliteOpenHelper> initDbHelperClass() {
        return GithubDbHelper.class;
    }
}