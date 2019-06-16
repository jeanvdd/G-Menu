package com.orange.dgil.trail.core.vecto.vecto;

import com.orange.dgil.trail.core.common.TrailPoint;
import com.orange.dgil.trail.core.vecto.linearwindowfilter.LinearWindowFilter;
import com.orange.dgil.trail.core.vecto.linearwindowfilter.LinearWindowFilterListener;

public class VectoFilter implements LinearWindowFilterListener, VectoFilterListener {
    private final LinearWindowFilter linearWindowFilter = new LinearWindowFilter(this);
    private final RawVectoFilter rawVectoFilter = new RawVectoFilter(this);
    private final TrailPoint trailPoint = new TrailPoint();
    private final VectoFilterListener vectoFilterListener;

    public VectoFilter(VectoFilterListener vectoFilterListener) {
        this.vectoFilterListener = vectoFilterListener;
    }

    public void reset() {
        this.linearWindowFilter.reset();
        this.rawVectoFilter.reset();
    }

    public void addPoint(int x, int y) {
        this.trailPoint.set(x, y);
        this.linearWindowFilter.addPoint(this.trailPoint);
    }

    public void epilogue() {
        this.rawVectoFilter.addPoint(this.linearWindowFilter.getLastPoint());
        this.rawVectoFilter.epilogue();
    }

    public void onNewFilteredPointAvailable(int x, int y) {
        this.trailPoint.set(x, y);
        this.rawVectoFilter.addPoint(this.trailPoint);
    }

    public void onNewVectoPointAvailable(int x, int y) {
        this.vectoFilterListener.onNewVectoPointAvailable(x, y);
    }

    public void onLastVectoPointAvailable(int x, int y) {
        this.vectoFilterListener.onLastVectoPointAvailable(x, y);
    }

    public VectoSettings getVectoSettings() {
        return this.rawVectoFilter.getVectoSettings();
    }
}
