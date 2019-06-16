package com.orange.dgil.trail.core.vecto.linearwindowfilter;

import com.google.common.annotations.VisibleForTesting;
import com.orange.dgil.trail.core.common.TrailPoint;
import com.orange.dgil.trail.core.vecto.SlidingWindow;

public class LinearWindowFilter {
    private final LinearWindowFilterListener linearWindowFilterListener;
    private final SlidingWindow slidingWindow = new SlidingWindow(this.windowWeights.length);
    private int weightsSum;
    private final int[] windowWeights = new int[]{1, 2, 1};

    public LinearWindowFilter(LinearWindowFilterListener linearWindowFilterListener) {
        this.linearWindowFilterListener = linearWindowFilterListener;
        initProperties();
    }

    @VisibleForTesting
    void initProperties() {
        this.weightsSum = getWeightsSum();
    }

    @VisibleForTesting
    int getWeightsSum() {
        int sum = 0;
        for (int weight : this.windowWeights) {
            sum += weight;
        }
        return sum;
    }

    public void reset() {
        this.slidingWindow.reset();
    }

    public TrailPoint getLastPoint() {
        return this.slidingWindow.getLastElement();
    }

    public void addPoint(TrailPoint point) {
        if (!this.slidingWindow.isSameAsLast(point)) {
            doAddPoint(point);
        }
    }

    @VisibleForTesting
    void doAddPoint(TrailPoint point) {
        this.slidingWindow.add(point);
        if (isNewPointAvailable()) {
            computeNewPointAndNotifyListener();
        }
    }

    @VisibleForTesting
    boolean isNewPointAvailable() {
        return this.slidingWindow.getAddedElementsNumber() == 1 || this.slidingWindow.isFull();
    }

    @VisibleForTesting
    void computeNewPointAndNotifyListener() {
        int x;
        int y;
        if (this.slidingWindow.getAddedElementsNumber() == 1) {
            x = this.slidingWindow.getX(0);
            y = this.slidingWindow.getY(0);
        } else {
            x = getMeanX();
            y = getMeanY();
        }
        this.linearWindowFilterListener.onNewFilteredPointAvailable(x, y);
    }

    @VisibleForTesting
    int getMeanX() {
        int sumX = 0;
        for (int i = 0; i < this.windowWeights.length; i++) {
            sumX += this.slidingWindow.getX(i) * this.windowWeights[i];
        }
        return ((this.weightsSum / 2) + sumX) / this.weightsSum;
    }

    @VisibleForTesting
    int getMeanY() {
        int sumY = 0;
        for (int i = 0; i < this.windowWeights.length; i++) {
            sumY += this.slidingWindow.getY(i) * this.windowWeights[i];
        }
        return ((this.weightsSum / 2) + sumY) / this.weightsSum;
    }
}
