package net.senneco.funlib.jobs;

import com.path.android.jobqueue.Params;
import net.senneco.funlib.app.FunApiProvider;

import java.io.Serializable;

/**
 * Created by senneco on 29.05.2014
 */
@SuppressWarnings("UnusedDeclaration")
public abstract class FunJob<T> implements Serializable {

    private transient FunApiProvider mApiProvider;
    private transient Params mParams;

    private final int mId;

    public FunJob(int id) {
        this(id, new Params(1).persist());
    }

    FunJob(int id, Params params) {
        mId = id;
        mParams = params;
    }

    public int getId() {
        return mId;
    }

    public void setApiProvider(FunApiProvider apiProvider) {
        mApiProvider = apiProvider;
    }

    public Object getApi() {
        return mApiProvider.getApi();
    }

    public Params getParams() {
        return mParams;
    }

    public void setParams(Params params) {
        mParams = params;
    }

    public abstract T doJob() throws Throwable;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FunJob funJob = (FunJob) o;

        if (mId != funJob.mId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return mId;
    }

    public static interface OnJobStateChangeListener {

        void onStart(FunJob job);

        void onComplete(FunJob job, Object result);

        void onFail(FunJob job, Throwable throwable);
    }
}