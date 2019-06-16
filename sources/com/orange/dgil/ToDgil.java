package com.orange.dgil;

import android.view.MotionEvent;
import com.google.common.annotations.VisibleForTesting;
import com.orange.dgil.event.IToDgil;
import com.orange.dgil.event.MotionEventBuilder;
import com.orange.dgil.trail.android.ITrailDrawer;
import com.orange.tui.taplog.TapLog;

class ToDgil {
    private final Context ctx;
    private int downX;
    private int downY;
    private final MotionEventBuilder eventBuilder;
    private boolean isInGesture;
    private int lastEventDt;
    private int lastX;
    private int lastY;
    private final IToDgil toDgilInterface;

    ToDgil(Context ctx) {
        this.ctx = ctx;
        this.eventBuilder = ctx.getEventBuilder();
        this.toDgilInterface = ctx.getToDgilInterface();
    }

    void reset() {
        this.isInGesture = false;
    }

    @VisibleForTesting
    void updateCurrentPosition(int actionMasked, MotionEvent ev, int pointerIndex) {
        if (actionMasked != 3) {
            this.lastX = (int) ev.getX(pointerIndex);
            this.lastY = (int) ev.getY(pointerIndex);
            this.lastEventDt = (int) (ev.getEventTime() - ev.getDownTime());
        }
    }

    int pressToDgil() {
        if (this.isInGesture) {
            TapLog.w(this, new Object[]{"Press without release"});
        }
        this.eventBuilder.checkPoint(this.lastX, this.lastY);
        int ret = doPressToDgil();
        drawerTouchDown();
        setPressPosition();
        return ret;
    }

    @VisibleForTesting
    int doPressToDgil() {
        this.isInGesture = true;
        this.ctx.getFromDgil().setFlickReplayMode(false);
        this.ctx.getForwardFlick().press(this.lastX, this.lastY, 0);
        return this.toDgilInterface.pressToDgil(this.lastX, this.lastY, 0);
    }

    @VisibleForTesting
    void setPressPosition() {
        this.downX = this.lastX;
        this.downY = this.lastY;
    }

    int moveToDgil(MotionEvent event) {
        if (this.isInGesture) {
            this.eventBuilder.checkPoint(this.lastX, this.lastY);
            return doMoveToDgil(event);
        }
        TapLog.w(this, new Object[]{"Move without press"});
        return 0;
    }

    @VisibleForTesting
    int doMoveToDgil(MotionEvent ev) {
        dispatchMoveHistoricalEvents(ev);
        return dispatchMove(this.lastX, this.lastY, this.lastEventDt, false);
    }

    @VisibleForTesting
    void dispatchMoveHistoricalEvents(MotionEvent ev) {
        for (int i = 0; i < ev.getHistorySize(); i++) {
            dispatchHistoricalEventAtIndex(ev, i);
        }
    }

    @VisibleForTesting
    void dispatchHistoricalEventAtIndex(MotionEvent ev, int i) {
        int x = (int) ev.getHistoricalX(i);
        int y = (int) ev.getHistoricalY(i);
        int t = (int) (ev.getHistoricalEventTime(i) - ev.getDownTime());
        if (shouldDispatch(x, y)) {
            doDispatchHistoricalEvent(x, y, t);
        }
    }

    @VisibleForTesting
    boolean shouldDispatch(int x, int y) {
        return (x == this.lastX && y == this.lastY) ? false : true;
    }

    @VisibleForTesting
    void doDispatchHistoricalEvent(int x, int y, int t) {
        this.eventBuilder.checkPoint(x, y);
        dispatchMove(x, y, t, true);
    }

    @VisibleForTesting
    int dispatchMove(int x, int y, int t, boolean historicalEvent) {
        this.ctx.getForwardFlick().move(x, y, t);
        drawerTouchMove(x, y);
        return this.toDgilInterface.moveToDgil(x, y, (long) t, historicalEvent);
    }

    int releaseToDgil() {
        drawerTouchUp();
        if (this.isInGesture) {
            return doReleaseToDgil();
        }
        TapLog.w(this, new Object[]{"Release without press"});
        return 0;
    }

    @VisibleForTesting
    int doReleaseToDgil() {
        this.isInGesture = false;
        this.ctx.getForwardFlick().release(this.lastX, this.lastY, this.lastEventDt);
        return this.toDgilInterface.releaseToDgil((long) this.lastEventDt);
    }

    @VisibleForTesting
    void drawerTouchDown() {
        ITrailDrawer drawer = this.ctx.getDrawer();
        if (drawer != null) {
            drawer.touchDown(this.lastX, this.lastY);
        }
    }

    @VisibleForTesting
    void drawerTouchMove(int x, int y) {
        ITrailDrawer drawer = this.ctx.getDrawer();
        if (drawer != null) {
            drawer.touchMove(x, y);
        }
    }

    @VisibleForTesting
    void drawerTouchUp() {
        ITrailDrawer drawer = this.ctx.getDrawer();
        if (drawer != null) {
            drawer.touchUp();
        }
    }

    @VisibleForTesting
    boolean isInGesture() {
        return this.isInGesture;
    }

    @VisibleForTesting
    int getDownX() {
        return this.downX;
    }

    @VisibleForTesting
    int getDownY() {
        return this.downY;
    }

    @VisibleForTesting
    int getLastX() {
        return this.lastX;
    }

    @VisibleForTesting
    int getLastY() {
        return this.lastY;
    }

    @VisibleForTesting
    int getLastEventDt() {
        return this.lastEventDt;
    }
}
