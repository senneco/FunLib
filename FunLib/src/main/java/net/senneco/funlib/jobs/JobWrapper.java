package net.senneco.funlib.jobs;

import com.path.android.jobqueue.Job;

/**
 * Created by senneco on 29.05.2014
 */
public class JobWrapper extends Job {

    private FunJob mJob;
    private Throwable mThrowable;

    public JobWrapper(FunJob job) {
        super(job.getParams());
        mJob = job;
    }

    @Override
    public void onAdded() {
        JobEventBusProvider.getInstance().post(new JobStateChangeEvent(mJob, JobStateChangeEvent.JobState.START));
    }

    @Override
    public void onRun() throws Throwable {
        Object result = mJob.doJob();

        JobEventBusProvider.getInstance().post(new JobStateChangeEvent(mJob, JobStateChangeEvent.JobState.COMPLETE, result));
    }

    @Override
    protected void onCancel() {
        JobEventBusProvider.getInstance().post(new JobStateChangeEvent(mJob, JobStateChangeEvent.JobState.FAIL, mThrowable));
    }

    @Override
    protected boolean shouldReRunOnThrowable(Throwable throwable) {
        mThrowable = throwable;

        return false;
    }
}
