package com.orange.dgil.trail.android.drawingtool;

import android.os.Build.VERSION;
import com.orange.dgil.trail.android.AndroidMetrics;
import com.orange.dgil.trail.android.DefaultDrawerConf;
import com.orange.dgil.trail.android.drawingtool.quillpen.QuillParameters;

public class TrailOptions {
    private int color = DefaultDrawerConf.TRAIL_COLOR;
    private DrawingTools drawingTools;
    private AndroidMetrics metrics;
    private QuillParameters quillParameters;
    private int shadowColor;
    private boolean shadowEnabled;
    private int shadowOffsetPixels;
    private int widthPixels;

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return this.color;
    }

    public void setShadowColor(int shadowColor) {
        this.shadowColor = shadowColor;
    }

    public int getShadowColor() {
        return this.shadowColor;
    }

    public QuillParameters getQuillParameters() {
        return this.quillParameters;
    }

    public boolean isShadowEnabled() {
        return this.shadowEnabled;
    }

    public int getShadowOffsetPixels() {
        return this.shadowOffsetPixels;
    }

    public int getWidthPixels() {
        return this.widthPixels;
    }

    public TrailOptions(AndroidMetrics metrics, DrawingTools drawingTools) {
        init(metrics, drawingTools);
    }

    private void init(AndroidMetrics metrics, DrawingTools drawingTools) {
        this.metrics = metrics;
        this.drawingTools = drawingTools;
        this.quillParameters = new QuillParameters(metrics);
        setTrailWidthMicrometers(DefaultDrawerConf.TRAIL_WIDTH_UM);
    }

    public void setTrailWidthMicrometers(int micrometers) {
        this.widthPixels = this.metrics.micrometersToPixels(micrometers);
        this.shadowOffsetPixels = (int) (((float) this.widthPixels) * 0.33f);
    }

    public boolean isShadowAvailable() {
        return VERSION.SDK_INT >= 14;
    }

    public void setShadowEnabled(boolean shadowEnabled) {
        boolean z = shadowEnabled && isShadowAvailable();
        this.shadowEnabled = z;
    }

    public void selectMarkerPen() {
        this.drawingTools.selectMarkerPen();
    }

    public void selectQuillPen() {
        this.drawingTools.selectQuillPen();
    }
}
