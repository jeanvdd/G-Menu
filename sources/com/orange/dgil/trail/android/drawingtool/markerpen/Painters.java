package com.orange.dgil.trail.android.drawingtool.markerpen;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import com.orange.dgil.trail.android.animation.AnimManager;
import com.orange.dgil.trail.android.drawingtool.TrailOptions;

class Painters {
    private final AnimManager animManager;
    private final Paint painter = new Paint();
    private Paint shadowPainter;
    private final Path shadowPath = new Path();
    private final TrailOptions trailOptions;

    Painters(AnimManager fadingManager, TrailOptions trailOptions) {
        this.animManager = fadingManager;
        this.trailOptions = trailOptions;
        init();
    }

    void init() {
        initStrokeStyle();
        if (this.trailOptions.isShadowAvailable()) {
            initShadowPainter();
            removeShadowLayers();
            return;
        }
        enableDithering();
    }

    private void initStrokeStyle() {
        this.painter.setAntiAlias(true);
        this.painter.setStyle(Style.STROKE);
        this.painter.setStrokeJoin(Join.ROUND);
        this.painter.setStrokeCap(Cap.ROUND);
    }

    private void initShadowPainter() {
        this.shadowPainter = new Paint(this.painter);
    }

    private void removeShadowLayers() {
        this.painter.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
        this.shadowPainter.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
    }

    private void enableDithering() {
        this.painter.setDither(true);
    }

    void drawPaths(Canvas c, Path path) {
        updateColors();
        updateWidth();
        if (isShadowEnabled()) {
            drawShadowPath(c, path);
        }
        c.drawPath(path, this.painter);
    }

    private boolean isShadowEnabled() {
        if (this.animManager.isRunning()) {
            return this.animManager.getAnimationParameters().isShadowEnabled();
        }
        return this.trailOptions.isShadowEnabled();
    }

    private void drawShadowPath(Canvas c, Path path) {
        int offset = this.trailOptions.getShadowOffsetPixels();
        this.shadowPath.set(path);
        this.shadowPath.offset((float) offset, (float) offset);
        c.drawPath(this.shadowPath, this.shadowPainter);
    }

    private void updateColors() {
        this.painter.setColor(getColor());
        if (this.trailOptions.isShadowEnabled()) {
            this.shadowPainter.setColor(getShadowColor());
        }
    }

    private int getColor() {
        return this.animManager.isRunning() ? this.animManager.getAnimColor() : this.trailOptions.getColor();
    }

    private int getShadowColor() {
        return this.animManager.isRunning() ? this.animManager.getAnimShadowColor() : this.trailOptions.getShadowColor();
    }

    private void updateWidth() {
        float width = ((float) this.trailOptions.getWidthPixels()) * this.animManager.getWidthFactor();
        this.painter.setStrokeWidth(width);
        if (this.trailOptions.isShadowEnabled()) {
            this.shadowPainter.setStrokeWidth(width);
        }
    }

    int getRadius() {
        return getSinglePathRadius() + getShadowExtraRadius();
    }

    private int getSinglePathRadius() {
        return (int) ((((float) (this.trailOptions.getWidthPixels() / 2)) * this.animManager.getWidthFactor()) + 1.0f);
    }

    private int getShadowExtraRadius() {
        return this.trailOptions.isShadowEnabled() ? (this.trailOptions.getShadowOffsetPixels() * 2) + 1 : 0;
    }
}
