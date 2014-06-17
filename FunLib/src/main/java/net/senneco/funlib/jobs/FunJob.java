package net.senneco.funlib.jobs;

import android.net.Uri;
import android.text.TextUtils;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.path.android.jobqueue.Params;
import net.senneco.funlib.app.FunApiProvider;

import java.io.Serializable;

/**
 * Created by senneco on 29.05.2014
 */
@SuppressWarnings("UnusedDeclaration")
public abstract class FunJob<T> implements Serializable {

    private transient FunApiProvider mApiProvider;
    private transient OrmLiteSqliteOpenHelper mDbHelper;
    private transient Params mParams;

    private final String mUri;

    private int mId;

    public FunJob() {
        this(null);
    }

    public FunJob(Uri uri) {
        this(uri, new Params(1).persist());
    }

    FunJob(Uri uri, Params params) {
        mId = 0;
        mParams = params;
        mUri = uri != null ? uri.toString() : "";
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public void setApiProvider(FunApiProvider apiProvider) {
        mApiProvider = apiProvider;
    }

    public Object getApi() {
        return mApiProvider.getApi();
    }

    public OrmLiteSqliteOpenHelper getDbHelper() {
        return mDbHelper;
    }

    public void setDbHelper(OrmLiteSqliteOpenHelper dbHelper) {
        mDbHelper = dbHelper;
    }

    public Uri getUri() {
        if (TextUtils.isEmpty(mUri)) {
            return null;
        }
        return Uri.parse(mUri);
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

    public static interface OnJobStateChangeListener<T> {

        void onJobStart(int jobId);

        void onJobComplete(int jobId, T result);

        void onJobFail(int jobId, Throwable throwable);
    }
}
