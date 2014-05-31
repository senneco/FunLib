package net.senneco.funlib.jobs;

import com.path.android.jobqueue.Job;
import de.greenrobot.event.EventBus;
import net.senneco.funlib.app.FunApiProvider;

import javax.inject.Inject;

/**
 * Created by senneco on 29.05.2014
 */
public class JobWrapper extends Job {

    @Inject
    transient FunApiProvider mFunApiProvider;

    private FunJob mJob;
    private Throwable mThrowable;

    public JobWrapper(FunJob job) {
        super(job.getParams());
        mJob = job;
    }

    @Override
    public void onAdded() {
        EventBus.getDefault().post(new JobStateChangeEvent(mJob, JobStateChangeEvent.JobState.START));
    }

    @Override
    public void onRun() throws Throwable {
        mJob.setApiProvider(mFunApiProvider);

        Object result = mJob.doJob();

        EventBus.getDefault().post(new JobStateChangeEvent(mJob, JobStateChangeEvent.JobState.COMPLETE, result));
    }

    @Override
    protected void onCancel() {
        EventBus.getDefault().post(new JobStateChangeEvent(mJob, JobStateChangeEvent.JobState.FAIL, mThrowable));
    }

    @Override
    protected boolean shouldReRunOnThrowable(Throwable throwable) {
        mThrowable = throwable;

        return false;
    }
}
