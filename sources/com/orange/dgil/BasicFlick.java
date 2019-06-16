package com.orange.dgil;

import com.google.common.annotations.VisibleForTesting;

public class BasicFlick {
    public static final int FLICK_DIRECTION_E = 2;
    public static final int FLICK_DIRECTION_N = 4;
    public static final int FLICK_DIRECTION_NE = 3;
    public static final int FLICK_DIRECTION_NW = 5;
    public static final int FLICK_DIRECTION_S = 8;
    public static final int FLICK_DIRECTION_SE = 9;
    public static final int FLICK_DIRECTION_SW = 7;
    public static final int FLICK_DIRECTION_W = 6;

    @VisibleForTesting
    int basicFlickDirection(int downX, int downY, int x, int y) {
        int dx = x - downX;
        int dy = y - downY;
        return Math.abs(dx) > Math.abs(dy) ? dx > 0 ? 2 : 6 : dy > 0 ? 8 : 4;
    }

    int basicFlickDirection(ToDgil toDgil) {
        return basicFlickDirection(toDgil.getDownX(), toDgil.getDownY(), toDgil.getLastX(), toDgil.getLastY());
    }
}
