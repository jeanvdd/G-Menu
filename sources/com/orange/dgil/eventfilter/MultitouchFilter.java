package com.orange.dgil.eventfilter;

import com.google.common.annotations.VisibleForTesting;
import com.orange.dgil.event.EventProperties;

public class MultitouchFilter {
    private int dgilTrackedPointerId;
    private EventProperties ev;
    private boolean multitouchForwardEnabled;

    public DispatchEventType getDispatchEventType(EventProperties eventProperties) {
        this.ev = eventProperties;
        if (this.ev.isActionDown() && this.ev.isMonoTouch()) {
            initOnFirstPointerDown();
        }
        return getDispatchEventType();
    }

    @VisibleForTesting
    void initOnFirstPointerDown() {
        this.dgilTrackedPointerId = this.ev.getPointerIdForIndex(0);
        this.multitouchForwardEnabled = false;
    }

    @VisibleForTesting
    DispatchEventType getDispatchEventType() {
        if (isMultitouchEvent()) {
            return getMultitouchEventType();
        }
        return getMonotouchEventType();
    }

    @VisibleForTesting
    boolean isMultitouchEvent() {
        return this.ev.isMultitouchModeAllowed() && (this.multitouchForwardEnabled || this.ev.isMultiTouch());
    }

    @VisibleForTesting
    DispatchEventType getMultitouchEventType() {
        if (this.multitouchForwardEnabled) {
            return DispatchEventType.DIRECTLY_TO_CHILD_VIEWS;
        }
        this.multitouchForwardEnabled = true;
        return DispatchEventType.DIRECTLY_TO_CHILD_VIEWS_FIRST_MULTITOUCH;
    }

    @VisibleForTesting
    DispatchEventType getMonotouchEventType() {
        updateDgilPointerForTrackedId();
        if (this.ev.isSequentialModeEnabled()) {
            return getSequentialEventType();
        }
        return getNormalEventType();
    }

    @VisibleForTesting
    void updateDgilPointerForTrackedId() {
        this.ev.setDgilPointerIndex(this.ev.getPointerIndexForId(this.dgilTrackedPointerId));
    }

    @VisibleForTesting
    DispatchEventType getNormalEventType() {
        if (this.ev.isDgilPointerValid() && this.ev.isCurrentEventForDgilPointer()) {
            return DispatchEventType.TO_DGIL;
        }
        return DispatchEventType.NO_DISPATCH;
    }

    @VisibleForTesting
    DispatchEventType getSequentialEventType() {
        if (this.ev.isActionMove()) {
            switchToCurrentActionPointer();
        }
        return getNormalEventType();
    }

    @VisibleForTesting
    void switchToCurrentActionPointer() {
        this.dgilTrackedPointerId = this.ev.getPointerId();
        updateDgilPointerForTrackedId();
    }

    public boolean isMultitouchForwardEnabled() {
        return this.multitouchForwardEnabled;
    }
}
