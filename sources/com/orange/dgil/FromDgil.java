package com.orange.dgil;

import android.os.SystemClock;
import android.view.MotionEvent;
import com.google.common.annotations.VisibleForTesting;

public class FromDgil {
    private final Context ctx;
    private boolean flickReplayMode;
    private ForgeParameters forgeParams;
    private boolean gestureWasCancelled;

    FromDgil(Context ctx) {
        this.ctx = ctx;
        init();
    }

    @VisibleForTesting
    void init() {
        this.forgeParams = new ForgeParameters(this.ctx.getEventBuilder(), this);
    }

    void dispatchPressToDgilView(MotionEvent event) {
        this.gestureWasCancelled = isMotionCancelled();
        if (!this.gestureWasCancelled) {
            dispatchEventToDgilView(event);
        }
    }

    void dispatchEventToDgilView(MotionEvent event) {
        DgilView dgilView = this.ctx.getDgilView();
        if (dgilView != null) {
            dgilView.dispatchFromDgilTouchEvent(event);
        }
    }

    @VisibleForTesting
    void doFromDgil(ForgeEvents forgeEvent, int x, int y, long t) {
        this.forgeParams.setEventProperties(x, y, t);
        forgeEvent.forgeAndDispatch(this.forgeParams);
    }

    @VisibleForTesting
    boolean dropEvent() {
        return this.gestureWasCancelled || isMultitouchForwardEnabled();
    }

    void pressFromDgil(int x, int y) {
        long t = getCurrentTime();
        setDownTime(t);
        doFromDgil(ForgeEvents.PRESS, x, y, t);
    }

    void moveFromDgil(int x, int y) {
        if (!dropEvent()) {
            doMoveFromDgil(x, y);
        }
    }

    @VisibleForTesting
    void doMoveFromDgil(int x, int y) {
        if (isMotionCancelled()) {
            doFromDgilCancelled(x, y, getCurrentTime());
        } else {
            doMoveFromDgilOk(x, y);
        }
    }

    @VisibleForTesting
    void doFromDgilCancelled(int x, int y, long t) {
        this.gestureWasCancelled = true;
        doFromDgil(ForgeEvents.RELEASE_CANCEL, x, y, t);
    }

    @VisibleForTesting
    void doMoveFromDgilOk(int x, int y) {
        if (isMoveNotNull(x, y)) {
            doFromDgil(ForgeEvents.MOVE, x, y, getDurationSincePress());
        }
    }

    void releaseFromDgil() {
        if (!dropEvent()) {
            doReleaseFromDgil();
        }
    }

    @VisibleForTesting
    void doReleaseFromDgil() {
        int x = getPreviousX();
        int y = getPreviousY();
        long t = getDurationSincePress();
        if (isMotionCancelled()) {
            doFromDgilCancelled(x, y, t);
        } else {
            doFromDgil(ForgeEvents.RELEASE_UP, x, y, t);
        }
    }

    @VisibleForTesting
    boolean isMoveNotNull(int x, int y) {
        return (x - getPreviousX() == 0 && y - getPreviousY() == 0) ? false : true;
    }

    @VisibleForTesting
    int getPreviousX() {
        return this.ctx.getEventBuilder().getX();
    }

    @VisibleForTesting
    int getPreviousY() {
        return this.ctx.getEventBuilder().getY();
    }

    @VisibleForTesting
    long getDurationSincePress() {
        if (this.flickReplayMode) {
            return getCurrentTime();
        }
        return this.ctx.getEventBuilder().getDownTime() + ((long) this.ctx.getToDgil().getLastEventDt());
    }

    @VisibleForTesting
    void setDownTime(long t) {
        this.ctx.getEventBuilder().setDownTime(t);
    }

    @VisibleForTesting
    long getCurrentTime() {
        return SystemClock.uptimeMillis();
    }

    @VisibleForTesting
    boolean isMotionCancelled() {
        return this.ctx.getGestureInfo().isMotionCancelled();
    }

    @VisibleForTesting
    boolean isMultitouchForwardEnabled() {
        return this.ctx.getMultitouchFilter().isMultitouchForwardEnabled();
    }

    void setFlickReplayMode(boolean flickReplayMode) {
        this.flickReplayMode = flickReplayMode;
    }
}
