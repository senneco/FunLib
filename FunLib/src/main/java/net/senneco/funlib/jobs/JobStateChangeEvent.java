package net.senneco.funlib.jobs;

/**
 * Created by senneco on 24.05.2014
 */
public class JobStateChangeEvent {

    private int mJobId;
    private JobState mJobState;
    private Object mResult;

    public JobStateChangeEvent(int jobId, JobState jobState) {
        mJobId = jobId;
        mJobState = jobState;
    }

    public JobStateChangeEvent(int jobId, JobState jobState, Object result) {
        mJobId = jobId;
        mJobState = jobState;
        mResult = result;
    }

    public int getJobId() {
        return mJobId;
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
            return "JobState{" +
                    "mValue='" + mValue + '\'' +
                    '}';
        }
    }
}
