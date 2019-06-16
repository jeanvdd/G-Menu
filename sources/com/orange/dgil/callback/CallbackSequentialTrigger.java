package com.orange.dgil.callback;

import com.orange.dgil.Dgil;
import com.orange.dgil.conf.Metaclasses.Metaclass;

class CallbackSequentialTrigger {
    private static final String SEQ_META_START = "SEQ";
    private final Dgil dgil;
    private DgilCallbacks dgilCallBacks;
    private final CallbackParameters params;
    private boolean seqMoveDetected;

    boolean isSeqMoveDetected() {
        return this.seqMoveDetected;
    }

    void setDgilCallBacks(DgilCallbacks dgilCallBacks) {
        this.dgilCallBacks = dgilCallBacks;
    }

    CallbackSequentialTrigger(Dgil dgil, CallbackParameters callbackParameters) {
        this.dgil = dgil;
        this.params = callbackParameters;
    }

    static boolean isSeqMetaclass(Metaclass metaclass) {
        return metaclass.name().startsWith(SEQ_META_START);
    }

    void onPressEvent() {
        this.seqMoveDetected = false;
    }

    void handleSequential() {
        if (isSequentialEarlyMoveMetaclass()) {
            handleSequentialEarlyMove();
        } else {
            handleSequentialMove();
        }
    }

    private boolean isSequentialEarlyMoveMetaclass() {
        boolean earlyMove;
        boolean cancel = isMetaclass(Metaclass.SEQ_CANCEL_EARLY_MOVE);
        if (isMetaclass(Metaclass.SEQ_EARLY_MOVE_UP) || isMetaclass(Metaclass.SEQ_EARLY_MOVE_DOWN) || isMetaclass(Metaclass.SEQ_EARLY_MOVE_LEFT) || isMetaclass(Metaclass.SEQ_EARLY_MOVE_RIGHT)) {
            earlyMove = true;
        } else {
            earlyMove = false;
        }
        if (earlyMove || cancel) {
            return true;
        }
        return false;
    }

    private void handleSequentialMove() {
        this.seqMoveDetected = true;
        switch (this.params.getMetaclass()) {
            case SEQ_MOVE_UP:
                this.dgil.seqMoveUpDetected();
                this.dgilCallBacks.seqMoveUpDetected();
                return;
            case SEQ_MOVE_DOWN:
                this.dgil.seqMoveDownDetected();
                this.dgilCallBacks.seqMoveDownDetected();
                return;
            case SEQ_MOVE_LEFT:
                this.dgil.seqMoveLeftDetected();
                this.dgilCallBacks.seqMoveLeftDetected();
                return;
            case SEQ_MOVE_RIGHT:
                this.dgil.seqMoveRightDetected();
                this.dgilCallBacks.seqMoveRightDetected();
                return;
            default:
                throwError();
                return;
        }
    }

    private void handleSequentialEarlyMove() {
        int seqRelativePosPercent = this.params.getSeqRelativePosPercent();
        switch (this.params.getMetaclass()) {
            case SEQ_EARLY_MOVE_UP:
                this.dgil.seqEarlyMoveUpDetected(seqRelativePosPercent);
                this.dgilCallBacks.seqEarlyMoveUpDetected(seqRelativePosPercent);
                return;
            case SEQ_EARLY_MOVE_DOWN:
                this.dgil.seqEarlyMoveDownDetected(seqRelativePosPercent);
                this.dgilCallBacks.seqEarlyMoveDownDetected(seqRelativePosPercent);
                return;
            case SEQ_EARLY_MOVE_LEFT:
                this.dgil.seqEarlyMoveLeftDetected(seqRelativePosPercent);
                this.dgilCallBacks.seqEarlyMoveLeftDetected(seqRelativePosPercent);
                return;
            case SEQ_EARLY_MOVE_RIGHT:
                this.dgil.seqEarlyMoveRightDetected(seqRelativePosPercent);
                this.dgilCallBacks.seqEarlyMoveRightDetected(seqRelativePosPercent);
                return;
            case SEQ_CANCEL_EARLY_MOVE:
                this.dgil.seqCancelEarlyMove();
                this.dgilCallBacks.seqCancelEarlyMove();
                return;
            default:
                throwError();
                return;
        }
    }

    private void throwError() {
        throw new DgilInvalidMetaclassException(String.format("Invalid sequential metaclass = %s", new Object[]{this.params.getMetaclass()}));
    }

    private boolean isMetaclass(Metaclass metaclass) {
        return this.params.getMetaclass() == metaclass;
    }
}
