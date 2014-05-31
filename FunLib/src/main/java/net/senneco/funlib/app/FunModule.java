package net.senneco.funlib.app;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import dagger.Module;
import dagger.Provides;
import net.senneco.funlib.jobs.JobWrapper;

import javax.inject.Singleton;

/**
 * Created by senneco on 29.05.2014
 */
@Module(injects = JobWrapper.class)
public class FunModule {

    private final FunApiProvider mApiProvider;
    private final OrmLiteSqliteOpenHelper mDbHelper;

    public FunModule(FunApp app) {
        mApiProvider = new FunApiProvider(app.getApi());
        mDbHelper = app.getDbHelper();
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
