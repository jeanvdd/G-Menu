package com.orange.dgil.trail.android.drawingtool.quillpen;

import android.view.View;
import com.orange.dgil.trail.android.drawingtool.InvalidateArea;
import com.orange.dgil.trail.core.common.TrailPoint;
import com.orange.dgil.trail.core.common.TrailRect;

class TrailBounds {
    private final InvalidateArea lastMoveBounds = new InvalidateArea();
    private final TrailPoint lastPoint = new TrailPoint();
    private final QuillParameters quillParameters;
    private final InvalidateArea trailBounds = new InvalidateArea();
    private final TrailRect trailRect;

    public TrailBounds(QuillParameters quillParameters, TrailRect trailRect) {
        this.quillParameters = quillParameters;
        this.trailRect = trailRect;
    }

    void updateTrailBoundsOnTouchDown(int x, int y) {
        updateRadius();
        this.lastMoveBounds.setEmpty();
        this.lastPoint.set(x, y);
    }

    void updateRadius() {
        this.trailBounds.setRadius(this.quillParameters.getRadius());
        this.lastMoveBounds.setRadius(this.quillParameters.getRadius());
    }

    void updateTrailBounds(int x, int y) {
        this.lastMoveBounds.setOrigin(this.lastPoint.getX(), this.lastPoint.getY());
        this.lastMoveBounds.add(x, y);
        this.lastPoint.set(x, y);
    }

    void invalidateAreaOnMove(View view) {
        view.invalidate(this.lastMoveBounds.getRect());
    }

    void invalidatePath(View view) {
        this.trailBounds.setOrigin(this.trailRect.getLeft(), this.trailRect.getTop());
        this.trailBounds.add(this.trailRect.getRight(), this.trailRect.getBottom());
        view.invalidate(this.trailBounds.getRect());
    }
}
