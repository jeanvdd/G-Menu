package com.orange.dgil.event;

import android.view.MotionEvent;
import com.orange.dgil.eventfilter.IForwardMotionEvent;

public class EventProperties {
    private int dgilPointerIndex;
    private MotionEvent event;
    private IForwardMotionEvent forward;
    private boolean multitouchModeAllowed;
    private boolean sequentialModeEnabled;

    public boolean isActionDown() {
        return this.event.getActionMasked() == 0;
    }

    public boolean isActionMove() {
        return this.event.getActionMasked() == 2;
    }

    public boolean isMonoTouch() {
        return this.event.getPointerCount() == 1;
    }

    public boolean isMultiTouch() {
        return this.event.getPointerCount() > 1;
    }

    public int getPointerIndexForId(int id) {
        return this.event.findPointerIndex(id);
    }

    public int getActionIndex() {
        return this.event.getActionIndex();
    }

    public int getPointerIdForIndex(int index) {
        return this.event.getPointerId(index);
    }

    public int getPointerId() {
        return this.event.getPointerId(this.event.getActionIndex());
    }

    public MotionEvent getEvent() {
        return this.event;
    }

    public void setEvent(MotionEvent event) {
        this.event = event;
    }

    public boolean isMultitouchModeAllowed() {
        return this.multitouchModeAllowed;
    }

    public void setMultitouchModeAllowed(boolean multitouchModeAllowed) {
        this.multitouchModeAllowed = multitouchModeAllowed;
    }

    public boolean isSequentialModeEnabled() {
        return this.sequentialModeEnabled;
    }

    public void setSequentialModeEnabled(boolean sequentialModeEnabled) {
        this.sequentialModeEnabled = sequentialModeEnabled;
    }

    public IForwardMotionEvent getForward() {
        return this.forward;
    }

    public void setForward(IForwardMotionEvent forward) {
        this.forward = forward;
    }

    public int getDgilPointerIndex() {
        return this.dgilPointerIndex;
    }

    public void setDgilPointerIndex(int dgilPointerIndex) {
        this.dgilPointerIndex = dgilPointerIndex;
    }

    public boolean isDgilPointerValid() {
        return this.dgilPointerIndex >= 0;
    }

    public boolean isCurrentEventForDgilPointer() {
        return this.dgilPointerIndex == this.event.getActionIndex();
    }
}
