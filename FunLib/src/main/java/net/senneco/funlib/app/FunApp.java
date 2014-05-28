package net.senneco.funlib.app;

import android.app.Application;
import com.path.android.jobqueue.JobManager;
import dagger.ObjectGraph;
import retrofit.RestAdapter;

/**
 * Created by senneco on 29.05.2014
 */
public class FunApp extends Application {

    private ObjectGraph mObjectGraph;
    private JobManager mJobManager;

    protected Object mApi;

    @Override
    public void onCreate() {
        super.onCreate();

        RestAdapter restAdapter = initRestAdapter();

        mApi = restAdapter.create(getApiClass());

        mJobManager = new JobManager(this);

        mObjectGraph = ObjectGraph.create(new FunModule(this, getApiClass()));
        mObjectGraph.injectStatics();
    }

    protected RestAdapter initRestAdapter() {
        return null;
    }

    public static Class getApiClass() {
        return null;
    }

    public JobManager getJobManager() {
        return mJobManager;
    }

    public Object getApi() {
        return mApi;
    }
}
