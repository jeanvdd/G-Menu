package com.orange.dgil.trail.core.common;

public class TrailRect {
    private int bottom;
    private int left;
    private int right;
    private int top;

    public int getLeft() {
        return this.left;
    }

    public int getTop() {
        return this.top;
    }

    public int getRight() {
        return this.right;
    }

    public int getBottom() {
        return this.bottom;
    }

    public void initAtPoint(int x, int y) {
        this.left = x;
        this.top = y;
        this.right = x;
        this.bottom = y;
    }

    public void union(int x, int y) {
        this.left = Math.min(this.left, x);
        this.right = Math.max(this.right, x);
        this.top = Math.min(this.top, y);
        this.bottom = Math.max(this.bottom, y);
    }
}
