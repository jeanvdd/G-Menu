package com.orange.dgil.forwardflick;

import com.google.common.annotations.VisibleForTesting;

class Interpolator {
    private final Points points;

    Interpolator(Points points) {
        this.points = points;
    }

    boolean interpolatePoint() {
        long t = this.points.getTimeSincePress();
        if (t < 0) {
            return true;
        }
        doInterpolation(t);
        return false;
    }

    @VisibleForTesting
    void doInterpolation(long t) {
        int index = this.points.getUpdatedIndexForCurrentTime(t);
        float ratio = getInIntervalRatio(index, t);
        this.points.setInterpolatedPoint(getInterpolationForPos(getX(index), getX(index + 1), ratio), getInterpolationForPos(getY(index), getY(index + 1), ratio));
    }

    @VisibleForTesting
    float getInIntervalRatio(int index, long t) {
        int beginT = getT(index);
        float dtInterval = (float) (getT(index + 1) - beginT);
        if (dtInterval == 0.0f) {
            return 0.0f;
        }
        return Math.min(1.0f, ((float) (t - ((long) beginT))) / dtInterval);
    }

    @VisibleForTesting
    int getInterpolationForPos(int begin, int end, float ratio) {
        return ((int) (((float) (end - begin)) * ratio)) + begin;
    }

    @VisibleForTesting
    int getX(int index) {
        return this.points.getPoints().x(index);
    }

    @VisibleForTesting
    int getY(int index) {
        return this.points.getPoints().y(index);
    }

    @VisibleForTesting
    int getT(int index) {
        return this.points.getPoints().t(index);
    }
}
