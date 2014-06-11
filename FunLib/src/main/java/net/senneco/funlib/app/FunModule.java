package net.senneco.funlib.app;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import dagger.Module;
import dagger.Provides;
import net.senneco.funlib.jobs.JobWrapper;

import javax.inject.Singleton;

/**
 * Created by senneco on 29.05.2014
 */
@Module(injects = JobWrapper.class, library = true, complete = false)
public class FunModule {

    private final FunApiProvider mApiProvider;
    private final OrmLiteSqliteOpenHelper mDbHelper;
    private final FunApp mApp;

    public FunModule(FunApp app) {
        mApp = app;
        mApiProvider = new FunApiProvider(app.getApi());
        mDbHelper = app.getDbHelper();
    }

    @Provides @Singleton
    FunApp provideFunApp() {
        return mApp;
    }

    @Provides @Singleton
    OrmLiteSqliteOpenHelper provideDbHelper()
    {
        return mDbHelper;
    }

    @Provides @Singleton
    FunApiProvider provideApiProvider()
    {
        return mApiProvider;
    }
}
