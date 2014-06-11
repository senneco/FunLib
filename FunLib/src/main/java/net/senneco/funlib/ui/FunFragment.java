package net.senneco.funlib.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.util.SparseArray;
import de.greenrobot.event.EventBus;
import net.senneco.funlib.app.FunApp;
import net.senneco.funlib.jobs.FunJob;
import net.senneco.funlib.jobs.JobStateChangeEvent;
import net.senneco.funlib.jobs.JobWrapper;
import net.senneco.funlib.loaders.FunLoader;
import net.senneco.funlib.loaders.LoaderResult;

/**
 * Created by senneco on 29.05.2014
 */
@SuppressWarnings("unchecked")
public class FunFragment extends Fragment implements FunJob.OnJobStateChangeListener, LoaderManager.LoaderCallbacks<LoaderResult>, FunLoader.LoaderListener<Object> {

    private SparseArray<FunJob.OnJobStateChangeListener> mJobStateChangeListeners = new SparseArray<FunJob.OnJobStateChangeListener>();
    private SparseArray<FunLoader<LoaderResult>> mLoaders = new SparseArray<FunLoader<LoaderResult>>();
    private SparseArray<FunLoader.LoaderListener> mLoaderListeners = new SparseArray<FunLoader.LoaderListener>();

    @Override
    public void onResume() {
        super.onResume();

        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();

        EventBus.getDefault().unregister(this);
    }

    public FunApp getApp() {
        return (FunApp) getActivity().getApplication();
    }

    public int getAsyncId(Class asyncClass, Object... params) {
        String id = getActivity().getClass().getSimpleName() + "." + ((Object) this).getClass().getSimpleName() + "." + asyncClass.getSimpleName();

        if (params.length > 0) {
            id += "." + TextUtils.join(".", params);
        }

        return id.hashCode();
    }

    public void startJob(FunJob job) {
        startJob(job.getId(), job, null);
    }

    public void startJob(int id, FunJob job) {
        startJob(id, job, null);
    }

    public void startJob(FunJob job, FunJob.OnJobStateChangeListener stateChangeListener) {
        startJob(job.getId(), job, stateChangeListener);
    }

    public void startJob(int jobId, FunJob job, FunJob.OnJobStateChangeListener stateChangeListener) {
        if (jobId == 0) {
            jobId = getAsyncId(job.getClass());
        }
        job.setId(jobId);

        getApp().getJobManager().addJobInBackground(new JobWrapper(job));

        if (stateChangeListener != null) {
            mJobStateChangeListeners.put(jobId, stateChangeListener);
        }
    }

    public void onEventMainThread(JobStateChangeEvent event) {
        FunJob.OnJobStateChangeListener stateChangeListener = mJobStateChangeListeners.get(event.getJobId());

        if (stateChangeListener == null) {
            stateChangeListener = this;
        }

        switch (event.getJobState()) {
            case START:
                stateChangeListener.onJobStart(event.getJobId());
                break;
            case COMPLETE:
                stateChangeListener.onJobComplete(event.getJobId(), event.getResult());
                break;
            case FAIL:
                stateChangeListener.onJobFail(event.getJobId(), (Throwable) event.getResult());
                break;
        }
    }

    @Override
    public void onJobStart(int jobId) {
        // pass
    }

    @Override
    public void onJobComplete(int jobId, Object result) {
        // pass
    }

    @Override
    public void onJobFail(int jobId, Throwable throwable) {
        // pass
    }

    public void initLoader(FunLoader loader, FunLoader.LoaderListener<?> loaderListener) {
        initLoader(getAsyncId(loader.getClass()), loader, loaderListener);
    }

    public void initLoader(int id, FunLoader loader, FunLoader.LoaderListener<?> loaderListener) {
        mLoaders.put(id, loader);
        mLoaderListeners.put(id, loaderListener);

        getLoaderManager().initLoader(id, null, this);
    }

    public void restartLoader(FunLoader loader, FunLoader.LoaderListener<?> loaderListener) {
        restartLoader(getAsyncId(loader.getClass()), loader, loaderListener);
    }

    public void restartLoader(int id, FunLoader loader, FunLoader.LoaderListener<?> loaderListener) {
        mLoaders.put(id, loader);
        mLoaderListeners.put(id, loaderListener);

        getLoaderManager().restartLoader(id, null, this);
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        FunLoader<LoaderResult> loader = mLoaders.get(id);
        loader.initLoader();

        return loader;
    }

    @Override
    public void onLoadFinished(Loader<LoaderResult> loader, LoaderResult data) {
        FunLoader.LoaderListener loaderListener = mLoaderListeners.get(loader.getId());

        if (loaderListener == null) {
            return;
        }

        //noinspection ThrowableResultOfMethodCallIgnored
        if (data.getException() == null) {
            loaderListener.onLoaderComplete(loader.getId(), data.getData());
        } else {
            loaderListener.onLoaderFail(loader.getId(), data.getException());
        }
    }

    @Override
    public void onLoaderReset(Loader<LoaderResult> loader) {
        onLoaderReset(loader.getId());
    }

    @Override
    public void onLoaderComplete(int loaderId, Object result) {
        // pass
    }

    @Override
    public void onLoaderFail(int loaderId, Exception exception) {
        // pass
    }

    @Override
    public void onLoaderReset(int loaderId) {
        // pass
    }
}
