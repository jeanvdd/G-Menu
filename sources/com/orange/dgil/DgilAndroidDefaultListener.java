package com.orange.dgil;

import android.content.Context;
import com.orange.android.activitylifecycle.ApplicationLifecycle;
import com.orange.android.activitylifecycle.Lifecycle;
import com.orange.android.activitylifecycle.LifecycleEvent;
import com.orange.dgil.callback.DgilDefaultListener;
import com.orange.dgil.callback.ListenerParams;
import com.orange.dgil.trail.android.ITrailDrawer;

public class DgilAndroidDefaultListener extends DgilDefaultListener implements Lifecycle {
    protected final Context context = this.dgilView.getContext();
    protected final DgilAndroid dgilAndroid = ((DgilAndroid) this.dgil);
    protected final DgilView dgilView = this.dgilAndroid.getDgilView();
    private ITrailDrawer listenerDrawer;

    public DgilAndroid dgilAndroid() {
        return this.dgilAndroid;
    }

    public DgilView dgilView() {
        return this.dgilView;
    }

    public Context context() {
        return this.context;
    }

    public ITrailDrawer listenerDrawer() {
        return this.listenerDrawer;
    }

    public DgilAndroidDefaultListener(ListenerParams parameters) {
        super(parameters);
        ApplicationLifecycle.register(this, this.context);
    }

    public void onListenerSelected() {
        super.onListenerSelected();
        if (this.listenerDrawer != null) {
            this.dgilAndroid.setDrawer(this.listenerDrawer);
        }
    }

    public void setListenerDrawer(ITrailDrawer listenerDrawer) {
        this.listenerDrawer = listenerDrawer;
    }

    public void onLifecycleEvent(LifecycleEvent lifecycleEvent) {
        if (lifecycleEvent == LifecycleEvent.ON_PAUSE) {
            saveAdaptation();
            symbolsMayChange();
        }
    }
}
