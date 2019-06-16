package com.orange.dgil.forwardflick;

import android.os.SystemClock;
import com.google.common.annotations.VisibleForTesting;
import com.orange.dgil.LowLevelPointsArray;
import com.orange.dgil.nativeinterface.DgilParamTouchscreen;
import com.orange.dgil.trail.android.DefaultDrawerConf;

class Points {
    static final int FLICK_TOUCH_TOLERANCE_UM = 1000;
    static final int MICROMETERS_PER_MM = 1000;
    private long downTime;
    private int interpolateX;
    private int interpolateY;
    private int lastT;
    private LowLevelPointsArray points;
    private int pointsIndex;

    Points() {
    }

    void allocatePoints(DgilParamTouchscreen ts) {
        if (this.points == null) {
            this.points = getNewArray(getTouchToleranceInPixels(ts.getPixelPitchUm()), getMaxNbOfPointsForAFlick(ts.getScreenSizeXMm(), ts.getScreenSizeYMm()));
        }
    }

    @VisibleForTesting
    LowLevelPointsArray getNewArray(int touchTolerance, int nbPoints) {
        return new LowLevelPointsArray(true, touchTolerance, nbPoints);
    }

    void prepForInterpolation() {
        this.lastT = this.points.t(this.points.size() - 1);
        this.pointsIndex = 0;
        this.downTime = getCurrenTime();
    }

    int getUpdatedIndexForCurrentTime(long t) {
        int index = this.pointsIndex;
        int sizeMinus1 = this.points.size() - 1;
        if (index != sizeMinus1) {
            while (index != sizeMinus1 && t > ((long) this.points.t(index))) {
                index++;
            }
            this.pointsIndex = Math.max(index - 1, 0);
        }
        return this.pointsIndex;
    }

    int getInterpolateX() {
        return this.interpolateX;
    }

    int getInterpolateY() {
        return this.interpolateY;
    }

    void setInterpolatedPoint(int interpolateX, int interpolateY) {
        this.interpolateX = interpolateX;
        this.interpolateY = interpolateY;
    }

    LowLevelPointsArray getPoints() {
        return this.points;
    }

    void setPoints(LowLevelPointsArray points) {
        this.points = points;
    }

    int getNbOfPoints() {
        return this.points.size();
    }

    int getPointsIndex() {
        return this.pointsIndex;
    }

    long getTimeSincePress() {
        long t = getCurrenTime() - this.downTime;
        return t > ((long) this.lastT) ? -1 : t;
    }

    @VisibleForTesting
    long getCurrenTime() {
        return SystemClock.uptimeMillis();
    }

    @VisibleForTesting
    int getTouchToleranceInPixels(int pixelPitchUm) {
        return DefaultDrawerConf.QUILL_HEIGHT_UM / pixelPitchUm;
    }

    @VisibleForTesting
    int getMaxNbOfPointsForAFlick(int xSideInMm, int ySideInMm) {
        return ((xSideInMm + ySideInMm) * DefaultDrawerConf.QUILL_HEIGHT_UM) / DefaultDrawerConf.QUILL_HEIGHT_UM;
    }
}
