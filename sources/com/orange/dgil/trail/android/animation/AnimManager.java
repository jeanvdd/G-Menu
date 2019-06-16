package com.orange.dgil.trail.android.animation;

import android.graphics.Color;

public class AnimManager {
    private final AnimParameters animParameters = new AnimParameters();
    private final AnimRunnable animRunnable;

    public AnimManager(IAnimDrawer drawer) {
        this.animRunnable = new AnimRunnable(drawer, this.animParameters);
    }

    public void start() {
        this.animRunnable.start();
    }

    public int getAnimColor() {
        return getInterpolatedColor(this.animParameters.getStartColor(), this.animParameters.getEndColor(), this.animRunnable.getFactor());
    }

    public int getAnimShadowColor() {
        return getInterpolatedColor(this.animParameters.getShadowStartColor(), this.animParameters.getShadowEndColor(), this.animRunnable.getFactor());
    }

    private int getInterpolatedColor(int startColor, int endColor, float factor) {
        return Color.argb(getInterp(Color.alpha(startColor), Color.alpha(endColor), factor), getInterp(Color.red(startColor), Color.red(endColor), factor), getInterp(Color.green(startColor), Color.green(endColor), factor), getInterp(Color.blue(startColor), Color.blue(endColor), factor));
    }

    private int getInterp(int start, int end, float factor) {
        return (int) ((((float) start) * factor) + (((float) end) * (1.0f - factor)));
    }

    public float getFactor() {
        return this.animRunnable.getFactor();
    }

    public float getWidthFactor() {
        return (this.animParameters.getWidthDilatationFactor() * (1.0f - getFactor())) + 1.0f;
    }

    public void reset() {
        this.animRunnable.reset();
    }

    public AnimParameters getAnimationParameters() {
        return this.animParameters;
    }

    public boolean isRunning() {
        return this.animRunnable.isRunning();
    }

    public boolean isAlphaAnimationRunning() {
        return isRunning() && this.animParameters.areStartEndColorsRgbEqual();
    }
}
