package net.senneco.funlib.ui;

import android.support.v4.app.Fragment;
import com.squareup.otto.Subscribe;
import net.senneco.funlib.app.FunApp;
import net.senneco.funlib.jobs.FunJob;
import net.senneco.funlib.jobs.JobEventBusProvider;
import net.senneco.funlib.jobs.JobStateChangeEvent;
import net.senneco.funlib.jobs.JobWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by senneco on 29.05.2014
 */
public class FunFragment extends Fragment implements FunJob.OnJobStateChangeListener {

    private Map<FunJob, FunJob.OnJobStateChangeListener> mJobStateChangeListeners = new HashMap<FunJob, FunJob.OnJobStateChangeListener>();

    public void startJob(FunJob job) {
        ((FunApp) getActivity().getApplication()).getJobManager().addJobInBackground(new JobWrapper(job));
    }

    public void startJob(FunJob job, FunJob.OnJobStateChangeListener stateChangeListener) {
        ((FunApp) getActivity().getApplication()).getJobManager().addJobInBackground(new JobWrapper(job));
        mJobStateChangeListeners.put(job, stateChangeListener);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Register ourselves so that we can provide the initial value.
        JobEventBusProvider.getInstance().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();

        // Always unregister when an object no longer should be on the bus.
        JobEventBusProvider.getInstance().unregister(this);
    }

    @Subscribe
    public void onJobStateChanged(JobStateChangeEvent event) {
        FunJob.OnJobStateChangeListener stateChangeListener = mJobStateChangeListeners.get(event.getJob());

        if (stateChangeListener == null) {
            stateChangeListener = this;
        }

        switch (event.getJobState()) {
            case START:
                stateChangeListener.onStart(event.getJob());
                break;
            case COMPLETE:
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
