package com.orange.dgil.trail.android.drawingtool.quillpen;

import android.graphics.Canvas;
import android.util.Log;
import com.orange.dgil.trail.android.animation.AnimManager;
import com.orange.dgil.trail.android.animation.AnimParameters;
import com.orange.dgil.trail.android.drawingtool.DrawingToolsContext;
import com.orange.dgil.trail.android.drawingtool.IDrawingTool;
import com.orange.dgil.trail.android.drawingtool.TrailOptions;
import com.orange.dgil.trail.core.common.TrailPoint;
import com.orange.dgil.trail.core.quad.QuadCurveArrayException;
import com.orange.dgil.trail.core.vecto.SlidingWindowIndexException;

public class QuillPen implements IDrawingTool, QuillTrailBitmapListener {
    private final AnimManager animManager;
    private final AnimParameters animParameters;
    private final BitmapDrawer bitmapDrawer;
    private boolean inGesture;
    private final TrailPoint lastRawPoint = new TrailPoint();
    private final TrailPoint lastVectoPoint;
    private boolean quadCurveArrayIsFull;
    private final QuadCurveTrail quadCurveTrail;
    private final QuadCurveTrail quadCurveTrailEnd;
    private final QuillTrailBitmap quillTrailBitmap;
    private final QuillTrailBitmapEnd quillTrailBitmapEnd;
    private boolean trailEndEnabled;
    private final TrailOptions trailOptions;

    public QuillPen(DrawingToolsContext drawingToolsContext) {
        this.trailOptions = drawingToolsContext.getTrailOptions();
        this.animManager = drawingToolsContext.getAnimManager();
        this.animParameters = drawingToolsContext.getAnimManager().getAnimationParameters();
        this.bitmapDrawer = new BitmapDrawer(this.trailOptions);
        this.quadCurveTrail = new QuadCurveTrail(true, drawingToolsContext.getAndroidMetrics());
        this.lastVectoPoint = this.quadCurveTrail.getLastVectoPoint();
        this.quadCurveTrailEnd = new QuadCurveTrail(false, drawingToolsContext.getAndroidMetrics());
        this.quillTrailBitmap = new QuillTrailBitmap(drawingToolsContext, this.bitmapDrawer, this.quadCurveTrail.getQuadCurveArray(), this);
        this.quillTrailBitmapEnd = new QuillTrailBitmapEnd(drawingToolsContext, this.bitmapDrawer, this.quadCurveTrailEnd.getQuadCurveArray());
    }

    public void reset() {
        this.quadCurveTrail.reset();
        this.quillTrailBitmap.reset();
    }

    public void trimMemory() {
        Log.i(QuillPen.class.getSimpleName(), "trimMemory, release quill bitmaps");
        this.quillTrailBitmap.trimMemory();
        this.quillTrailBitmapEnd.trimMemory();
    }

    public void forceRedrawForAnimation(boolean eraseBitmap) {
        this.quillTrailBitmap.forceRedrawForAnimation(eraseBitmap);
    }

    public void touchDown(int x, int y) {
        this.inGesture = true;
        this.trailEndEnabled = false;
        this.quadCurveArrayIsFull = false;
        this.lastRawPoint.set(x, y);
        this.bitmapDrawer.updateDrawParameters();
        this.quadCurveTrail.touchDown(x, y);
        this.quillTrailBitmap.touchDown(x, y);
    }

    public void touchMove(int x, int y) {
        this.lastRawPoint.set(x, y);
        if (!this.quadCurveArrayIsFull) {
            addTouchMove(x, y);
        }
    }

    private void addTouchMove(int x, int y) {
        try {
            doAddTouchMove(x, y);
        } catch (QuadCurveArrayException e) {
            this.quadCurveArrayIsFull = true;
        }
    }

    private void doAddTouchMove(int x, int y) {
        this.quadCurveTrail.touchMove(x, y);
        this.quillTrailBitmap.touchMove(x, y);
        addTouchMoveToTrailEnd(x, y);
    }

    private void addTouchMoveToTrailEnd(int x, int y) {
        if (this.trailEndEnabled) {
            this.quadCurveTrailEnd.touchMove(x, y);
            this.quillTrailBitmapEnd.touchMove(x, y);
        }
    }

    public void touchUp() {
        this.inGesture = false;
        tryCurveTrailTouchUp();
        if (!this.quadCurveTrail.getQuadCurveArray().isNotEmpty()) {
            drawSinglePointOnTouchUp();
        }
        drawLastPointsOnTouchUp();
    }

    private void tryCurveTrailTouchUp() {
        try {
            this.quadCurveTrail.touchUp();
        } catch (QuadCurveArrayException e) {
        } catch (SlidingWindowIndexException e2) {
        }
    }

    private void drawLastPointsOnTouchUp() {
        this.quillTrailBitmap.touchUp();
        addTouchUpToTrailEnd();
        invalidatePath();
    }

    private void drawSinglePointOnTouchUp() {
        this.quillTrailBitmap.drawSinglePointAt(this.lastRawPoint);
    }

    private void addTouchUpToTrailEnd() {
        if (this.trailEndEnabled) {
            this.quillTrailBitmapEnd.touchUp();
        }
    }

    public void draw(Canvas canvas) {
        checkAnimationForceRedraw();
        this.quillTrailBitmap.draw(canvas);
        this.quillTrailBitmapEnd.draw(canvas);
    }

    private void checkAnimationForceRedraw() {
        if (this.animManager.isRunning() && !this.animParameters.areStartEndColorsRgbEqual()) {
            forceRedrawForAnimation(true);
        }
    }

    public void invalidateAreaOnMove() {
        this.quillTrailBitmap.invalidateAreaOnMove();
        this.quillTrailBitmapEnd.invalidateAreaOnMove();
    }

    public void invalidatePath() {
        this.quillTrailBitmap.invalidatePath();
    }

    public void onLastDrawnPointChange(int x, int y) {
        if (this.inGesture) {
            initTrailEnd(x, y);
        }
    }

    private void initTrailEnd(int x, int y) {
        if (this.trailEndEnabled) {
            this.quillTrailBitmapEnd.touchUp();
        }
        this.trailEndEnabled = true;
        simulateTrailEndTouchDown(x, y);
        simulateTrailEndTouchMove(this.lastVectoPoint);
        simulateTrailEndTouchMove(this.lastRawPoint);
    }

    private void simulateTrailEndTouchMove(TrailPoint point) {
        int x = point.getX();
        int y = point.getY();
        this.quadCurveTrailEnd.touchMove(x, y);
        this.quillTrailBitmapEnd.touchMove(x, y);
    }

    private void simulateTrailEndTouchDown(int x, int y) {
        this.quadCurveTrailEnd.touchDown(x, y);
        this.quillTrailBitmapEnd.touchDown(x, y);
    }
}
