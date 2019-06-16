package com.orange.dgil.trail.android.drawingtool;

import android.view.View;
import com.orange.dgil.trail.android.AndroidMetrics;
import com.orange.dgil.trail.android.animation.AnimManager;

public class DrawingToolsContext {
    private final AndroidMetrics androidMetrics;
    private final AnimManager animManager;
    private final TrailOptions trailOptions;
    private final View view;

    public DrawingToolsContext(View view, AnimManager animManager, AndroidMetrics androidMetrics, TrailOptions trailOptions) {
        this.view = view;
        this.animManager = animManager;
        this.androidMetrics = androidMetrics;
        this.trailOptions = trailOptions;
    }

    public View getView() {
        return this.view;
    }

    public AnimManager getAnimManager() {
        return this.animManager;
    }

    public AndroidMetrics getAndroidMetrics() {
        return this.androidMetrics;
    }

    public TrailOptions getTrailOptions() {
        return this.trailOptions;
    }
}
