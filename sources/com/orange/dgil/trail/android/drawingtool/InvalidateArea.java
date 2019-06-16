package com.orange.dgil.trail.android.drawingtool;

import android.graphics.Point;
import android.graphics.Rect;

public class InvalidateArea {
    private int radius;
    private final Rect rect = new Rect();

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Rect getRect() {
        return this.rect;
    }

    public int getRadius() {
        return this.radius;
    }

    public void setOrigin(int x, int y) {
        this.rect.set(x - this.radius, y - this.radius, this.radius + x, this.radius + y);
    }

    public void setOrigin(Point p) {
        setOrigin(p.x, p.y);
    }

    public void add(int x, int y) {
        this.rect.union(x - this.radius, y - this.radius, this.radius + x, this.radius + y);
    }

    public void add(Point p) {
        add(p.x, p.y);
    }

    public void setEmpty() {
        this.rect.setEmpty();
    }
}
