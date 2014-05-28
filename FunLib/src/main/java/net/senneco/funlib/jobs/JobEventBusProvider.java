package net.senneco.funlib.jobs;

import android.os.Handler;
import android.os.Looper;
import com.squareup.otto.Bus;

/**
 * Created by senneco on 24.05.2014
 */
public final class JobEventBusProvider {
    private static final Bus BUS = new AndroidBus();

    public static Bus getInstance() {
        return BUS;
    }

    private JobEventBusProvider() {
        // No instances.
    }

    private static class AndroidBus extends Bus {
        private final Handler mainThread = new Handler(Looper.getMainLooper());

        @Override
        public void post(final Object event) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                super.post(event);
            } else {
                mainThread.post(new Runnable() {
                    @Override
                    public void run() {
                        post(event);
                    }
                });
            }
        }
    }
}