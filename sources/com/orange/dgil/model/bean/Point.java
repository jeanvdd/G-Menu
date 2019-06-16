package com.orange.dgil.model.bean;

import java.io.Serializable;

public class Point implements Serializable {
    private static final long serialVersionUID = 1;
    /* renamed from: x */
    private int f8x;
    /* renamed from: y */
    private int f9y;

    public Point(int x, int y) {
        this.f8x = x;
        this.f9y = y;
    }

    public int getX() {
        return this.f8x;
    }

    public void setX(int x) {
        this.f8x = x;
    }

    public int getY() {
        return this.f9y;
    }

    public void setY(int y) {
        this.f9y = y;
    }
}
