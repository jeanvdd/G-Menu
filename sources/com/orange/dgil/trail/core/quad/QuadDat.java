package com.orange.dgil.trail.core.quad;

import com.orange.dgil.trail.core.common.TrailPoint;

class QuadDat {
    private long coefA;
    private long coefB;
    private long coefC;
    private long coefD;
    private long coefE;
    private long coefF;
    private boolean curveEnd;
    private boolean curveStart;
    private final TrailPoint interpEndPoint = new TrailPoint();
    private int interpNbPoints;
    private final TrailPoint interpStartPoint = new TrailPoint();
    private final TrailPoint middlePoint = new TrailPoint();
    private TrailPoint point0;
    private TrailPoint point1;
    private TrailPoint point2;

    void setCoefA(long coefA) {
        this.coefA = coefA;
    }

    void setCoefB(long coefB) {
        this.coefB = coefB;
    }

    void setCoefC(long coefC) {
        this.coefC = coefC;
    }

    void setCoefD(long coefD) {
        this.coefD = coefD;
    }

    void setCoefE(long coefE) {
        this.coefE = coefE;
    }

    void setCoefF(long coefF) {
        this.coefF = coefF;
    }

    void setCurveEnd(boolean curveEnd) {
        this.curveEnd = curveEnd;
    }

    void setCurveStart(boolean curveStart) {
        this.curveStart = curveStart;
    }

    void setInterpNbPoints(int interpNbPoints) {
        this.interpNbPoints = interpNbPoints;
    }

    void setPoint0(TrailPoint point0) {
        this.point0 = point0;
    }

    void setPoint1(TrailPoint point1) {
        this.point1 = point1;
    }

    void setPoint2(TrailPoint point2) {
        this.point2 = point2;
    }

    QuadDat() {
    }

    TrailPoint getInterpStartPoint() {
        return this.interpStartPoint;
    }

    TrailPoint getInterpEndPoint() {
        return this.interpEndPoint;
    }

    TrailPoint getMiddlePoint() {
        return this.middlePoint;
    }

    long getCoefA() {
        return this.coefA;
    }

    long getCoefB() {
        return this.coefB;
    }

    long getCoefC() {
        return this.coefC;
    }

    long getCoefD() {
        return this.coefD;
    }

    long getCoefE() {
        return this.coefE;
    }

    long getCoefF() {
        return this.coefF;
    }

    int getInterpNbPoints() {
        return this.interpNbPoints;
    }

    boolean isCurveStart() {
        return this.curveStart;
    }

    boolean isCurveEnd() {
        return this.curveEnd;
    }

    TrailPoint getPoint0() {
        return this.point0;
    }

    TrailPoint getPoint1() {
        return this.point1;
    }

    TrailPoint getPoint2() {
        return this.point2;
    }

    void reset() {
        this.curveStart = true;
        this.curveEnd = false;
    }
}
