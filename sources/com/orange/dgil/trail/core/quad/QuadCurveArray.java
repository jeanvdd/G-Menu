package com.orange.dgil.trail.core.quad;

import com.orange.dgil.trail.core.common.TrailPoint;
import com.orange.dgil.trail.core.common.TrailRect;

public class QuadCurveArray {
    private static final int ARRAY_LENGTH_LONG = 4000;
    private static final int ARRAY_LENGTH_SHORT = 500;
    private int index;
    private final TrailPoint[] quadCurveArray;
    private final TrailRect trailRect = new TrailRect();

    public TrailRect getTrailRect() {
        return this.trailRect;
    }

    QuadCurveArray(boolean longCurve) {
        this.quadCurveArray = new TrailPoint[(longCurve ? ARRAY_LENGTH_LONG : ARRAY_LENGTH_SHORT)];
        initArray();
    }

    private void initArray() {
        for (int i = 0; i < this.quadCurveArray.length; i++) {
            this.quadCurveArray[i] = new TrailPoint();
        }
    }

    void reset() {
        this.index = 0;
    }

    void add(int x, int y) {
        if (this.index == this.quadCurveArray.length) {
            throw new QuadCurveArrayException("Quad curve array is full");
        }
        doAdd(x, y);
    }

    private void doAdd(int x, int y) {
        updateTrailRect(x, y);
        this.quadCurveArray[this.index].set(x, y);
        this.index++;
    }

    private void updateTrailRect(int x, int y) {
        if (this.index == 0) {
            this.trailRect.initAtPoint(x, y);
        } else {
            this.trailRect.union(x, y);
        }
    }

    public TrailPoint get(int index) {
        return this.quadCurveArray[index];
    }

    public int getLastPointIndex() {
        return this.index - 1;
    }

    public TrailPoint getLastPoint() {
        return get(getLastPointIndex());
    }

    public boolean isNotEmpty() {
        return this.index > 0;
    }
}
