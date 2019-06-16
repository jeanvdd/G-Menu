package com.orange.dgil.trail.core.common;

public class TrailPoint {
    /* renamed from: x */
    private int f24x = -1;
    /* renamed from: y */
    private int f25y = -1;

    public int getX() {
        return this.f24x;
    }

    public int getY() {
        return this.f25y;
    }

    public TrailPoint(int x, int y) {
        set(x, y);
    }

    public void set(int x, int y) {
        this.f24x = x;
        this.f25y = y;
    }

    public boolean isSameAs(TrailPoint point) {
        return this.f24x == point.f24x && this.f25y == point.f25y;
    }

    public double getDistanceTo(TrailPoint point) {
        int dx = this.f24x - point.getX();
        int dy = this.f25y - point.getY();
        return Math.sqrt((double) ((dx * dx) + (dy * dy)));
    }

    public void deepCopy(TrailPoint point) {
        this.f24x = point.f24x;
        this.f25y = point.f25y;
    }
}
