package net.senneco.funlib.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.SparseArray;
import de.greenrobot.event.EventBus;
import net.senneco.funlib.app.FunApp;
import net.senneco.funlib.jobs.FunJob;
import net.senneco.funlib.jobs.JobStateChangeEvent;
import net.senneco.funlib.jobs.JobWrapper;
import net.senneco.funlib.loaders.FunLoader;
import net.senneco.funlib.loaders.LoaderResult;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by senneco on 29.05.2014
 */
public class FunFragment extends Fragment implements FunJob.OnJobStateChangeListener, LoaderManager.LoaderCallbacks<LoaderResult>, FunLoader.LoaderListener<Object> {

    private Map<FunJob, FunJob.OnJobStateChangeListener> mJobStateChangeListeners = new HashMap<FunJob, FunJob.OnJobStateChangeListener>();
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

    public void startJob(FunJob job) {
        ((FunApp) getActivity().getApplication()).getJobManager().addJobInBackground(new JobWrapper(job));
    }

    public void startJob(FunJob job, FunJob.OnJobStateChangeListener stateChangeListener) {
        ((FunApp) getActivity().getApplication()).getJobManager().addJobInBackground(new JobWrapper(job));
        mJobStateChangeListeners.put(job, stateChangeListener);
    }

    public void onEventMainThread(JobStateChangeEvent event) {
        FunJob.OnJobStateChangeListener stateChangeListener = mJobStateChangeListeners.get(event.getJob());

        if (stateChangeListener == null) {
            stateChangeListener = this;
        }

        switch (event.getJobState()) {
            case START:
                stateChangeListener.onJobStart(event.getJob());
                break;
            case COMPLETE:
                // TODO: DI context or content provider to job for do this work not here!!!
                if (event.getJob().getUri() != null) {
                    getActivity().getContentResolver().notifyChange(event.getJob().getUri(), null);
                }
                stateChangeListener.onJobComplete(event.getJob(), event.getResult());
                break;
            case FAIL:
                stateChangeListener.onJobFail(event.getJob(), (Throwable) event.getResult());
                break;
        }
    }

    @Override
    public void onJobStart(FunJob job) {
        // pass
    }

    @Override
    public void onJobComplete(FunJob job, Object result) {
        // pass
    }

    @Override
    public void onJobFail(FunJob job, Throwable throwable) {
        // pass
    }

    public void initLoader(int id, FunLoader loader, FunLoader.LoaderListener<?> loaderListener) {
        mLoaders.put(id, loader);
        mLoaderListeners.put(id, loaderListener);

        getLoaderManager().initLoader(id, null, this);
    }

    public void restartLoader(int id, FunLoader<LoaderResult> loader, FunLoader.LoaderListener<Object> loaderListener) {
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
