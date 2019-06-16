package com.orange.dgil.trail.android.animation;

import android.view.View;

public interface IAnimDrawer {
    void animationFinished();

    View getView();

    void invalidatePath();
}
