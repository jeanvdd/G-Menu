package com.orange.dgil.trail.android.drawingtool.quillpen;

import android.graphics.Canvas;
import android.view.View;
import com.orange.dgil.trail.android.drawingtool.DrawingToolsContext;
import com.orange.dgil.trail.android.drawingtool.IDrawingTool;
import com.orange.dgil.trail.core.common.TrailPoint;
import com.orange.dgil.trail.core.quad.QuadCurveArray;

class QuillTrailBitmapEnd implements IDrawingTool {
    private final BitmapDrawer bitmapDrawer;
    private boolean inGesture;
    private final QuadCurveArray quadCurveArray;
    private final QuillBitmap quillBitmap = new QuillBitmap(this.view);
    private int readIndex;
    private final TrailBounds trailBounds;
    private final View view;

    QuillTrailBitmapEnd(DrawingToolsContext drawingToolsContext, BitmapDrawer bitmapDrawer, QuadCurveArray quadCurveArray) {
        this.view = drawingToolsContext.getView();
        this.quadCurveArray = quadCurveArray;
        this.bitmapDrawer = bitmapDrawer;
        this.trailBounds = new TrailBounds(drawingToolsContext.getTrailOptions().getQuillParameters(), quadCurveArray.getTrailRect());
    }

    public void reset() {
    }

    public void trimMemory() {
        this.quillBitmap.releaseBitmap();
    }

    public void touchDown(int x, int y) {
        this.inGesture = true;
        this.readIndex = 0;
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
        this.inGesture = false;
        this.quillBitmap.reset();
        invalidatePath();
    }

    public void draw(Canvas canvas) {
        if (this.inGesture) {
            handleBitmapDrawing(canvas);
        }
    }

    private void handleBitmapDrawing(Canvas canvas) {
        this.quillBitmap.lazyLoading();
        drawTrail(this.quillBitmap.getBitmapCanvas());
        this.quillBitmap.drawBitmap(canvas);
    }

    private void drawTrail(Canvas canvas) {
        for (int i = this.readIndex; i <= this.quadCurveArray.getLastPointIndex(); i++) {
            TrailPoint point = this.quadCurveArray.get(i);
            this.bitmapDrawer.drawPoint(canvas, point.getX(), point.getY());
        }
        this.readIndex = this.quadCurveArray.getLastPointIndex() + 1;
    }

    public void invalidateAreaOnMove() {
        this.trailBounds.invalidateAreaOnMove(this.view);
    }

    public void invalidatePath() {
        this.trailBounds.invalidatePath(this.view);
    }

    public void forceRedrawForAnimation(boolean eraseBitmap) {
    }
}
