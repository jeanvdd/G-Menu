package com.orange.dgil.trail.android.drawingtool.markerpen;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.view.View;
import com.orange.dgil.trail.android.drawingtool.DrawingToolsContext;
import com.orange.dgil.trail.android.drawingtool.IDrawingTool;
import com.orange.dgil.trail.android.drawingtool.InvalidateArea;

public class MarkerPaths implements IDrawingTool {
    private final InvalidateArea invalidateArea = new InvalidateArea();
    private final Point middlePos = new Point();
    private final Painters painters;
    private final Path path = new Path();
    private final Point position = new Point();
    private final Point prevMiddlePos = new Point();
    private final Point prevPos = new Point();
    private final RectF rectF = new RectF();
    private final View view;

    public MarkerPaths(DrawingToolsContext drawingToolsContext) {
        this.view = drawingToolsContext.getView();
        this.painters = new Painters(drawingToolsContext.getAnimManager(), drawingToolsContext.getTrailOptions());
    }

    public void reset() {
        this.path.rewind();
    }

    public void draw(Canvas canvas) {
        this.painters.drawPaths(canvas, this.path);
    }

    public void touchDown(int x, int y) {
        this.position.set(x, y);
        this.middlePos.set(x, y);
        this.path.moveTo((float) x, (float) y);
        this.invalidateArea.setRadius(this.painters.getRadius());
    }

    public void touchMove(int x, int y) {
        updatePositions(x, y);
        this.path.quadTo((float) this.prevPos.x, (float) this.prevPos.y, (float) this.middlePos.x, (float) this.middlePos.y);
    }

    public void touchUp() {
    }

    private void updatePositions(int x, int y) {
        this.prevPos.set(this.position.x, this.position.y);
        this.prevMiddlePos.set(this.middlePos.x, this.middlePos.y);
        this.position.set(x, y);
        this.middlePos.set((this.prevPos.x + x) / 2, (this.prevPos.y + y) / 2);
    }

    public void invalidateAreaOnMove() {
        this.invalidateArea.setOrigin(this.prevMiddlePos);
        this.invalidateArea.add(this.prevPos);
        this.invalidateArea.add(this.middlePos);
        this.view.invalidate(this.invalidateArea.getRect());
    }

    public void invalidatePath() {
        if (!this.path.isEmpty()) {
            doInvalidatePath();
        }
    }

    private void doInvalidatePath() {
        this.path.computeBounds(this.rectF, false);
        addRadiusToRect(this.rectF);
        this.rectF.roundOut(this.invalidateArea.getRect());
        this.view.invalidate(this.invalidateArea.getRect());
    }

    private void addRadiusToRect(RectF rF) {
        int radius = this.painters.getRadius();
        rF.left -= (float) radius;
        rF.top -= (float) radius;
        rF.right += (float) radius;
        rF.bottom += (float) radius;
    }

    public void forceRedrawForAnimation(boolean eraseBitmap) {
    }

    public void trimMemory() {
    }
}
