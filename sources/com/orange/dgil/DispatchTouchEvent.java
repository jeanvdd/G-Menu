package com.orange.dgil;

import android.view.MotionEvent;
import com.google.common.annotations.VisibleForTesting;
import com.orange.dgil.conf.Settings;
import com.orange.dgil.event.EventProperties;
import com.orange.dgil.eventfilter.IForwardMotionEvent;
import com.orange.dgil.eventfilter.MultitouchFilter;
import com.orange.dgil.trail.android.ITrailDrawer;
import com.orange.tui.taplog.TapLog;

class DispatchTouchEvent implements IForwardMotionEvent {
    private final Context ctx;
    private final DgilAndroid dgil;
    private final MultitouchFilter multitouchFilter;
    private final ToDgil toDgil;

    DispatchTouchEvent(Context ctx) {
        this.ctx = ctx;
        this.dgil = ctx.getDgil();
        this.multitouchFilter = ctx.getMultitouchFilter();
        this.toDgil = ctx.getToDgil();
    }

    public void dispatchTouchEvent(MotionEvent ev) {
        updateEventProperties(ev);
        this.multitouchFilter.getDispatchEventType(this.ctx.getEventProperties()).dispatchToNextLayer(this.ctx.getEventProperties());
    }

    @VisibleForTesting
    void updateEventProperties(MotionEvent ev) {
        Settings settings = this.dgil.getSettings();
        EventProperties eventProperties = this.ctx.getEventProperties();
        boolean z = settings.getDisableOnMultitouch() && !this.dgil.getSymbolicPatternFlag();
        eventProperties.setMultitouchModeAllowed(z);
        this.ctx.getEventProperties().setSequentialModeEnabled(settings.getSeqEnabled());
        this.ctx.getEventProperties().setEvent(ev);
    }

    public void eventToChildViews(MotionEvent ev) {
        this.ctx.getDgilView().dispatchFromDgilTouchEvent(ev);
    }

    public void eventToDgilAnalysis(MotionEvent ev, int pointerIndex) {
        int actionMasked = ev.getActionMasked();
        this.toDgil.updateCurrentPosition(actionMasked, ev, pointerIndex);
        dispatchEvent(actionMasked, ev);
    }

    public void firstMultitouchEvent(MotionEvent ev) {
        this.dgil.reset();
        boolean inForward = this.dgil.inForward();
        if (inForward) {
            this.dgil.forwardStop();
        } else {
            this.dgil.pressFromDgil(this.toDgil.getDownX(), this.toDgil.getDownY());
        }
        Object[] objArr = new Object[1];
        String str = "Multitouch mode activation, %s";
        Object[] objArr2 = new Object[1];
        objArr2[0] = inForward ? "while forwarding" : "while NOT forwarding";
        objArr[0] = String.format(str, objArr2);
        TapLog.d(this, objArr);
        eventToChildViews(ev);
    }

    @VisibleForTesting
    void dispatchEvent(int actionMasked, MotionEvent ev) {
        switch (actionMasked) {
            case 0:
                dispatchActionDown(ev);
                return;
            case 1:
                dispatchActionUp();
                return;
            case 2:
                dispatchActionMove(ev);
                return;
            case 3:
                dispatchActionCancel(ev);
                return;
            case 6:
                dispatchActionPointerUp();
                return;
            default:
                Object[] objArr = new Object[1];
                objArr[0] = String.format("Invalid motion ev: %s", new Object[]{ev.toString()});
                TapLog.e(this, objArr);
                return;
        }
    }

    @VisibleForTesting
    void dispatchActionDown(MotionEvent ev) {
        this.ctx.getForwardFlick().setEnabled(this.dgil.getSettings().getFlickReplayEnabled(), this.dgil.getTs());
        this.toDgil.pressToDgil();
    }

    @VisibleForTesting
    void dispatchActionMove(MotionEvent ev) {
        this.toDgil.moveToDgil(ev);
    }

    @VisibleForTesting
    void dispatchActionUp() {
        this.toDgil.releaseToDgil();
    }

    @VisibleForTesting
    void dispatchActionPointerUp() {
        if (!this.dgil.isSeqMoveDetected()) {
            dispatchActionUp();
        }
    }

    @VisibleForTesting
    void dispatchActionCancel(MotionEvent ev) {
        Object[] objArr = new Object[1];
        objArr[0] = String.format("ACTION_CANCEL %s", new Object[]{ev.toString()});
        TapLog.d(this, objArr);
        this.toDgil.releaseToDgil();
        ITrailDrawer drawer = this.ctx.getDrawer();
        if (drawer != null) {
            drawer.touchCancel();
        }
    }
}
