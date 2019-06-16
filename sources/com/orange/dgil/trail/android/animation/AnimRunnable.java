package com.orange.dgil.trail.android.animation;

import android.view.View;
import android.view.animation.AnimationUtils;
import com.google.common.annotations.VisibleForTesting;

class AnimRunnable implements Runnable {
    private final AnimParameters animParameters;
    private final IAnimDrawer drawer;
    private float factor;
    private boolean running;
    private long startTime;

    public boolean isRunning() {
        return this.running;
    }

    public float getFactor() {
        return this.factor;
    }

    AnimRunnable(IAnimDrawer drawer, AnimParameters animParameters) {
        this.drawer = drawer;
        this.animParameters = animParameters;
    }

    @VisibleForTesting
    void start() {
        reset();
        setStartTime(this.animParameters.getPreAnimDelay());
        this.running = true;
        this.drawer.invalidatePath();
        updateAfter(this.animParameters.getPreAnimDelay());
    }

    @VisibleForTesting
    void updateAfter(int delayMillis) {
        View v = this.drawer.getView();
        if (delayMillis == 0) {
            v.post(this);
        } else {
            v.postDelayed(this, (long) delayMillis);
        }
    }

    void reset() {
        this.factor = 1.0f;
        this.running = false;
        this.drawer.getView().removeCallbacks(this);
    }

    @VisibleForTesting
    void setStartTime(int preAnimDelay) {
        this.startTime = getCurrentTime() + ((long) preAnimDelay);
    }

    @VisibleForTesting
    long getRemainingDuration() {
        return Math.max((this.startTime + ((long) this.animParameters.getAnimDuration())) - getCurrentTime(), 0);
    }

    private long getCurrentTime() {
        return AnimationUtils.currentAnimationTimeMillis();
    }

    public void run() {
        if (this.running) {
            doRun();
        }
        this.drawer.invalidatePath();
    }

    @VisibleForTesting
    void doRun() {
        long remainingDuration = getRemainingDuration();
        if (remainingDuration > 0) {
            update(remainingDuration);
        } else {
            end();
        }
    }

    @VisibleForTesting
    void end() {
        reset();
        this.drawer.animationFinished();
    }

    @VisibleForTesting
    void update(long remainingDuration) {
        this.factor = getTimeFactor(remainingDuration);
        updateAfter(0);
    }

    @VisibleForTesting
    float getTimeFactor(long remainingDuration) {
        return ((float) remainingDuration) / ((float) this.animParameters.getAnimDuration());
    }
}
