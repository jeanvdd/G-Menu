package com.orange.dgil.trail.core.common;

public class Vector {
    private int norm12;
    private int norm13;
    private int norm23;
    private final TrailPoint point1 = new TrailPoint();
    private final TrailPoint point2 = new TrailPoint();
    private final TrailPoint point3 = new TrailPoint();

    public TrailPoint getPoint1() {
        return this.point1;
    }

    public TrailPoint getPoint2() {
        return this.point2;
    }

    public TrailPoint getPoint3() {
        return this.point3;
    }

    public void setPoint1(TrailPoint point) {
        this.point1.set(point.getX(), point.getY());
    }

    public void setPoint2(TrailPoint point) {
        this.point2.set(point.getX(), point.getY());
    }

    public void setPoint3(TrailPoint point) {
        this.point3.set(point.getX(), point.getY());
    }

    public int getHeightP3ToV12() {
        return (int) (((double) Math.abs(((float) crossProduct()) / ((float) getNorm(this.point1, this.point2)))) + 0.5d);
    }

    public int getHeightP2ToV13(int normMin) {
        updateNorms();
        if (areP1P2orP2P3TooClose(normMin)) {
            return 0;
        }
        if (areP1P3TooClose(normMin)) {
            return getRawHeightEstimation();
        }
        return doGetHeightP2ToV13();
    }

    private void updateNorms() {
        this.norm12 = getNorm(this.point1, this.point2);
        this.norm13 = getNorm(this.point1, this.point3);
        this.norm23 = getNorm(this.point2, this.point3);
    }

    private boolean areP1P2orP2P3TooClose(int normMin) {
        return this.norm12 < normMin || this.norm23 < normMin;
    }

    private boolean areP1P3TooClose(int normMin) {
        return this.norm13 < normMin;
    }

    private int getRawHeightEstimation() {
        return (this.norm12 + this.norm23) / 2;
    }

    private int doGetHeightP2ToV13() {
        return (int) ((((float) Math.abs(crossProduct())) / ((float) this.norm13)) + 0.5f);
    }

    private int crossProduct() {
        return (getDx(this.point1, this.point2) * getDy(this.point1, this.point3)) - (getDy(this.point1, this.point2) * getDx(this.point1, this.point3));
    }

    private int getNorm(TrailPoint point1, TrailPoint point2) {
        return (int) Math.sqrt(Math.pow((double) getDx(point1, point2), 2.0d) + Math.pow((double) getDy(point1, point2), 2.0d));
    }

    private int getDx(TrailPoint point1, TrailPoint point2) {
        return point2.getX() - point1.getX();
    }

    private int getDy(TrailPoint point1, TrailPoint point2) {
        return point2.getY() - point1.getY();
    }
}
