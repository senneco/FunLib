package net.senneco.funlib.loaders;

/**
 * Created by senneco on 06.06.2014
 */
public class LoaderResult<T> {
    public T mData;
    public Throwable mThrowable;

    public LoaderResult() {
        // pass
    }

    public LoaderResult(T data) {
        mData = data;
    }

    public LoaderResult(Throwable throwable) {
        mThrowable = throwable;
    }

    public T getData() {
        return mData;
    }

    public void setData(T data) {
        mData = data;
    }

    public Throwable getThrowable() {
        return mThrowable;
    }

    public void setThrowable(Throwable throwable) {
        mThrowable = throwable;
    }
}
