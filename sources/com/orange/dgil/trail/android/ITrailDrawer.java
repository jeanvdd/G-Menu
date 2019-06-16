package com.orange.dgil.trail.android;

import android.graphics.Canvas;
import com.orange.dgil.trail.android.animation.AnimParameters;
import com.orange.dgil.trail.android.animation.IAnimListener;
import com.orange.dgil.trail.android.drawingtool.TrailOptions;

public interface ITrailDrawer {
    void animate();

    void animateAlpha(int i);

    void animateToColor(int i);

    void clear();

    void draw(Canvas canvas);

    AnimParameters getAnimationParameters();

    TrailOptions getTrailOptions();

    void hide();

    void setAnimationListener(IAnimListener iAnimListener);

    void setMultistrokeEnabled(boolean z);

    void show();

    void show(int i);

    void showAndRedrawPath();

    void showAndRedrawPath(int i);

    void touchCancel();

    void touchDown(int i, int i2);

    void touchMove(int i, int i2);

    void touchUp();

    void trimMemory();
}
