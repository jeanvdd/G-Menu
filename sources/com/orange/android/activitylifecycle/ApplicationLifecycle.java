package com.orange.android.activitylifecycle;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import lombok.NonNull;

public class ApplicationLifecycle {
    private static final Object $LOCK = new Object[0];
    private static final ApplicationLifecycle sInstance = new ApplicationLifecycle();
    private final ApplicationCallbacksListener callbacksListener = new ApplicationCallbacksListener();
    private boolean initialized;

    private static void init(Application application) {
        synchronized ($LOCK) {
            if (!sInstance.initialized) {
                application.registerActivityLifecycleCallbacks(sInstance.callbacksListener);
                sInstance.initialized = true;
            }
        }
    }

    public static void register(@NonNull Lifecycle lifecycle, @NonNull Context context) {
        if (lifecycle == null) {
            throw new NullPointerException("lifecycle");
        } else if (context == null) {
            throw new NullPointerException("context");
        } else if (context instanceof Activity) {
            init(((Activity) context).getApplication());
            doRegister(lifecycle, context);
        } else {
            throw new IllegalArgumentException("The provided context is not an activity context; application context ? Please register with the activity context instead.");
        }
    }

    private static void doRegister(Lifecycle lifecycle, Context context) {
        synchronized ($LOCK) {
            sInstance.callbacksListener.register(lifecycle, context);
        }
    }

    public static void reset() {
        synchronized ($LOCK) {
            sInstance.callbacksListener.getListeners().clear();
        }
    }

    public static void setDebugEnabled(boolean enable) {
        sInstance.callbacksListener.getDebug().setEnabled(enable);
    }

    public static void showListeners() {
        sInstance.callbacksListener.getDebug().showListeners();
    }
}
