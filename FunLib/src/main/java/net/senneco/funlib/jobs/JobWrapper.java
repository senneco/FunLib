package net.senneco.funlib.jobs;

import android.net.Uri;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.path.android.jobqueue.Job;
import de.greenrobot.event.EventBus;
import net.senneco.funlib.app.FunApiProvider;
import net.senneco.funlib.app.FunApp;

import javax.inject.Inject;

/**
 * Created by senneco on 29.05.2014
 */
public class JobWrapper extends Job {

    @Inject
    transient FunApp mFunApp;
    @Inject
    transient FunApiProvider mFunApiProvider;
    @Inject
    transient OrmLiteSqliteOpenHelper mDbHelper;

    private FunJob mJob;
    private Throwable mThrowable;

    public JobWrapper(FunJob job) {
        super(job.getParams());
        mJob = job;
    }

    @Override
    public void onAdded() {
        EventBus.getDefault().postSticky(new JobStateChangeEvent(mJob.getId(), JobStateChangeEvent.JobState.START));
    }

    @Override
    public void onRun() throws Throwable {
        mJob.setApiProvider(mFunApiProvider);
        mJob.setDbHelper(mDbHelper);

        Object result = mJob.doJob();

        Uri uri = mJob.getUri();
        if (uri != null) {
            mFunApp.getContentResolver().notifyChange(mJob.getUri(), null);
        }

        EventBus.getDefault().postSticky(new JobStateChangeEvent(mJob.getId(), JobStateChangeEvent.JobState.COMPLETE, result));
    }

    @Override
    protected void onCancel() {
        EventBus.getDefault().postSticky(new JobStateChangeEvent(mJob.getId(), JobStateChangeEvent.JobState.FAIL, mThrowable));
    }

    @Override
    protected boolean shouldReRunOnThrowable(Throwable throwable) {
        mThrowable = throwable;

        return false;
    }
}
