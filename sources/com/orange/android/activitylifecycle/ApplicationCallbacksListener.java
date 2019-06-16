package com.orange.android.activitylifecycle;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.List;

class ApplicationCallbacksListener implements ActivityLifecycleCallbacks {
    private final Debug debug = new Debug(this.listeners);
    private final SparseArray<List<Lifecycle>> listeners = new SparseArray();

    ApplicationCallbacksListener() {
    }

    public SparseArray<List<Lifecycle>> getListeners() {
        return this.listeners;
    }

    public Debug getDebug() {
        return this.debug;
    }

    void register(Lifecycle lifecycle, Context context) {
        List<Lifecycle> contextListeners = (List) this.listeners.get(context.hashCode());
        if (contextListeners == null) {
            contextListeners = new ArrayList();
            this.listeners.put(context.hashCode(), contextListeners);
            this.debug.register(context);
        }
        contextListeners.add(lifecycle);
    }

    public void onActivityStarted(Activity activity) {
        notify(activity, LifecycleEvent.ON_START);
    }

    public void onActivityResumed(Activity activity) {
        notify(activity, LifecycleEvent.ON_RESUME);
    }

    public void onActivityPaused(Activity activity) {
        notify(activity, LifecycleEvent.ON_PAUSE);
    }

    public void onActivityStopped(Activity activity) {
        notify(activity, LifecycleEvent.ON_STOP);
    }

    public void onActivityDestroyed(Activity activity) {
        notify(activity, LifecycleEvent.ON_DESTROY);
        this.listeners.remove(activity.hashCode());
    }

    private void notify(Context context, LifecycleEvent event) {
        this.debug.showEvent(context, event.toString());
        List<Lifecycle> contextListeners = (List) this.listeners.get(context.hashCode());
        if (contextListeners != null) {
            for (Lifecycle lifecycle : contextListeners) {
                lifecycle.onLifecycleEvent(event);
            }
        }
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        Debug debug = this.debug;
        String str = "ON_CREATE%s";
        Object[] objArr = new Object[1];
        objArr[0] = bundle == null ? "" : " / Bundle: " + bundle.toString();
        debug.showEvent(activity, String.format(str, objArr));
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        Debug debug = this.debug;
        String str = "ON_SAVE_INSTANCE_STATE%s";
        Object[] objArr = new Object[1];
        objArr[0] = bundle == null ? "" : " / Bundle: " + bundle.toString();
        debug.showEvent(activity, String.format(str, objArr));
    }
}
