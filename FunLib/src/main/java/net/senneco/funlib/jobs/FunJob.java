package net.senneco.funlib.jobs;

import com.path.android.jobqueue.Params;
import net.senneco.funlib.app.FunApiProvider;

import javax.inject.Inject;

/**
 * Created by senneco on 29.05.2014
 */
public abstract class FunJob<T> {

    private Params mParams;

    @Inject
    FunApiProvider mApimFunApiProvider;

    public FunJob() {
        this(new Params(1));
    }

    FunJob(Params params) {
        mParams = params;
    }

    public Params getParams() {
        return mParams;
    }

    public void setParams(Params params) {
        mParams = params;
    }

    public abstract T doJob() throws Throwable;

    public static interface OnJobStateChangeListener {
        void onStart(FunJob job);

        void onComplete(FunJob job, Object result);

        void onFail(FunJob job, Throwable throwable);
    }
}
