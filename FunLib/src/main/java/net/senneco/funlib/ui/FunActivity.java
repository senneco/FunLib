package net.senneco.funlib.ui;

import android.support.v7.app.ActionBarActivity;
import de.greenrobot.event.EventBus;
import net.senneco.funlib.app.FunApp;
import net.senneco.funlib.jobs.FunJob;
import net.senneco.funlib.jobs.JobStateChangeEvent;
import net.senneco.funlib.jobs.JobWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by senneco on 29.05.2014
 */
public class FunActivity extends ActionBarActivity implements FunJob.OnJobStateChangeListener {

    private Map<FunJob, FunJob.OnJobStateChangeListener> mJobStateChangeListeners = new HashMap<FunJob, FunJob.OnJobStateChangeListener>();

    public void startJob(FunJob job) {
        ((FunApp) getApplication()).getJobManager().addJobInBackground(new JobWrapper(job));
    }

    public void startJob(FunJob job, FunJob.OnJobStateChangeListener stateChangeListener) {
        ((FunApp) getApplication()).getJobManager().addJobInBackground(new JobWrapper(job));
        mJobStateChangeListeners.put(job, stateChangeListener);
    }

    @Override
    protected void onResume() {
        super.onResume();

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(JobStateChangeEvent event) {
        FunJob.OnJobStateChangeListener stateChangeListener = mJobStateChangeListeners.get(event.getJob());

        if (stateChangeListener == null) {
            stateChangeListener = this;
        }

        switch (event.getJobState()) {
            case START:
               stateChangeListener.onStart(event.getJob());
                break;
            case COMPLETE:
                // TODO: DI context or content provider to job for do this work not here!!!
                if (event.getJob().getUri() != null) {
                    getContentResolver().notifyChange(event.getJob().getUri(), null);
                }
                stateChangeListener.onComplete(event.getJob(), event.getResult());
                break;
            case FAIL:
                stateChangeListener.onFail(event.getJob(), (Throwable) event.getResult());
                break;
        }
    }

    @Override
    public void onStart(FunJob job) {

    }

    @Override
    public void onComplete(FunJob job, Object result) {

    }

    @Override
    public void onFail(FunJob job, Throwable throwable) {

    }
}
