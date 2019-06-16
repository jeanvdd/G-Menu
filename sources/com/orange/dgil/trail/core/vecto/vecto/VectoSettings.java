package com.orange.dgil.trail.core.vecto.vecto;

public class VectoSettings {
    private static final int DEFAULT_VECTO_ERROR_HEIGHT_PIXELS = 1;
    private int vectorsHeightThreshold = 1;
    private final WindowAnalysis windowAnalysis;

    public VectoSettings(WindowAnalysis windowAnalysis) {
        this.windowAnalysis = windowAnalysis;
    }

    public int getVectorsHeightThreshold() {
        return this.vectorsHeightThreshold;
    }

    public void setVectorsHeightThreshold(int vectorsHeightThreshold) {
        this.vectorsHeightThreshold = vectorsHeightThreshold;
    }

    public void setWindowSize(int windowSize) {
        this.windowAnalysis.setWindowSize(windowSize);
    }
}
