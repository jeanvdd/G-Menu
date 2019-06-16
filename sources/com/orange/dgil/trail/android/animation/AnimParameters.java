package com.orange.dgil.trail.android.animation;

import android.graphics.Color;
import com.orange.dgil.trail.android.DefaultDrawerConf;

public class AnimParameters {
    private int animDuration = DefaultDrawerConf.ANIM_DURATION_MS;
    private int endColor = getColorWithNullAlpha(this.startColor);
    private int preAnimDelay = 100;
    private boolean shadowEnabled;
    private int shadowEndColor = this.shadowStartColor;
    private int shadowStartColor = -16777216;
    private int startColor = DefaultDrawerConf.TRAIL_COLOR;
    private float widthDilatationFactor;

    public int getPreAnimDelay() {
        return this.preAnimDelay;
    }

    public int getAnimDuration() {
        return this.animDuration;
    }

    public int getStartColor() {
        return this.startColor;
    }

    public int getEndColor() {
        return this.endColor;
    }

    public void setShadowEnabled(boolean shadowEnabled) {
        this.shadowEnabled = shadowEnabled;
    }

    public boolean isShadowEnabled() {
        return this.shadowEnabled;
    }

    public int getShadowStartColor() {
        return this.shadowStartColor;
    }

    public int getShadowEndColor() {
        return this.shadowEndColor;
    }

    public void setWidthDilatationFactor(float widthDilatationFactor) {
        this.widthDilatationFactor = widthDilatationFactor;
    }

    public float getWidthDilatationFactor() {
        return this.widthDilatationFactor;
    }

    private static int getColorWithNullAlpha(int col) {
        return Color.argb(0, Color.red(col), Color.green(col), Color.blue(col));
    }

    public void setTimeProperties(int preAnimDelay, int animDuration) {
        this.preAnimDelay = preAnimDelay;
        this.animDuration = animDuration;
    }

    public void setColorForAlphaAnimation(int color) {
        setColorProperties(color, getColorWithNullAlpha(color));
    }

    public void setColorProperties(int startColor, int endColor) {
        this.startColor = startColor;
        this.endColor = endColor;
    }

    public void setShadowColorForAlphaAnimation(int color) {
        setShadowColorProperties(color, getColorWithNullAlpha(color));
    }

    public void setShadowColorProperties(int startColor, int endColor) {
        this.shadowStartColor = startColor;
        this.shadowEndColor = endColor;
    }

    public boolean areStartEndColorsRgbEqual() {
        return Color.red(this.startColor) == Color.red(this.endColor) && Color.green(this.startColor) == Color.green(this.endColor) && Color.blue(this.startColor) == Color.blue(this.endColor);
    }
}
