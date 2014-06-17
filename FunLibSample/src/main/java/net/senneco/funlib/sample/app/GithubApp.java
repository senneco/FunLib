package net.senneco.funlib.sample.app;

import android.text.TextUtils;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import net.senneco.funlib.app.FunApp;
import net.senneco.funlib.sample.common.PrefUtils;
import retrofit.RequestInterceptor;

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
    protected RequestInterceptor initRequestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                String token = PrefUtils.getToken(GithubApp.this);

                if (!TextUtils.isEmpty(token)) {
                    request.addHeader("Authorization", token);
                }
            }
        };
    }

    @Override
    protected Class<? extends OrmLiteSqliteOpenHelper> initDbHelperClass() {
        return GithubDbHelper.class;
    }
}
