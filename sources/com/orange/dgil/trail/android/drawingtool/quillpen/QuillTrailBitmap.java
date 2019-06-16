package com.orange.dgil.trail.android.drawingtool.quillpen;

import android.graphics.Canvas;
import android.view.View;
import com.orange.dgil.trail.android.animation.AnimManager;
import com.orange.dgil.trail.android.drawingtool.DrawingToolsContext;
import com.orange.dgil.trail.android.drawingtool.IDrawingTool;
import com.orange.dgil.trail.core.common.TrailPoint;
import com.orange.dgil.trail.core.quad.QuadCurveArray;

class QuillTrailBitmap implements IDrawingTool {
    private final AnimManager animManager;
    private final BitmapDrawer bitmapDrawer;
    private boolean inGesture;
    private final QuadCurveArray quadCurveArray;
    private final QuillBitmap quillBitmap = new QuillBitmap(this.view);
    private final QuillTrailBitmapListener quillTrailBitmapListener;
    private int readIndex;
    private boolean shouldDrawTrailEnd;
    private final TrailBounds trailBounds;
    private final View view;

    QuillTrailBitmap(DrawingToolsContext drawingToolsContext, BitmapDrawer bitmapDrawer, QuadCurveArray quadCurveArray, QuillTrailBitmapListener quillTrailBitmapListener) {
        this.view = drawingToolsContext.getView();
        this.animManager = drawingToolsContext.getAnimManager();
        this.quadCurveArray = quadCurveArray;
        this.bitmapDrawer = bitmapDrawer;
        this.quillTrailBitmapListener = quillTrailBitmapListener;
        this.trailBounds = new TrailBounds(drawingToolsContext.getTrailOptions().getQuillParameters(), quadCurveArray.getTrailRect());
    }

    public void reset() {
        if (this.quillBitmap.isLoaded()) {
            this.readIndex = 0;
            this.quillBitmap.reset();
        }
    }

    public void trimMemory() {
        this.quillBitmap.releaseBitmap();
    }

    public void forceRedrawForAnimation(boolean eraseBitmap) {
        this.quillBitmap.lazyLoading();
        this.bitmapDrawer.forceColor(this.animManager.getAnimColor());
        if (eraseBitmap) {
            this.quillBitmap.reset();
        }
        for (int i = 0; i <= this.quadCurveArray.getLastPointIndex(); i++) {
            TrailPoint point = this.quadCurveArray.get(i);
            this.bitmapDrawer.drawPoint(this.quillBitmap.getBitmapCanvas(), point.getX(), point.getY());
        }
    }

    public void touchDown(int x, int y) {
        this.inGesture = true;
        this.readIndex = 0;
        this.quillBitmap.resetAlpha();
        this.trailBounds.updateTrailBoundsOnTouchDown(x, y);
    }

    public void touchMove(int x, int y) {
        updateTrailBounds();
    }

    private void updateTrailBounds() {
        if (this.quadCurveArray.isNotEmpty()) {
            TrailPoint lastPoint = this.quadCurveArray.getLastPoint();
            this.trailBounds.updateTrailBounds(lastPoint.getX(), lastPoint.getY());
        }
    }

    public void touchUp() {
        updateTrailBounds();
        invalidateAreaOnMove();
        this.shouldDrawTrailEnd = true;
        this.inGesture = false;
    }

    public void draw(Canvas canvas) {
        handleBitmapDrawing();
        if (this.quillBitmap.isLoaded()) {
            this.quillBitmap.drawBitmap(canvas);
        }
    }

    private void handleBitmapDrawing() {
        if (shouldDrawTrailInBitmap()) {
            drawTrailInBitmap();
        } else if (this.animManager.isAlphaAnimationRunning()) {
            animateBitmap();
        }
    }

    private boolean shouldDrawTrailInBitmap() {
        return this.shouldDrawTrailEnd || this.inGesture;
    }

    private void drawTrailInBitmap() {
        this.quillBitmap.lazyLoading();
        this.shouldDrawTrailEnd = false;
        drawTrail(this.quillBitmap.getBitmapCanvas());
        checkDrawChange();
        updateReadIndex();
    }

    private void drawTrail(Canvas canvas) {
        for (int i = this.readIndex; i <= this.quadCurveArray.getLastPointIndex(); i++) {
            TrailPoint point = this.quadCurveArray.get(i);
            this.bitmapDrawer.drawPoint(canvas, point.getX(), point.getY());
        }
    }

    private void checkDrawChange() {
        if (this.readIndex < this.quadCurveArray.getLastPointIndex()) {
            TrailPoint point = this.quadCurveArray.getLastPoint();
            this.quillTrailBitmapListener.onLastDrawnPointChange(point.getX(), point.getY());
        }
    }

    private void updateReadIndex() {
        this.readIndex = this.quadCurveArray.getLastPointIndex() + 1;
    }

    private void animateBitmap() {
        this.quillBitmap.setAlpha((int) (255.0f * this.animManager.getFactor()));
    }

    public void invalidateAreaOnMove() {
        this.trailBounds.invalidateAreaOnMove(this.view);
    }

    public void invalidatePath() {
        this.trailBounds.invalidatePath(this.view);
    }

    void drawSinglePointAt(TrailPoint point) {
        this.quillBitmap.lazyLoading();
        this.bitmapDrawer.drawCirclePoint(this.quillBitmap.getBitmapCanvas(), point.getX(), point.getY());
        this.trailBounds.updateTrailBounds(point.getX(), point.getY());
    }
}
