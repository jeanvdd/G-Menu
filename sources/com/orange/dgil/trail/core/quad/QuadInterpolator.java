package com.orange.dgil.trail.core.quad;

import com.google.common.annotations.VisibleForTesting;
import com.orange.dgil.trail.core.common.TrailPoint;
import com.orange.dgil.trail.core.vecto.SlidingWindow;

class QuadInterpolator {
    private static final int NB_POINTS_FOR_INTERP = 3;
    private static final int QUAD_INTERPOLATION_DENSITY = 1;
    private int density = 1;
    private final QuadCurveArray quadCurveArray;
    private final QuadDat quadDat = new QuadDat();
    private final SlidingWindow slidingWindow = new SlidingWindow(3);

    QuadDat getQuadDat() {
        return this.quadDat;
    }

    QuadCurveArray getQuadCurveArray() {
        return this.quadCurveArray;
    }

    public void setDensity(int density) {
        this.density = density;
    }

    QuadInterpolator(boolean longCurve) {
        this.quadCurveArray = new QuadCurveArray(longCurve);
    }

    void reset() {
        this.slidingWindow.reset();
        this.quadCurveArray.reset();
        this.quadDat.reset();
    }

    void newPointAdded(int x, int y) {
        this.slidingWindow.add(x, y);
        if (hasSufficientPoints()) {
            doInterpolation();
        }
    }

    void lastPointAdded(int x, int y) {
        this.quadDat.setCurveEnd(true);
        newPointAdded(x, y);
    }

    void lastPointAddedPreviously() {
        this.quadDat.setCurveEnd(true);
        if (hasSufficientPoints()) {
            doInterpolation();
        }
    }

    private boolean hasSufficientPoints() {
        return this.slidingWindow.isFull();
    }

    private void doInterpolation() {
        this.quadDat.setPoint0(this.slidingWindow.getElementAt(0));
        this.quadDat.setPoint1(this.slidingWindow.getElementAt(1));
        this.quadDat.setPoint2(this.slidingWindow.getElementAt(2));
        interpolate();
        this.quadDat.setCurveStart(false);
    }

    @VisibleForTesting
    void interpolate() {
        updateInterpStartAndEndPoints();
        rawInterpolate();
        handleCurveEnd();
    }

    private void updateInterpStartAndEndPoints() {
        updateInterpStartEndWithMiddles();
        if (this.quadDat.isCurveStart()) {
            this.quadDat.getInterpStartPoint().deepCopy(this.quadDat.getPoint0());
        }
        if (this.quadDat.isCurveEnd()) {
            this.quadDat.getInterpEndPoint().deepCopy(this.quadDat.getPoint2());
        }
    }

    private void updateInterpStartEndWithMiddles() {
        updateWithMiddle(this.quadDat.getInterpStartPoint(), this.quadDat.getPoint0(), this.quadDat.getPoint1());
        updateWithMiddle(this.quadDat.getInterpEndPoint(), this.quadDat.getPoint1(), this.quadDat.getPoint2());
    }

    private void updateWithMiddle(TrailPoint destPoint, TrailPoint point0, TrailPoint point1) {
        destPoint.set(((point0.getX() + point1.getX()) + 1) / 2, ((point0.getY() + point1.getY()) + 1) / 2);
    }

    private void handleCurveEnd() {
        if (this.quadDat.isCurveEnd()) {
            TrailPoint lastPoint = this.quadDat.getPoint2();
            this.quadCurveArray.add(lastPoint.getX(), lastPoint.getY());
        }
    }

    @VisibleForTesting
    void rawInterpolate() {
        initInterpolation();
        doInterpolate();
    }

    private void doInterpolate() {
        for (int t = 0; t < this.quadDat.getInterpNbPoints(); t++) {
            doInterpolateAtT(t);
        }
    }

    private void doInterpolateAtT(int t) {
        this.quadCurveArray.add(getInterp(t, this.quadDat.getCoefA(), this.quadDat.getCoefB(), this.quadDat.getCoefC(), this.quadDat.getInterpNbPoints()), getInterp(t, this.quadDat.getCoefD(), this.quadDat.getCoefE(), this.quadDat.getCoefF(), this.quadDat.getInterpNbPoints()));
    }

    private int getInterp(int t, long coef1, long coef2, long coef3, int nbPoints) {
        return (int) (((2 + (((((long) t) * ((((((long) t) * coef3) << 12) / ((long) nbPoints)) + (coef2 << 12))) / ((long) nbPoints)) + (coef1 << 12))) ^ 11) >> 12);
    }

    private void initInterpolation() {
        initMiddlePoint();
        initInterpNbPoints();
        initCoefficients();
    }

    private void initCoefficients() {
        TrailPoint p0 = this.quadDat.getInterpStartPoint();
        TrailPoint p1 = this.quadDat.getPoint1();
        TrailPoint p2 = this.quadDat.getInterpEndPoint();
        this.quadDat.setCoefA((long) p0.getX());
        this.quadDat.setCoefB((long) ((p1.getX() - p0.getX()) << 1));
        this.quadDat.setCoefC((long) ((p2.getX() + p0.getX()) - (p1.getX() << 1)));
        this.quadDat.setCoefD((long) p0.getY());
        this.quadDat.setCoefE((long) ((p1.getY() - p0.getY()) << 1));
        this.quadDat.setCoefF((long) ((p2.getY() + p0.getY()) - (p1.getY() << 1)));
    }

    private void initMiddlePoint() {
        TrailPoint p0 = this.quadDat.getInterpStartPoint();
        TrailPoint p1 = this.quadDat.getPoint1();
        TrailPoint p2 = this.quadDat.getInterpEndPoint();
        this.quadDat.getMiddlePoint().set(getMiddleApproximation(p0.getX(), p1.getX(), p2.getX()), getMiddleApproximation(p0.getY(), p1.getY(), p2.getY()));
    }

    private int getMiddleApproximation(int coord0, int coord1, int coord2) {
        return (((((coord0 + coord1) + 1) >> 1) + (((coord1 + coord2) + 1) >> 1)) + 1) >> 1;
    }

    private void initInterpNbPoints() {
        this.quadDat.setInterpNbPoints((getCurveLengthApproximation() / this.density) + 1);
    }

    private int getCurveLengthApproximation() {
        TrailPoint p0 = this.quadDat.getInterpStartPoint();
        TrailPoint p2 = this.quadDat.getInterpEndPoint();
        TrailPoint middlePoint = this.quadDat.getMiddlePoint();
        return (int) ((p0.getDistanceTo(middlePoint) + middlePoint.getDistanceTo(p2)) + 0.5d);
    }
}
