package net.senneco.funlib.jobs;

/**
 * Created by senneco on 24.05.2014
 */
public class JobStateChangeEvent {

    private FunJob mJob;
    private JobState mJobState;
    private Object mResult;

    public JobStateChangeEvent(FunJob job, JobState jobState) {
        mJob = job;
        mJobState = jobState;
    }

    public JobStateChangeEvent(FunJob job, JobState jobState, Object result) {
        mJob = job;
        mJobState = jobState;
        mResult = result;
    }

    public FunJob getJob() {
        return mJob;
    }

    public JobState getJobState() {
        return mJobState;
    }

    public Object getResult() {
        return mResult;
    }

    public static enum JobState {
        START("start"),
        COMPLETE("complete"),
        FAIL("fail");

        private String mValue;

        JobState(String value) {
            mValue = value;
        }

        @Override
        public String toString() {
            return "WorkerState{" +
                    "mValue='" + mValue + '\'' +
                    '}';
        }
    }
}
