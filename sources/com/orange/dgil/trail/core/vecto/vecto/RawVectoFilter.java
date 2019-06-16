package com.orange.dgil.trail.core.vecto.vecto;

import com.orange.dgil.trail.core.common.TrailPoint;
import com.orange.dgil.trail.core.vecto.SlidingWindow;

public class RawVectoFilter implements OnWindowSizeListener {
    private boolean firstPoint = true;
    private int nbPointsForListener;
    private TrailPoint[] pointsForListener = new TrailPoint[this.slidingWindow.getWindowSize()];
    private final SlidingWindow slidingWindow = this.windowAnalysis.getSlidingWindow();
    private final VectoFilterListener vectoFilterListener;
    private final WindowAnalysis windowAnalysis = new WindowAnalysis(this);

    public RawVectoFilter(VectoFilterListener vectoFilterListener) {
        this.vectoFilterListener = vectoFilterListener;
        allocatePoints();
    }

    private void allocatePoints() {
        for (int i = 0; i < this.pointsForListener.length; i++) {
            this.pointsForListener[i] = new TrailPoint();
        }
    }

    public void reset() {
        this.firstPoint = true;
        this.nbPointsForListener = 0;
        this.slidingWindow.reset();
    }

    public void addPoint(TrailPoint point) {
        doAddPoint(point);
        notifyListenerForNewPoints(false);
    }

    public void epilogue() {
        addRemainingSalientPointsInWindow();
        addRawReleasePointToVecto();
        notifyListenerForNewPoints(true);
    }

    private void addRemainingSalientPointsInWindow() {
        boolean salientPointFound = true;
        while (isAnalysisPossible() && salientPointFound) {
            this.windowAnalysis.analyse();
            salientPointFound = this.windowAnalysis.isSalientPointFoundInWindow();
            if (salientPointFound) {
                addSalientPoint();
            }
        }
    }

    private boolean isAnalysisPossible() {
        return this.slidingWindow.getAddedElementsNumber() >= 3;
    }

    private void addRawReleasePointToVecto() {
        addPointToVecto(this.slidingWindow.getLastElement());
    }

    private void doAddPoint(TrailPoint p) {
        this.slidingWindow.add(p);
        if (this.firstPoint) {
            this.firstPoint = false;
            addPointToVecto(p);
        } else if (this.slidingWindow.isFull()) {
            analyseWindow();
        }
    }

    private void analyseWindow() {
        this.windowAnalysis.analyse();
        addSalientPoint();
    }

    private void addSalientPoint() {
        int salientPointIndex = this.windowAnalysis.getLastSalientPointIndex();
        addPointToVecto(this.slidingWindow.getElementAt(salientPointIndex));
        this.slidingWindow.removeElementsAtLeftOf(salientPointIndex);
    }

    private void addPointToVecto(TrailPoint p) {
        this.pointsForListener[this.nbPointsForListener].set(p.getX(), p.getY());
        this.nbPointsForListener++;
    }

    private void notifyListenerForNewPoints(boolean epilogue) {
        int i = 0;
        while (i < this.nbPointsForListener) {
            boolean lastPoint;
            if (epilogue && i == this.nbPointsForListener - 1) {
                lastPoint = true;
            } else {
                lastPoint = false;
            }
            notifyListenerForNewPoint(this.pointsForListener[i], lastPoint);
            i++;
        }
        this.nbPointsForListener = 0;
    }

    private void notifyListenerForNewPoint(TrailPoint point, boolean lastPoint) {
        int x = point.getX();
        int y = point.getY();
        if (lastPoint) {
            this.vectoFilterListener.onLastVectoPointAvailable(x, y);
        } else {
            this.vectoFilterListener.onNewVectoPointAvailable(x, y);
        }
    }

    public VectoSettings getVectoSettings() {
        return this.windowAnalysis.getVectoSettings();
    }

    public void onWindowSizeChanged(int size) {
        this.pointsForListener = new TrailPoint[size];
        allocatePoints();
    }
}
