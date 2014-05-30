package net.senneco.funlib.app;

import android.app.Application;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.path.android.jobqueue.BaseJob;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.config.Configuration;
import com.path.android.jobqueue.di.DependencyInjector;
import dagger.ObjectGraph;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import java.lang.reflect.Field;

/**
 * Created by senneco on 29.05.2014
 */
public class FunApp<T> extends Application {

    private static final Gson GSON;

    private Object mApi;
    private JobManager mJobManager;
    private ObjectGraph mObjectGraph;

    static {
        GSON = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setFieldNamingStrategy(new CustomFieldNamingPolicy())
                .setPrettyPrinting()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .serializeNulls()
                .create();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        RestAdapter restAdapter = initRestAdapter();
        Class apiClass = initApiClass();

        if (restAdapter != null && apiClass != null) {
            mApi = restAdapter.create(apiClass);
        }

        Configuration configuration = new Configuration.Builder(this)
                .injector(new DependencyInjector() {
                    @Override
                    public void inject(BaseJob job) {
                        mObjectGraph.inject(job);
                    }
                })
                .id("fun[damental]")
                .build();

        mJobManager = new JobManager(this, configuration);

        mObjectGraph = ObjectGraph.create(new FunModule(this));
    }

    protected String initRestUrl() {
        return null;
    }

    protected RestAdapter initRestAdapter() {
        return new RestAdapter.Builder()
                .setEndpoint(initRestUrl())
                .setConverter(new GsonConverter(GSON))
                .build();
    }

    protected Class initApiClass() {
        return null;
    }

    public JobManager getJobManager() {
        return mJobManager;
    }

    public T getApi() {
        //noinspection unchecked
        return (T) mApi;
    }

    static class CustomFieldNamingPolicy implements FieldNamingStrategy {

        @Override
        public String translateName(Field field) {
            String name = FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES.translateName(field);
            name = name.substring(2, name.length()).toLowerCase();
            return name;
        }
    }
}
