package com.orange.dgil.trail.android.drawingtool;

import android.graphics.Canvas;

public interface IDrawingTool {
    void draw(Canvas canvas);

    void forceRedrawForAnimation(boolean z);

    void invalidateAreaOnMove();

    void invalidatePath();

    void reset();

    void touchDown(int i, int i2);

    void touchMove(int i, int i2);

    void touchUp();

    void trimMemory();
}
