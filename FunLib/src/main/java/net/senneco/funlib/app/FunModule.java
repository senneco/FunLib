package net.senneco.funlib.app;

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

    public FunModule(FunApp app) {
        mApiProvider = new FunApiProvider(app.getApi());
    }

    @Provides @Singleton
    FunApiProvider provideApdagiProvider()
    {
        return mApiProvider;
    }
}
