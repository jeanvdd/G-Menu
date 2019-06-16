package com.orange.dgil;

public class LowLevelPointsArray {
    private static final int XYT_SIZE = 3;
    private final int[] mArray;
    private int mArrayIndex = 0;
    private int mLastX;
    private int mLastY;
    private final int mMinDistInPixel;
    private final boolean mSpatialDecimation;

    public LowLevelPointsArray(boolean spatialDecimation, int minDistInPixel, int nbPts) {
        this.mSpatialDecimation = spatialDecimation;
        this.mMinDistInPixel = minDistInPixel;
        this.mArray = new int[(Math.max(nbPts, 2) * 3)];
    }

    public void press(int x, int y, int t) {
        this.mArrayIndex = 0;
        add(x, y, t);
    }

    public void move(int x, int y, int t) {
        if (this.mArrayIndex < this.mArray.length - 3) {
            if (this.mSpatialDecimation) {
                boolean ex;
                if (Math.abs(x - this.mLastX) > this.mMinDistInPixel) {
                    ex = true;
                } else {
                    ex = false;
                }
                boolean ey;
                if (Math.abs(y - this.mLastY) > this.mMinDistInPixel) {
                    ey = true;
                } else {
                    ey = false;
                }
                if (ex || ey) {
                    add(x, y, t);
                    return;
                }
                return;
            }
            add(x, y, t);
        }
    }

    public void release(int x, int y, int t) {
        add(x, y, t);
    }

    private void add(int x, int y, int t) {
        this.mArray[this.mArrayIndex] = x;
        this.mArray[this.mArrayIndex + 1] = y;
        this.mArray[this.mArrayIndex + 2] = t;
        this.mArrayIndex += 3;
        this.mLastX = x;
        this.mLastY = y;
    }

    public int size() {
        return this.mArrayIndex / 3;
    }

    public int[] deepCopy() {
        int[] array = new int[this.mArrayIndex];
        System.arraycopy(this.mArray, 0, array, 0, this.mArrayIndex);
        return array;
    }

    /* renamed from: x */
    public int m1x(int index) {
        return this.mArray[index * 3];
    }

    /* renamed from: y */
    public int m2y(int index) {
        return this.mArray[(index * 3) + 1];
    }

    /* renamed from: t */
    public int m0t(int index) {
        return this.mArray[(index * 3) + 2];
    }
}
