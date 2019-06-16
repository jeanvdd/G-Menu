package com.orange.dgil.msh;

import com.orange.dgil.DgilAndroid;
import com.orange.dgil.nativeinterface.DgilMSHListener;
import com.orange.dgil.nativeinterface.MSHResultsListener;
import com.orange.dgil.trail.android.ITrailDrawer;

public class MSHNativeListener implements DgilMSHListener, IStrokeTimeout {
    private final MSHConfiguration configuration = new MSHConfiguration();
    private DgilAndroid dgil;
    private boolean firstStroke = true;
    private boolean inDrag = true;
    private final MSHResultsListener resultsListener;
    private final TimeoutHandler timeoutHandler = new TimeoutHandler(this);
    private final Trail trail;

    public MSHConfiguration getConfiguration() {
        return this.configuration;
    }

    public MSHNativeListener(DgilAndroid dgil, MSHResultsListener resultsListener) {
        this.resultsListener = resultsListener;
        this.dgil = dgil;
        this.trail = new Trail(dgil.getDrawer(), this.configuration);
    }

    public void setDrawer(ITrailDrawer drawer) {
        this.trail.setDrawer(drawer);
    }

    public void mshCompleted(String symbol, boolean formFactorValid) {
        this.trail.mshCompleted();
        this.resultsListener.mshCompleted(symbol, formFactorValid);
    }

    public void mshRejected() {
        this.trail.mshRejected(this.inDrag);
        this.resultsListener.mshRejected();
    }

    public void mshReset() {
        this.inDrag = true;
        this.trail.mshReset();
    }

    public void mshStartFirstStroke() {
        this.firstStroke = true;
    }

    public void mshStartMoveInStroke() {
        if (this.firstStroke) {
            redraw();
            this.firstStroke = false;
        }
    }

    public void mshResetTimeout() {
        this.timeoutHandler.reset();
    }

    public void mshWaitNextStroke(int nbStrokes, int timeout, boolean errorOnTimeout) {
        this.trail.mshWaitNextStroke(this.inDrag, timeout, errorOnTimeout);
        this.timeoutHandler.startWaiting(timeout);
    }

    public void mshForceDefaultDragMode() {
        this.resultsListener.mshForceDefaultDragMode();
    }

    public void startSymbolic() {
        this.inDrag = false;
        redraw();
    }

    public void onStrokeTimeout() {
        this.dgil.mshStrokeTimeout();
    }

    private void redraw() {
        this.trail.redrawPath(this.inDrag);
    }
}
