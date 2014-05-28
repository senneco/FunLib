package net.senneco.funlib.app;

import dagger.Module;
import dagger.Provides;
import net.senneco.funlib.jobs.FunJob;

import javax.inject.Singleton;

/**
 * Created by senneco on 29.05.2014
 */
@Module(injects = FunJob.class)
public class FunModule<T> {

    private final FunApp mApp;
    private final FunApiProvider mApiProvider;

    public FunModule(FunApp app, Class<T> targetClass) {
        mApp = app;
        mApiProvider = new FunApiProvider((T) app.getApi());
    }

    @Provides @Singleton
    FunApiProvider provideApdagiProvider()
    {
        return mApiProvider;
    }
}
