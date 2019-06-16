package com.orange.dgil.msh;

import com.google.common.annotations.VisibleForTesting;
import com.orange.dgil.trail.android.ITrailDrawer;

class Trail {
    private MSHAnimationDelays animDelays;
    private MSHColors colors;
    private final MSHConfiguration config;
    private ITrailDrawer drawer;

    public void setDrawer(ITrailDrawer drawer) {
        this.drawer = drawer;
    }

    Trail(ITrailDrawer drawer, MSHConfiguration config) {
        this.drawer = drawer;
        this.config = config;
        init();
    }

    private void init() {
        this.colors = this.config.getColors();
        this.animDelays = this.config.getAnimationDelays();
        this.drawer.getTrailOptions().selectMarkerPen();
    }

    void mshCompleted() {
        setMultistrokeEnabled(false);
        setAnimTimeProperties(this.animDelays.getFadingDelayOk(), this.animDelays.getFadingDurationOk());
        this.drawer.animateAlpha(this.colors.getSymbolicColorOk());
    }

    void mshRejected(boolean inDrag) {
        setMultistrokeEnabled(false);
        if (inDrag) {
            this.drawer.hide();
        } else {
            doSymbolicRejected();
        }
    }

    @VisibleForTesting
    void doSymbolicRejected() {
        setAnimTimeProperties(this.animDelays.getFadingDelayNok(), this.animDelays.getFadingDurationNok());
        this.drawer.animateAlpha(this.colors.getSymbolicColorNok());
    }

    void mshReset() {
        setMultistrokeEnabled(false);
    }

    void mshWaitNextStroke(boolean inDrag, int timeout, boolean errorOnTimeout) {
        if (shouldStartColorFading(inDrag, errorOnTimeout)) {
            startFadingAnimation(inDrag, timeout, errorOnTimeout);
        }
        setMultistrokeEnabled(true);
    }

    @VisibleForTesting
    boolean shouldStartColorFading(boolean inDrag, boolean errorOnTimeout) {
        return (inDrag && errorOnTimeout) ? false : true;
    }

    @VisibleForTesting
    void startFadingAnimation(boolean inDrag, int timeout, boolean errorOnTimeout) {
        if (shouldDoColorFadingWhileWaitingNextStroke(inDrag, errorOnTimeout)) {
            setAnimTimeProperties(0, timeout);
            this.drawer.animate();
        }
    }

    @VisibleForTesting
    boolean shouldDoColorFadingWhileWaitingNextStroke(boolean inDrag, boolean errorOnTimeout) {
        if (isMaybeCompletedColorFadingEnabled(errorOnTimeout)) {
            setAnimColorProperties(getDragOrSymbolicColor(inDrag), this.colors.getSymbolicColorOk());
            return true;
        } else if (!isNotCompletedColorFadingEnabled(errorOnTimeout)) {
            return false;
        } else {
            setAnimColorProperties(getDragOrSymbolicColor(inDrag), this.colors.getSymbolicColorNok());
            return true;
        }
    }

    @VisibleForTesting
    int getDragOrSymbolicColor(boolean inDrag) {
        return inDrag ? this.colors.getDragColor() : this.colors.getSymbolicColor();
    }

    @VisibleForTesting
    boolean isMaybeCompletedColorFadingEnabled(boolean errorOnTimeout) {
        return this.config.isEnableMaybeCompletedFading() && !errorOnTimeout;
    }

    @VisibleForTesting
    boolean isNotCompletedColorFadingEnabled(boolean errorOnTimeout) {
        return this.config.isEnableNotCompletedFading() && errorOnTimeout;
    }

    void redrawPath(boolean inDrag) {
        if (inDrag) {
            redrawPathInDrag();
        } else {
            showAndRedraw(this.colors.getSymbolicColor());
        }
    }

    private void redrawPathInDrag() {
        if (this.colors.isDragColorTransparent()) {
            this.drawer.hide();
        } else {
            showAndRedraw(this.colors.getDragColor());
        }
    }

    private void showAndRedraw(int color) {
        this.drawer.showAndRedrawPath(color);
    }

    @VisibleForTesting
    void setMultistrokeEnabled(boolean enable) {
        this.drawer.setMultistrokeEnabled(enable);
    }

    @VisibleForTesting
    void setAnimTimeProperties(int preAnimDelay, int animDuration) {
        this.drawer.getAnimationParameters().setTimeProperties(preAnimDelay, animDuration);
    }

    @VisibleForTesting
    void setAnimColorProperties(int startColor, int endColor) {
        this.drawer.getAnimationParameters().setColorProperties(startColor, endColor);
    }
}
