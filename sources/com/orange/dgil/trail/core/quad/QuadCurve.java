package com.orange.dgil.trail.core.quad;

import com.orange.dgil.trail.core.common.TrailPoint;
import com.orange.dgil.trail.core.vecto.vecto.VectoFilter;
import com.orange.dgil.trail.core.vecto.vecto.VectoFilterListener;
import com.orange.dgil.trail.core.vecto.vecto.VectoSettings;

public class QuadCurve implements VectoFilterListener {
    private final TrailPoint lastVectoPoint = new TrailPoint();
    private QuadInterpolator quadInterpolator;
    private final boolean vectoEnabled;
    private VectoFilter vectoFilter = new VectoFilter(this);

    public TrailPoint getLastVectoPoint() {
        return this.lastVectoPoint;
    }

    public QuadCurve(boolean vectoEnabled) {
        this.vectoEnabled = vectoEnabled;
        this.vectoFilter = vectoEnabled ? new VectoFilter(this) : null;
        this.quadInterpolator = new QuadInterpolator(vectoEnabled);
    }

    public void reset() {
        resetPointsContainer();
        this.quadInterpolator.reset();
    }

    private void resetPointsContainer() {
        if (this.vectoEnabled) {
            this.vectoFilter.reset();
        }
    }

    public void addPoint(int x, int y) {
        if (this.vectoEnabled) {
            addPointToVecto(x, y);
        } else {
            addRawPoint(x, y);
        }
    }

    private void addPointToVecto(int x, int y) {
        this.vectoFilter.addPoint(x, y);
    }

    private void addRawPoint(int x, int y) {
        this.quadInterpolator.newPointAdded(x, y);
    }

    public void epilogue() {
        if (this.vectoEnabled) {
            doVectoEpilogue();
        } else {
            this.quadInterpolator.lastPointAddedPreviously();
        }
    }

    private void doVectoEpilogue() {
        this.vectoFilter.epilogue();
    }

    public void onNewVectoPointAvailable(int x, int y) {
        this.lastVectoPoint.set(x, y);
        this.quadInterpolator.newPointAdded(x, y);
    }

    public void onLastVectoPointAvailable(int x, int y) {
        this.quadInterpolator.lastPointAdded(x, y);
    }

    public QuadCurveArray getQuadCurveArray() {
        return this.quadInterpolator.getQuadCurveArray();
    }

    public VectoSettings getVectoSettings() {
        if (this.vectoEnabled) {
            return this.vectoFilter.getVectoSettings();
        }
        throw new QuadCurveException("Vecto not enabled");
    }

    public void setDensity(int density) {
        this.quadInterpolator.setDensity(density);
    }
}
