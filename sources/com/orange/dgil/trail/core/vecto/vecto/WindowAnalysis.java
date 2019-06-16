package com.orange.dgil.trail.core.vecto.vecto;

import com.orange.dgil.trail.core.common.Vector;
import com.orange.dgil.trail.core.vecto.SlidingWindow;

public class WindowAnalysis {
    private static final int DEFAULT_WINDOW_SIZE = 8;
    private int lastIndex;
    private int lastSalientPointIndex;
    private final OnWindowSizeListener onWindowSizeListener;
    private boolean salientPointFoundInRange;
    private boolean salientPointFoundInWindow;
    private final SlidingWindow slidingWindow = new SlidingWindow(8);
    private final Vector v13 = new Vector();
    private final VectoSettings vectoSettings = new VectoSettings(this);

    public WindowAnalysis(OnWindowSizeListener onWindowSizeListener) {
        this.onWindowSizeListener = onWindowSizeListener;
    }

    public VectoSettings getVectoSettings() {
        return this.vectoSettings;
    }

    public SlidingWindow getSlidingWindow() {
        return this.slidingWindow;
    }

    public boolean isSalientPointFoundInWindow() {
        return this.salientPointFoundInWindow;
    }

    public int getLastSalientPointIndex() {
        return this.lastSalientPointIndex;
    }

    public void analyse() {
        this.lastIndex = this.slidingWindow.getLastElementIndex();
        this.lastSalientPointIndex = this.lastIndex;
        this.salientPointFoundInWindow = false;
        doAnalysis();
    }

    private void doAnalysis() {
        updatePoint1();
        int endIndex = this.lastIndex;
        while (endIndex > 2) {
            analyseRange(endIndex);
            if (this.salientPointFoundInRange) {
                endIndex--;
            } else {
                this.lastSalientPointIndex--;
                return;
            }
        }
    }

    private void updatePoint1() {
        this.v13.setPoint1(this.slidingWindow.getElementAt(0));
    }

    private void updatePoint3(int index) {
        this.v13.setPoint3(this.slidingWindow.getElementAt(index));
    }

    private void analyseRange(int endIndex) {
        this.salientPointFoundInRange = false;
        updatePoint3(endIndex);
        doAnalyseRange(endIndex - 1);
    }

    private void doAnalyseRange(int point2EndIndex) {
        for (int i = 1; i <= point2EndIndex; i++) {
            this.v13.setPoint2(this.slidingWindow.getElementAt(i));
            analyseHeight(point2EndIndex);
        }
    }

    private void analyseHeight(int point2EndIndex) {
        int vectorsHeightThreshold = this.vectoSettings.getVectorsHeightThreshold();
        if (this.v13.getHeightP2ToV13(vectorsHeightThreshold) > vectorsHeightThreshold) {
            this.lastSalientPointIndex = point2EndIndex;
            this.salientPointFoundInRange = true;
            this.salientPointFoundInWindow = true;
        }
    }

    public void setWindowSize(int windowSize) {
        this.slidingWindow.setWindowSize(windowSize);
        if (this.onWindowSizeListener != null) {
            this.onWindowSizeListener.onWindowSizeChanged(windowSize);
        }
    }
}
