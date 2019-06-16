package com.orange.dgil;

import com.google.common.annotations.VisibleForTesting;
import com.orange.dgil.aperiodictrigger.AperiodicTrigger;
import com.orange.dgil.callback.CallbackTrigger;
import com.orange.dgil.callback.DgilCallbacks;
import com.orange.dgil.callback.DgilDefaultListener;
import com.orange.dgil.callback.DgilListener;
import com.orange.dgil.callback.FlickParameters;
import com.orange.dgil.callback.ListenerParams;
import com.orange.dgil.callback.SymbolParameters;
import com.orange.dgil.conf.Modes.Mode;
import com.orange.dgil.model.bean.Unpexbin;
import com.orange.dgil.model.dao.DgilReco;
import com.orange.dgil.model.dao.UnpexbinReader;
import com.orange.dgil.nativeinterface.DgilLibLoader;
import com.orange.dgil.nativeinterface.DgilNative;
import com.orange.dgil.nativeinterface.DgilParamTouchscreen;
import com.orange.tui.taplog.TapLog;
import java.io.IOException;

public abstract class Dgil extends DgilNative implements DgilCallbacks {
    public static final boolean LOG = false;
    private final CallbackTrigger callbackTrigger;
    private DgilListener dgilListener;
    private Mode dgilMode;
    private boolean forwardStartAtPress;
    private final DgilParamTouchscreen ts;

    public Mode getDgilMode() {
        return this.dgilMode;
    }

    public DgilParamTouchscreen getTs() {
        return this.ts;
    }

    public DgilListener getDgilListener() {
        return this.dgilListener;
    }

    public Dgil(DgilParamTouchscreen paramTouchScreen, AperiodicTrigger aperiodicTrigger) {
        super(paramTouchScreen, aperiodicTrigger);
        this.callbackTrigger = new CallbackTrigger(this);
        this.forwardStartAtPress = true;
        this.ts = paramTouchScreen;
        this.dgilMode = Mode.DYNAMIC_POINTING;
        if (DgilLibLoader.isLoaded()) {
            super.setMode(Mode.DYNAMIC_POINTING);
            setListener(new DgilDefaultListener(new ListenerParams(this)));
        }
    }

    public void setListener(DgilListener dgilListener) {
        if (dgilListener == null) {
            TapLog.e(this, new Object[]{"Tried to set a null dgil listener"});
        } else if (this.dgilListener != dgilListener) {
            doSetListener(dgilListener);
        }
    }

    @VisibleForTesting
    void doSetListener(DgilListener dgilListener) {
        this.callbackTrigger.setDgilCallBacks(dgilListener);
        if (this.dgilListener != null) {
            this.dgilListener.onListenerUnselected();
        }
        this.dgilListener = dgilListener;
        dgilListener.onListenerSelected();
    }

    public Dgil(DgilParamTouchscreen paramTouchScreen, AperiodicTrigger aperiodicTrigger, UnpexbinReader reader) throws IOException {
        this(paramTouchScreen, aperiodicTrigger);
        setUnpexbeanReader(reader);
    }

    protected boolean isSeqMoveDetected() {
        return this.callbackTrigger.isSeqMoveDetected();
    }

    public final void setUnpexbeanReader(UnpexbinReader reader) throws IOException {
        unloadSymbols();
        if (reader != null) {
            DgilReco.get().setUnpexbinReader(reader);
            for (Unpexbin unpexbin : DgilReco.get().getUnpexbins()) {
                loadUnpexbin(unpexbin);
            }
        }
    }

    public final void loadUnpexbin(Unpexbin unpexbin) {
        if (unpexbin.getData() != null && unpexbin.getData().length > 0 && !isSymbolLoaded(unpexbin.getSymbolName())) {
            addSymbolUnpexBin(unpexbin.getData());
            setMSHSymbolClass(unpexbin.getSymbolName(), unpexbin.getMshClass());
        }
    }

    public void setMode(Mode mode) {
        this.dgilMode = mode;
        super.setMode(mode);
    }

    public void pressDetected(int x, int y) {
        startForwardCheck(true);
    }

    public void shortPressDetected(int x, int y) {
        startForwardCheck(false);
    }

    public void longPressDetected(int x, int y) {
    }

    public void longLongPressDetected(int x, int y) {
    }

    public void releaseLongPressDetected(int x, int y) {
    }

    public void releaseLongLongPressDetected(int x, int y) {
    }

    public void tapDetected(int x, int y) {
        if (!inForward()) {
            pressFromDgil(x, y);
            releaseFromDgil(0, 0, 0);
        }
    }

    public void pendingDoubleTapDetected() {
    }

    public void doubleTapPressDetected() {
    }

    public void doubleTapDetected() {
    }

    public void longPressDoubleTapDetected() {
    }

    public void startMoveDetected() {
    }

    public void dragDetected() {
        startForwardCheck(false);
    }

    public void breakDragDetected() {
    }

    public void endDragDetected() {
    }

    public void fastStraightDetected() {
    }

    public void preSymbolicPatternDetected() {
    }

    public void symbolicPatternDetected() {
        setCancelMotion(true);
    }

    public void symbolicDetected(SymbolParameters symbolParameters) {
    }

    public void flickDetected(FlickParameters flickParameters) {
        setCancelMotion(false);
    }

    public void breakFlickDetected(FlickParameters flickParameters) {
    }

    public void breakSymbolicDetected(SymbolParameters symbolParameters) {
    }

    public void regularCurvedDetected() {
        forwardSuspend();
    }

    public void clockwiseRotationDetected(int rotationAngle) {
        setCancelMotion(true);
    }

    public void anticlockwiseRotationDetected(int rotationAngle) {
        setCancelMotion(true);
    }

    public void breakRotationDetected() {
        setCancelMotion(false);
        this.forwardStartAtPress = false;
    }

    public void endRotationDetected() {
    }

    public void seqMoveUpDetected() {
    }

    public void seqMoveDownDetected() {
    }

    public void seqMoveLeftDetected() {
    }

    public void seqMoveRightDetected() {
    }

    public void seqEarlyMoveUpDetected(int seqRelativePosPercent) {
    }

    public void seqEarlyMoveDownDetected(int seqRelativePosPercent) {
    }

    public void seqEarlyMoveLeftDetected(int seqRelativePosPercent) {
    }

    public void seqEarlyMoveRightDetected(int seqRelativePosPercent) {
    }

    public void seqCancelEarlyMove() {
    }

    public void endGestureDetected() {
    }

    private boolean isInPointingMode() {
        return this.dgilMode == Mode.POINTING || this.dgilMode == Mode.POINTING_ANDROID_OMNIDIRECTIONAL || this.dgilMode == Mode.POINTING_ANDROID_HORIZONTAL || this.dgilMode == Mode.POINTING_ANDROID_VERTICAL;
    }

    private void startForwardCheck(boolean onPress) {
        boolean pointing = isInPointingMode();
        boolean start = onPress ? pointing : !pointing;
        if (start) {
            forwardStart(this.forwardStartAtPress);
        }
    }

    public int pressToDgil(int x, int y, long t) {
        this.forwardStartAtPress = true;
        this.callbackTrigger.onPressEvent(x, y);
        return super.pressToDgil(x, y, t);
    }

    public int moveToDgil(int x, int y, long t, boolean bufferized) {
        return super.moveToDgil(x, y, t, bufferized);
    }

    public int releaseToDgil(int x, int y, long t) {
        moveToDgil(x, y, t, false);
        return super.releaseToDgil(t);
    }

    public void actionFromDgil(int rawMetaclass, int flagBeginClass, int rotationGestureEvent, int rotationAngle, int flickDirection, int flickDirectionAngularError, int gestureLength, int gestureDuration, int pixelPitch, int velocityEnd, int velocityEndX, int velocityEndY, int velocityEstimator, int velocityInst, int seqRelativePosPercent, int symbolSim1, int symbolSim2, int symbolSquareSideMaxlen, String symbol1, String symbol2, int[] protosSims) {
        this.callbackTrigger.getCallbackParameters().set(rawMetaclass, flagBeginClass, rotationGestureEvent, rotationAngle, flickDirection, flickDirectionAngularError, pixelPitch, velocityEnd, velocityEndX, velocityEndY, seqRelativePosPercent, symbolSim1, symbolSim2, symbolSquareSideMaxlen, symbol1, symbol2, protosSims);
        this.callbackTrigger.trigger();
    }

    public boolean symbolicDetectedInternal(String s1, int sim1) {
        return false;
    }
}
