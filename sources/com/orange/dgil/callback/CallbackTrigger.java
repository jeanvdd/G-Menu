package com.orange.dgil.callback;

import com.orange.dgil.Dgil;

public class CallbackTrigger {
    private final CallbackParameters callbackParameters;
    private final CallbackRegularTrigger regularTrigger;
    private final CallbackSequentialTrigger seqTrigger;

    public CallbackParameters getCallbackParameters() {
        return this.callbackParameters;
    }

    public CallbackTrigger(Dgil dgil) {
        this.callbackParameters = new CallbackParameters(dgil.getSettings());
        this.seqTrigger = new CallbackSequentialTrigger(dgil, this.callbackParameters);
        this.regularTrigger = new CallbackRegularTrigger(dgil, this.callbackParameters);
    }

    public boolean isSeqMoveDetected() {
        return this.seqTrigger.isSeqMoveDetected();
    }

    public void onPressEvent(int x, int y) {
        this.regularTrigger.onPressEvent(x, y);
        this.seqTrigger.onPressEvent();
    }

    public void trigger() {
        if (this.callbackParameters.isShouldTriggerCallback()) {
            triggerCallback();
        }
    }

    private void triggerCallback() {
        if (this.callbackParameters.isSequentialMetaclass()) {
            this.seqTrigger.handleSequential();
        } else {
            this.regularTrigger.handleRegular();
        }
    }

    public void setDgilCallBacks(DgilCallbacks dgilCallBacks) {
        this.seqTrigger.setDgilCallBacks(dgilCallBacks);
        this.regularTrigger.setDgilCallBacks(dgilCallBacks);
    }
}
