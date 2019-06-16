package com.orange.dgil.trail.android.drawingtool.quillpen;

import com.orange.dgil.trail.android.AndroidMetrics;
import com.orange.dgil.trail.core.common.TrailPoint;
import com.orange.dgil.trail.core.quad.QuadCurve;
import com.orange.dgil.trail.core.quad.QuadCurveArray;
import com.orange.dgil.trail.core.vecto.vecto.VectoSettings;

class QuadCurveTrail {
    private static final int VECTO_HEIGHT_THRESHOLD_UM = 1000;
    private static final int VECTO_WINDOW_SIZE = 6;
    private final QuadCurve quadCurve;

    QuadCurveTrail(boolean vectoEnabled, AndroidMetrics metrics) {
        this.quadCurve = new QuadCurve(vectoEnabled);
        init(vectoEnabled, metrics);
    }

    private void init(boolean vectoEnabled, AndroidMetrics metrics) {
        if (vectoEnabled) {
            initQuadCurveVecto(metrics);
        }
        initQuadCurveDensity(metrics);
    }

    private void initQuadCurveDensity(AndroidMetrics metrics) {
        this.quadCurve.setDensity((int) (metrics.getDensity() + 0.5f));
    }

    private void initQuadCurveVecto(AndroidMetrics metrics) {
        int heightPx = metrics.micrometersToPixels(1000);
        VectoSettings vectoSettings = this.quadCurve.getVectoSettings();
        vectoSettings.setWindowSize(6);
        vectoSettings.setVectorsHeightThreshold(heightPx);
    }

    void reset() {
        this.quadCurve.reset();
    }

    void touchDown(int x, int y) {
        this.quadCurve.reset();
        addPoint(x, y);
    }

    void touchMove(int x, int y) {
        addPoint(x, y);
    }

    void touchUp() {
        this.quadCurve.epilogue();
    }

    private void addPoint(int x, int y) {
        this.quadCurve.addPoint(x, y);
    }

    QuadCurveArray getQuadCurveArray() {
        return this.quadCurve.getQuadCurveArray();
    }

    VectoSettings getVectoSettings() {
        return this.quadCurve.getVectoSettings();
    }

    TrailPoint getLastVectoPoint() {
        return this.quadCurve.getLastVectoPoint();
    }
}
