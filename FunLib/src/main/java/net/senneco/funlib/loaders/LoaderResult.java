package net.senneco.funlib.loaders;

/**
 * Created by senneco on 06.06.2014
 */
public class LoaderResult<T> {
    public T mData;
    public Exception mException;

    public LoaderResult() {
        // pass
    }

    public LoaderResult(T data) {
        mData = data;
    }

    public LoaderResult(Exception exception) {
        mException = exception;
    }

    public T getData() {
        return mData;
    }

    public void setData(T data) {
        mData = data;
    }

    public Exception getException() {
        return mException;
    }

    public void setException(Exception exception) {
        mException = exception;
    }
}
