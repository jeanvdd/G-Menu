package com.orange.dgil.callback;

import com.orange.dgil.conf.Metaclasses.Metaclass;
import com.orange.dgil.conf.Settings;

public class CallbackParameters {
    private final FlickParameters flickParameters = new FlickParameters();
    private boolean isSequentialMetaclass;
    private Metaclass metaclass;
    private int pixelPitch;
    private int rotationAngle;
    private int seqRelativePosPercent;
    private boolean shouldTriggerCallback;
    private final SymbolParameters symbolParameters;

    public SymbolParameters getSymbolParameters() {
        return this.symbolParameters;
    }

    public FlickParameters getFlickParameters() {
        return this.flickParameters;
    }

    public Metaclass getMetaclass() {
        return this.metaclass;
    }

    public boolean isSequentialMetaclass() {
        return this.isSequentialMetaclass;
    }

    public boolean isShouldTriggerCallback() {
        return this.shouldTriggerCallback;
    }

    public int getRotationAngle() {
        return this.rotationAngle;
    }

    public int getPixelPitch() {
        return this.pixelPitch;
    }

    public int getSeqRelativePosPercent() {
        return this.seqRelativePosPercent;
    }

    public CallbackParameters(Settings dgilSettings) {
        this.symbolParameters = new SymbolParameters(dgilSettings);
    }

    public void set(int rawMetaclass, int flagBeginClass, int rotationGestureEvent, int rotationAngle, int flickDirection, int flickDirectionAngularError, int pixelPitch, int velocityEnd, int velocityEndX, int velocityEndY, int seqRelativePosPercent, int symbolSim1, int symbolSim2, int symbolSquareSideMaxlen, String symbol1, String symbol2, int[] protosSims) {
        this.rotationAngle = rotationAngle;
        this.pixelPitch = pixelPitch;
        this.seqRelativePosPercent = seqRelativePosPercent;
        update(rawMetaclass, flagBeginClass, rotationGestureEvent);
        updateSymbolParameters(symbol1, symbol2, symbolSim1, symbolSim2, symbolSquareSideMaxlen, protosSims);
        updateFlickParameters(velocityEnd, velocityEndX, velocityEndY, pixelPitch, flickDirection, flickDirectionAngularError);
    }

    private void update(int rawMetaclass, int flagBeginClass, int rotationGestureEvent) {
        this.metaclass = Metaclass.values()[rawMetaclass];
        this.isSequentialMetaclass = CallbackSequentialTrigger.isSeqMetaclass(this.metaclass);
        boolean z = (flagBeginClass == 0 && rotationGestureEvent == 0 && !this.isSequentialMetaclass) ? false : true;
        this.shouldTriggerCallback = z;
    }

    private void updateSymbolParameters(String symbol1, String symbol2, int symbolSim1, int symbolSim2, int symbolSquareSideMaxlen, int[] protosSims) {
        this.symbolParameters.set(symbol1, symbol2, symbolSim1, symbolSim2, symbolSquareSideMaxlen, protosSims);
    }

    private void updateFlickParameters(int velocityEnd, int velocityEndX, int velocityEndY, int pixelPitch, int flickDirection, int flickDirectionAngularError) {
        this.flickParameters.set(velocityEnd, velocityEndX, velocityEndY, pixelPitch, flickDirection, flickDirectionAngularError);
    }
}
