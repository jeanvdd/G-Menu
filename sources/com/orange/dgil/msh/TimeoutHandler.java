package com.orange.dgil.msh;

import android.os.Handler;
import android.os.Message;
import com.google.common.annotations.VisibleForTesting;

class TimeoutHandler extends Handler {
    private boolean isWaiting = false;
    private final IStrokeTimeout strokeTimeoutListener;

    TimeoutHandler(IStrokeTimeout strokeTimeoutListener) {
        this.strokeTimeoutListener = strokeTimeoutListener;
    }

    boolean isWaiting() {
        return this.isWaiting;
    }

    void startWaiting(int timeout) {
        this.isWaiting = true;
        sendMessageAfterTimeout(timeout);
    }

    @VisibleForTesting
    void sendMessageAfterTimeout(int timeout) {
        sendEmptyMessageDelayed(0, (long) timeout);
    }

    void reset() {
        this.isWaiting = false;
        removeMessages();
    }

    @VisibleForTesting
    void removeMessages() {
        removeMessages(0);
    }

    public void handleMessage(Message msg) {
        this.isWaiting = false;
        this.strokeTimeoutListener.onStrokeTimeout();
    }
}
