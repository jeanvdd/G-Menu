package com.orange.dgil.aperiodictrigger.impl;

import android.os.Handler;
import android.os.SystemClock;
import com.google.common.annotations.VisibleForTesting;
import com.orange.dgil.aperiodictrigger.AperiodicTrigger;

public class AperiodicTriggerAndroidImpl extends AperiodicTrigger implements Runnable {
    private static final int DEFAULT_TIMEOUT_MS = 16;
    private static final int DEFAULT_TS_PERIOD_MS = 10;
    private boolean disable;
    private final Handler handler = new Handler();
    private long lastTriggerTimeMs;
    private long timeoutMs = 16;

    public void init(long timeoutMs) {
        this.timeoutMs = timeoutMs;
    }

    public void disable() {
        this.disable = true;
    }

    public void start() {
        this.disable = false;
        trigger(true);
    }

    public void run() {
        trigger(false);
    }

    public void trigger(boolean startRequest) {
        if (this.disable) {
            resetTrigger();
        } else {
            doTrigger(startRequest);
        }
    }

    @VisibleForTesting
    void doTrigger(boolean startRequest) {
        this.lastTriggerTimeMs = getDate();
        resetTrigger();
        restartAfterDelay();
        doNativeTrigger(startRequest);
    }

    @VisibleForTesting
    void restartAfterDelay() {
        this.handler.postDelayed(this, this.timeoutMs);
    }

    public void resetTrigger() {
        this.handler.removeCallbacks(this);
    }

    public void check() {
        if (getTimeSinceLastTrigger() > getTriggerOnPointThreshold()) {
            trigger(false);
        }
    }

    @VisibleForTesting
    long getTimeSinceLastTrigger() {
        return getDate() - this.lastTriggerTimeMs;
    }

    @VisibleForTesting
    long getTriggerOnPointThreshold() {
        return this.timeoutMs - 10;
    }

    @VisibleForTesting
    void doNativeTrigger(boolean startRequest) {
        nativeTrigger(startRequest);
    }

    @VisibleForTesting
    long getDate() {
        return SystemClock.currentThreadTimeMillis();
    }
}
