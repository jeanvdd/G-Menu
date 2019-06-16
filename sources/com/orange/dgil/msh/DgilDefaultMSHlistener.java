package com.orange.dgil.msh;

import com.google.common.annotations.VisibleForTesting;
import com.orange.dgil.DgilAndroidDefaultListener;
import com.orange.dgil.callback.ListenerParams;
import com.orange.dgil.nativeinterface.MSHResultsListener;
import com.orange.dgil.trail.android.ITrailDrawer;

public class DgilDefaultMSHlistener extends DgilAndroidDefaultListener implements MSHResultsListener {
    private MSHNativeListener mshNativeListener = new MSHNativeListener(this.dgilAndroid, this);
    private boolean tap;

    public DgilDefaultMSHlistener(ListenerParams parameters) {
        super(parameters);
        init();
    }

    public void setListenerDrawer(ITrailDrawer listenerDrawer) {
        super.setListenerDrawer(listenerDrawer);
        this.mshNativeListener.setDrawer(listenerDrawer);
    }

    @VisibleForTesting
    void init() {
        this.dgil.initMSH(this.mshNativeListener);
        this.mshNativeListener.mshForceDefaultDragMode();
    }

    public void onListenerSelected() {
        this.dgil.reset();
        this.dgil.getSettings().setMshEnabled(true);
        super.onListenerSelected();
    }

    public MSHConfiguration getConfiguration() {
        return this.mshNativeListener.getConfiguration();
    }

    public void symbolicPatternDetected() {
        this.mshNativeListener.startSymbolic();
    }

    public void pressDetected(int x, int y) {
        this.tap = true;
    }

    public void startMoveDetected() {
        this.tap = false;
    }

    public boolean wasATap() {
        return this.tap;
    }

    public void mshCompleted(String symbol, boolean formFactorValid) {
    }

    public void mshRejected() {
    }

    public void mshForceDefaultDragMode() {
    }
}
