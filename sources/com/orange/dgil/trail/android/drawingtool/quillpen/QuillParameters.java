package com.orange.dgil.trail.android.drawingtool.quillpen;

import com.orange.dgil.trail.android.AndroidMetrics;
import com.orange.dgil.trail.android.DefaultDrawerConf;

public class QuillParameters {
    private final AndroidMetrics metrics;
    private int quillAngleDeg;
    private int quillAngleXOffset;
    private int quillHeightPixels;
    private int quillWidthPixels;
    private int radius;

    public int getQuillWidthPixels() {
        return this.quillWidthPixels;
    }

    public int getQuillHeightPixels() {
        return this.quillHeightPixels;
    }

    public int getQuillAngleDeg() {
        return this.quillAngleDeg;
    }

    public int getQuillAngleXOffset() {
        return this.quillAngleXOffset;
    }

    public int getRadius() {
        return this.radius;
    }

    public QuillParameters(AndroidMetrics metrics) {
        this.metrics = metrics;
        init();
    }

    private void init() {
        setQuillWidthHeightMicrometers(DefaultDrawerConf.QUILL_WIDTH_UM, DefaultDrawerConf.QUILL_HEIGHT_UM);
        setQuillAngleDeg(45);
    }

    public void setQuillWidthHeightMicrometers(int widthMicrometers, int heightMicrometers) {
        this.quillWidthPixels = this.metrics.micrometersToPixels(widthMicrometers);
        this.quillHeightPixels = this.metrics.micrometersToPixels(heightMicrometers);
        this.radius = this.quillWidthPixels * 2;
        setQuillAngleDeg(this.quillAngleDeg);
    }

    public void setQuillAngleDeg(int angleDeg) {
        this.quillAngleDeg = angleDeg;
        this.quillAngleXOffset = this.quillHeightPixels;
    }
}
