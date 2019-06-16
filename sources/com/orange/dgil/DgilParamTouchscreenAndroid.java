package com.orange.dgil;

import android.content.Context;
import com.google.common.annotations.VisibleForTesting;
import com.orange.dgil.nativeinterface.DgilParamTouchscreen;
import com.orange.dgil.trail.android.AndroidMetrics;

public class DgilParamTouchscreenAndroid extends DgilParamTouchscreen {
    public DgilParamTouchscreenAndroid(Context ctx) {
        initScreenProperties(AndroidMetrics.get(ctx));
    }

    @VisibleForTesting
    void initScreenProperties(AndroidMetrics metrics) {
        setPixelPitchUm(metrics.getMicrometersPerPixel());
        setScreenResolutionX(metrics.getWidthPixels());
        setScreenResolutionY(metrics.getHeightPixels());
        setScreenSizeXMm(metrics.pixelsToMillimeters(metrics.getWidthPixels()));
        setScreenSizeYMm(metrics.pixelsToMillimeters(metrics.getHeightPixels()));
    }
}
