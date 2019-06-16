package com.orange.dgil.forwardflick;

import android.os.Handler;
import com.google.common.annotations.VisibleForTesting;
import com.orange.dgil.event.IFromDgilMotionEvent;
import com.orange.dgil.nativeinterface.DgilParamTouchscreen;

public class ForwardFlickRunnable implements Runnable {
    private boolean enabled;
    private final IFromDgilMotionEvent fromInterface;
    private final Handler handler;
    private final Interpolator interpolator = new Interpolator(this.points);
    private boolean isForwarding;
    private final Points points = new Points();

    public ForwardFlickRunnable(IFromDgilMotionEvent fromInterface, Handler handler) {
        this.fromInterface = fromInterface;
        this.handler = handler;
    }

    public void reset() {
        this.isForwarding = false;
        resetHandler();
    }

    public void replay() {
        if (this.enabled && this.points.getNbOfPoints() > 2) {
            doReplay();
        }
    }

    @VisibleForTesting
    void doReplay() {
        this.fromInterface.pressFromDgil(this.points.getPoints().x(0), this.points.getPoints().y(0));
        this.isForwarding = true;
        this.points.prepForInterpolation();
        trigHandler();
    }

    public void press(int x, int y, int t) {
        reset();
        if (this.enabled) {
            this.points.getPoints().press(x, y, t);
        }
    }

    public void move(int x, int y, int t) {
        if (this.enabled) {
            this.points.getPoints().move(x, y, t);
        }
    }

    public void release(int x, int y, int t) {
        if (this.enabled) {
            this.points.getPoints().release(x, y, t);
        }
    }

    public void setEnabled(boolean enable, DgilParamTouchscreen ts) {
        this.enabled = enable;
        if (enable) {
            this.points.allocatePoints(ts);
        }
    }

    public void run() {
        if (this.isForwarding) {
            doRun();
        }
    }

    @VisibleForTesting
    void doRun() {
        if (this.interpolator.interpolatePoint()) {
            this.fromInterface.releaseFromDgil(0, 0, 0);
            reset();
            return;
        }
        this.fromInterface.moveFromDgil(this.points.getInterpolateX(), this.points.getInterpolateY());
        trigHandler();
    }

    @VisibleForTesting
    void resetHandler() {
        if (this.handler != null) {
            this.handler.removeCallbacks(this);
        }
    }

    @VisibleForTesting
    void trigHandler() {
        if (this.handler != null) {
            this.handler.post(this);
        }
    }
}
