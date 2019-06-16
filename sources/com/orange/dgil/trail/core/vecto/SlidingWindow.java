package com.orange.dgil.trail.core.vecto;

import com.google.common.annotations.VisibleForTesting;
import com.orange.dgil.trail.core.common.TrailPoint;

public class SlidingWindow {
    private int addedElementsNumber;
    private TrailPoint[] points;

    public int getAddedElementsNumber() {
        return this.addedElementsNumber;
    }

    public SlidingWindow(int windowSize) {
        setWindowSize(windowSize);
    }

    public void setWindowSize(int windowSize) {
        this.points = new TrailPoint[windowSize];
        allocatePoints();
    }

    public int getWindowSize() {
        return this.points.length;
    }

    private void allocatePoints() {
        for (int i = 0; i < this.points.length; i++) {
            this.points[i] = new TrailPoint();
        }
    }

    public void reset() {
        this.addedElementsNumber = 0;
    }

    public void add(int x, int y) {
        if (isFull()) {
            slidePointsToTheLeft();
        }
        doAdd(x, y);
    }

    public void add(TrailPoint point) {
        add(point.getX(), point.getY());
    }

    public int getX(int index) {
        if (isIndexValid(index)) {
            return this.points[index].getX();
        }
        throw new SlidingWindowIndexException(String.format("Invalid index '%d' (max index %d)", new Object[]{Integer.valueOf(index), Integer.valueOf(getMaxIndex())}));
    }

    public int getY(int index) {
        if (isIndexValid(index)) {
            return this.points[index].getY();
        }
        throw getInvalidIndexException(index);
    }

    @VisibleForTesting
    boolean isIndexValid(int index) {
        return index >= 0 && index <= getMaxIndex();
    }

    @VisibleForTesting
    int getMaxIndex() {
        return Math.min(this.addedElementsNumber, this.points.length) - 1;
    }

    @VisibleForTesting
    void slidePointsToTheLeft() {
        TrailPoint p0 = this.points[0];
        for (int i = 0; i < this.points.length - 1; i++) {
            this.points[i] = this.points[i + 1];
        }
        this.points[this.points.length - 1] = p0;
    }

    @VisibleForTesting
    void doAdd(int x, int y) {
        this.points[getInsertIndex()].set(x, y);
        this.addedElementsNumber++;
    }

    public TrailPoint getElementAt(int index) {
        if (isIndexValid(index)) {
            return this.points[index];
        }
        throw getInvalidIndexException(index);
    }

    public TrailPoint getLastElement() {
        return getElementAt(getLastElementIndex());
    }

    public boolean isSameAsLast(TrailPoint p) {
        if (this.addedElementsNumber == 0) {
            return false;
        }
        return p.isSameAs(this.points[getLastElementIndex()]);
    }

    @VisibleForTesting
    int getInsertIndex() {
        return Math.min(this.addedElementsNumber, this.points.length - 1);
    }

    public int getLastElementIndex() {
        return Math.min(this.addedElementsNumber - 1, this.points.length - 1);
    }

    public boolean isFull() {
        return this.addedElementsNumber >= this.points.length;
    }

    public void removeElementsAtLeftOf(int index) {
        if (isIndexValid(index)) {
            doRemoveElementsAtLeftOf(index);
            return;
        }
        throw getInvalidIndexException(index);
    }

    private void doRemoveElementsAtLeftOf(int index) {
        for (int i = 0; i <= getLastElementIndex() - index; i++) {
            shiftPoint(i, index + i);
        }
        this.addedElementsNumber -= index;
    }

    private void shiftPoint(int destIndex, int sourceIndex) {
        if (sourceIndex < this.points.length) {
            TrailPoint source = this.points[sourceIndex];
            this.points[destIndex].set(source.getX(), source.getY());
        }
    }

    private SlidingWindowIndexException getInvalidIndexException(int index) {
        return new SlidingWindowIndexException(String.format("Invalid index '%d' (max index %d)", new Object[]{Integer.valueOf(index), Integer.valueOf(getMaxIndex())}));
    }
}
