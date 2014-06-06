package net.senneco.funlib.loaders;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.AsyncTaskLoader;

/**
 * Created by senneco on 06.06.2014
 * Using blog post http://www.androiddesignpatterns.com/2012/08/implementing-loaders.html
 */
public abstract class FunLoader<T> extends AsyncTaskLoader<LoaderResult<T>> {

    protected Uri mUri;
    protected LoaderResult<T> mResult;
    protected ForceLoadContentObserver mObserver;

    public FunLoader(Context context) {
        this(context, null);
    }

    public FunLoader(Context context, Uri uri) {
        super(context);

        mUri = uri;
        mObserver = new ForceLoadContentObserver();
    }

    @Override
    public LoaderResult<T> loadInBackground() {
        LoaderResult<T> loaderResult = new LoaderResult<T>();

        try {
            T data = loadData();
            loaderResult.setData(data);
        } catch (Throwable throwable) {
            loaderResult.setThrowable(throwable);
        }

        return loaderResult;
    }

    protected abstract T loadData() throws Throwable;

    @Override
    public void deliverResult(LoaderResult<T> data) {
        if (isReset()) {
            releaseResources(data);
            return;
        }

        LoaderResult<T> oldData = mResult;
        mResult = data;

        if (isStarted()) {
            super.deliverResult(data);
        }

        if (oldData != null && oldData != data) {
            releaseResources(oldData);
        }
    }

    protected void releaseResources(LoaderResult<T> result) {
        // pass
    }

    /**
     * Starts an asynchronous load of the contacts list data. When the result is ready the callbacks
     * will be called on the UI thread. If a previous load has been completed and is still valid
     * the result may be passed to the callbacks immediately.
     * <p/>
     * Must be called from the UI thread
     */
    @Override
    protected void onStartLoading() {
        if (mResult != null) {
            deliverResult(mResult);
        }

        if (mUri != null) {
            getContext().getContentResolver().registerContentObserver(mUri, true, mObserver);
        }

        if (takeContentChanged() || mResult == null) {
            forceLoad();
        }
    }

    /**
     * Must be called from the UI thread
     */
    @Override
    protected void onStopLoading() {
        // Attempt to cancel the current load task if possible.
        cancelLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();

        // Ensure the loader is stopped
        onStopLoading();

        // At this point we can release the resources associated with 'mData'.
        if (mResult != null) {
            releaseResources(mResult);
            mResult = null;
        }

        // The Loader is being reset, so we should stop monitoring for changes.
        if (mUri != null) {
            getContext().getContentResolver().unregisterContentObserver(mObserver);
        }
    }

    @Override
    public void onCanceled(LoaderResult<T> result) {
        releaseResources(result);

        super.onCanceled(result);
    }
}
