package com.orange.dgil.callback;

import com.orange.dgil.Dgil;
import com.orange.dgil.conf.Metaclasses.Metaclass;

class CallbackRegularTrigger {
    private final Dgil dgil;
    private DgilCallbacks dgilCallBacks;
    private int downX;
    private int downY;
    private final CallbackParameters params;

    void setDgilCallBacks(DgilCallbacks dgilCallBacks) {
        this.dgilCallBacks = dgilCallBacks;
    }

    CallbackRegularTrigger(Dgil dgil, CallbackParameters callbackParameters) {
        this.dgil = dgil;
        this.params = callbackParameters;
    }

    void handleRegular() {
        if (isMetaclassOfPressType()) {
            handlePressType();
        } else if (isMetaclassOfDoubleTapType()) {
            handleDoubleTapType();
        } else if (isMetaclassOfDragType()) {
            handleDragType();
        } else if (isMetaclassOfFlickOrSymbolicType()) {
            handleFlickOrSymbolicType();
        } else if (isMetaclassOfRotationType()) {
            handleRotationType();
        } else {
            handleEndGesture();
        }
    }

    private boolean isMetaclassOfPressType() {
        boolean pressOrShortPress;
        boolean tap = isMetaclass(Metaclass.TAP);
        if (isMetaclass(Metaclass.PRESS) || isMetaclass(Metaclass.SHORT_PRESS)) {
            pressOrShortPress = true;
        } else {
            pressOrShortPress = false;
        }
        boolean longPress;
        if (isMetaclass(Metaclass.LONG_PRESS) || isMetaclass(Metaclass.LONG_LONG_PRESS) || isMetaclass(Metaclass.RELEASE_LONG_PRESS) || isMetaclass(Metaclass.RELEASE_LONG_LONG_PRESS)) {
            longPress = true;
        } else {
            longPress = false;
        }
        if (pressOrShortPress || longPress || tap) {
            return true;
        }
        return false;
    }

    private void handlePressType() {
        switch (this.params.getMetaclass()) {
            case PRESS:
                this.dgil.pressDetected(this.downX, this.downY);
                this.dgilCallBacks.pressDetected(this.downX, this.downY);
                return;
            case SHORT_PRESS:
                this.dgil.shortPressDetected(this.downX, this.downY);
                this.dgilCallBacks.shortPressDetected(this.downX, this.downY);
                return;
            case LONG_PRESS:
                this.dgil.longPressDetected(this.downX, this.downY);
                this.dgilCallBacks.longPressDetected(this.downX, this.downY);
                return;
            case LONG_LONG_PRESS:
                this.dgil.longLongPressDetected(this.downX, this.downY);
                this.dgilCallBacks.longLongPressDetected(this.downX, this.downY);
                return;
            case RELEASE_LONG_PRESS:
                this.dgil.releaseLongPressDetected(this.downX, this.downY);
                this.dgilCallBacks.releaseLongPressDetected(this.downX, this.downY);
                return;
            case RELEASE_LONG_LONG_PRESS:
                this.dgil.releaseLongLongPressDetected(this.downX, this.downY);
                this.dgilCallBacks.releaseLongLongPressDetected(this.downX, this.downY);
                return;
            case TAP:
                this.dgil.tapDetected(this.downX, this.downY);
                this.dgilCallBacks.tapDetected(this.downX, this.downY);
                return;
            default:
                throwError();
                return;
        }
    }

    private boolean isMetaclassOfDoubleTapType() {
        return isMetaclass(Metaclass.PENDING_DOUBLETAP) || isMetaclass(Metaclass.DOUBLETAP_PRESS) || isMetaclass(Metaclass.DOUBLETAP_RELEASE) || isMetaclass(Metaclass.LONG_PRESS_DOUBLETAP);
    }

    private void handleDoubleTapType() {
        switch (this.params.getMetaclass()) {
            case PENDING_DOUBLETAP:
                this.dgil.pendingDoubleTapDetected();
                this.dgilCallBacks.pendingDoubleTapDetected();
                return;
            case DOUBLETAP_PRESS:
                this.dgil.doubleTapPressDetected();
                this.dgilCallBacks.doubleTapPressDetected();
                return;
            case DOUBLETAP_RELEASE:
                this.dgil.doubleTapDetected();
                this.dgilCallBacks.doubleTapDetected();
                return;
            case LONG_PRESS_DOUBLETAP:
                this.dgil.longPressDoubleTapDetected();
                this.dgilCallBacks.longPressDoubleTapDetected();
                return;
            default:
                throwError();
                return;
        }
    }

    private boolean isMetaclassOfDragType() {
        return isMetaclass(Metaclass.START_MOVE) || isMetaclass(Metaclass.DRAG) || isMetaclass(Metaclass.BREAK_DRAG) || isMetaclass(Metaclass.END_DRAG);
    }

    private void handleDragType() {
        switch (this.params.getMetaclass()) {
            case START_MOVE:
                this.dgil.startMoveDetected();
                this.dgilCallBacks.startMoveDetected();
                return;
            case DRAG:
                this.dgil.dragDetected();
                this.dgilCallBacks.dragDetected();
                return;
            case BREAK_DRAG:
                this.dgil.breakDragDetected();
                this.dgilCallBacks.breakDragDetected();
                return;
            case END_DRAG:
                this.dgil.endDragDetected();
                this.dgilCallBacks.endDragDetected();
                return;
            default:
                throwError();
                return;
        }
    }

    private boolean isMetaclassOfFlickOrSymbolicType() {
        boolean flick;
        if (isMetaclass(Metaclass.FAST_STRAIGHT) || isMetaclass(Metaclass.FLICK) || isMetaclass(Metaclass.BREAK_FLICK)) {
            flick = true;
        } else {
            flick = false;
        }
        boolean symbolic;
        if (isMetaclass(Metaclass.SYMBOLIC_PATTERN) || isMetaclass(Metaclass.SYMBOLIC) || isMetaclass(Metaclass.BREAK_SYMBOLIC)) {
            symbolic = true;
        } else {
            symbolic = false;
        }
        if (flick || symbolic) {
            return true;
        }
        return false;
    }

    private void handleFlickOrSymbolicType() {
        switch (this.params.getMetaclass()) {
            case FAST_STRAIGHT:
                this.dgil.fastStraightDetected();
                this.dgilCallBacks.fastStraightDetected();
                return;
            case SYMBOLIC_PATTERN:
                this.dgilCallBacks.preSymbolicPatternDetected();
                this.dgil.symbolicPatternDetected();
                this.dgilCallBacks.symbolicPatternDetected();
                return;
            case SYMBOLIC:
                handleSymbolicMeta(this.params.getSymbolParameters());
                return;
            case FLICK:
                this.dgil.flickDetected(this.params.getFlickParameters());
                this.dgilCallBacks.flickDetected(this.params.getFlickParameters());
                return;
            case BREAK_SYMBOLIC:
                this.dgil.breakSymbolicDetected(this.params.getSymbolParameters());
                this.dgilCallBacks.breakSymbolicDetected(this.params.getSymbolParameters());
                return;
            case BREAK_FLICK:
                this.dgil.breakFlickDetected(this.params.getFlickParameters());
                this.dgilCallBacks.breakFlickDetected(this.params.getFlickParameters());
                return;
            default:
                throwError();
                return;
        }
    }

    private boolean isMetaclassOfRotationType() {
        boolean direction;
        if (isMetaclass(Metaclass.CLOCKWISE_ROTATION) || isMetaclass(Metaclass.ANTICLOCKWISE_ROTATION)) {
            direction = true;
        } else {
            direction = false;
        }
        boolean rotation;
        if (isMetaclass(Metaclass.REGULAR_CURVED) || isMetaclass(Metaclass.BREAK_ROTATION) || isMetaclass(Metaclass.END_ROTATION)) {
            rotation = true;
        } else {
            rotation = false;
        }
        if (direction || rotation) {
            return true;
        }
        return false;
    }

    private void handleRotationType() {
        switch (this.params.getMetaclass()) {
            case REGULAR_CURVED:
                this.dgil.regularCurvedDetected();
                this.dgilCallBacks.regularCurvedDetected();
                return;
            case CLOCKWISE_ROTATION:
                this.dgil.clockwiseRotationDetected(this.params.getRotationAngle());
                this.dgilCallBacks.clockwiseRotationDetected(this.params.getRotationAngle());
                return;
            case ANTICLOCKWISE_ROTATION:
                this.dgil.anticlockwiseRotationDetected(this.params.getRotationAngle());
                this.dgilCallBacks.anticlockwiseRotationDetected(this.params.getRotationAngle());
                return;
            case BREAK_ROTATION:
                this.dgil.breakRotationDetected();
                this.dgilCallBacks.breakRotationDetected();
                return;
            case END_ROTATION:
                this.dgil.endRotationDetected();
                this.dgilCallBacks.endRotationDetected();
                return;
            default:
                throwError();
                return;
        }
    }

    private void handleEndGesture() {
        if (this.params.getMetaclass() == Metaclass.END_GESTURE) {
            this.dgil.endGestureDetected();
            this.dgilCallBacks.endGestureDetected();
            return;
        }
        throwError();
    }

    private void handleSymbolicMeta(SymbolParameters params) {
        if (!this.dgil.symbolicDetectedInternal(params.symbol1(), params.symbolSim1())) {
            this.dgil.symbolicDetected(params);
            this.dgilCallBacks.symbolicDetected(params);
        }
    }

    private void throwError() {
        throw new DgilInvalidMetaclassException(String.format("Invalid regular metaclass = %s", new Object[]{this.params.getMetaclass()}));
    }

    private boolean isMetaclass(Metaclass metaclass) {
        return this.params.getMetaclass() == metaclass;
    }

    void onPressEvent(int x, int y) {
        this.downX = x;
        this.downY = y;
    }
}
