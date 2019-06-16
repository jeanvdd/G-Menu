package com.orange.dgil.nativeinterface;

import com.orange.dgil.aperiodictrigger.AperiodicTrigger;
import com.orange.dgil.conf.IFieldGetterSetter;
import com.orange.dgil.conf.MSHMetaclasses.MSHMetaclass;
import com.orange.dgil.conf.MSHSymbolClasses.MSHSymbolClass;
import com.orange.dgil.conf.Modes.Mode;
import com.orange.dgil.conf.Settings;
import com.orange.dgil.model.bean.Model;
import com.orange.tui.taplog.TapLog;

public abstract class DgilNative implements IFieldGetterSetter {
    private final JavaLibVersion javaLibVersion = new JavaLibVersion();
    private AperiodicTrigger mAperiodicTrigger;
    private boolean mCancelMotion;
    private boolean mDisable;
    private boolean mInForward;
    private DgilMSHListener mMSHListener;
    private final Settings settings = new Settings(this);

    private native short[] getModelVectTrace(String str, int i, int i2);

    private native int getModelVectTraceStrokesNb(String str, int i);

    private native int getNativeMode();

    private native void initMSH(boolean z, boolean z2, boolean z3);

    private native String mshAdaptGetAlt(int i);

    private native int mshAdaptGetAltsNb();

    private native void nativeConstructor(AperiodicTrigger aperiodicTrigger, int i, int i2, int i3, int i4, int i5, int i6);

    private native void nativeForwardStart(boolean z);

    private native int nativeMoveToDgil(int i, int i2, long j, boolean z);

    private native int nativePressToDgil(int i, int i2, long j);

    private native int nativeReleaseToDgil(long j);

    private native void resetDgil();

    private native void setMSHSymbolClass(String str, int i);

    private native void setMode(int i);

    public abstract void actionFromDgil(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, String str, String str2, int[] iArr);

    public native void addModelToSymbol(String str, int[][] iArr, int i);

    public native void addSymbolUnpexBin(byte[] bArr);

    public native void createSymbol(String str);

    public native int distToProto(int i);

    public native void enableSymbolicMultistrokeMem();

    public native void forwardStop();

    protected native void forwardSuspend();

    public native void freezeSeqMoveDownTemp();

    public native void freezeSeqMoveLeftTemp();

    public native void freezeSeqMoveRightTemp();

    public native void freezeSeqMoveUpTemp();

    public native boolean getNativeBoolean(int i);

    public native float getNativeFloat(int i);

    public native int getNativeInteger(int i);

    public native String getNativeString(int i);

    public final native int getNativeVersion();

    public native int[] getRobustSegModel(int i);

    public native int[] getRobustSegProto(int i);

    public native String getRobustSegSymbol(int i);

    public native int getRobustSegSymbolNb();

    public native int[] getSalientPoints();

    public native short[] getSmoothedStroke(int i, short[] sArr);

    public native String getSymbol(int i);

    public native int getSymbolModelsNb(String str);

    public native byte[] getSymbolUnpexBin(String str);

    public native boolean getSymbolicPatternFlag();

    public native int getSymbolsNb();

    public native boolean isSymbolLoaded(String str);

    public abstract void moveFromDgil(int i, int i2);

    public native String mshAdaptUpdateCurrAlt(boolean z);

    public native void mshAdaptValidateAlt(String str);

    public native byte[] mshGetAdaptMu();

    public native void mshSetAdaptMu(byte[] bArr);

    public native void mshSetDynamicMode(boolean z);

    public native void mshStrokeTimeout();

    public native void nativeDestructor();

    public abstract void pressFromDgil(int i, int i2);

    public abstract void releaseFromDgil(int i, int i2, int i3);

    public native void removeModelFromSymbol(String str, int i);

    public native void removeSymbol(String str);

    public native void resetSeqModeAbscissa();

    public native void resetSeqModeOrdinate();

    public native void resetSymbolicMultistrokeMem();

    protected native void setHardwareParamTS(int i, int i2, int i3, int i4, int i5, int i6);

    protected native void setMultipleLearningsIndex(int i);

    public native void setNativeBoolean(int i, boolean z);

    public native void setNativeFloat(int i, float f);

    public native void setNativeInteger(int i, int i2);

    public native void setNativeString(int i, String str);

    public native void unloadSymbols();

    protected native void updateDgilJavaObject();

    public Settings getSettings() {
        return this.settings;
    }

    public DgilNative(DgilParamTouchscreen paramTouchScreen, AperiodicTrigger aperiodicTrigger) {
        new DgilLibLoader().loadNativeLib();
        if (DgilLibLoader.isLoaded()) {
            init(paramTouchScreen, aperiodicTrigger);
            return;
        }
        Object[] objArr = new Object[1];
        objArr[0] = String.format("Dgil library is not loaded (loadEnabled = %b)", new Object[]{Boolean.valueOf(DgilLibLoader.isLoadEnabled())});
        TapLog.w(this, objArr);
    }

    private void init(DgilParamTouchscreen paramTouchScreen, AperiodicTrigger aperiodicTrigger) {
        this.javaLibVersion.checkNativeLibraryVersion(getNativeVersion());
        this.mAperiodicTrigger = aperiodicTrigger;
        nativeConstructor(this.mAperiodicTrigger, paramTouchScreen.getScreenResolutionX(), paramTouchScreen.getScreenResolutionY(), paramTouchScreen.getScreenSizeXMm(), paramTouchScreen.getScreenSizeYMm(), paramTouchScreen.getWindowOffsetX(), paramTouchScreen.getWindowOffsetY());
    }

    public void reset() {
        this.mDisable = true;
        this.mAperiodicTrigger.disable();
        resetDgil();
    }

    public int pressToDgil(int x, int y, long t) {
        this.mDisable = false;
        this.mInForward = false;
        this.mCancelMotion = false;
        int ret = nativePressToDgil(x, y, t);
        this.mAperiodicTrigger.start();
        return ret;
    }

    public int moveToDgil(int x, int y, long t, boolean historicalEvent) {
        if (this.mDisable) {
            return -1;
        }
        int ret = nativeMoveToDgil(x, y, t, historicalEvent);
        if (historicalEvent) {
            return ret;
        }
        this.mAperiodicTrigger.check();
        return ret;
    }

    public int moveToDgil(int x, int y, long t) {
        return moveToDgil(x, y, t, false);
    }

    public int releaseToDgil(long t) {
        if (this.mDisable) {
            return -1;
        }
        int ret = nativeReleaseToDgil(t);
        this.mAperiodicTrigger.check();
        return ret;
    }

    public void setBoolean(int id, boolean val) {
        setNativeBoolean(id, val);
    }

    public void setInteger(int id, int val) {
        setNativeInteger(id, val);
    }

    public void setFloat(int id, float val) {
        setNativeFloat(id, val);
    }

    public void setString(int id, String val) {
        setNativeString(id, val);
    }

    public boolean getBoolean(int id) {
        return getNativeBoolean(id);
    }

    public int getInteger(int id) {
        return getNativeInteger(id);
    }

    public float getFloat(int id) {
        return getNativeFloat(id);
    }

    public String getString(int id) {
        return getNativeString(id);
    }

    public void setMode(Mode mode) {
        setMode(mode.ordinal());
    }

    public Mode getMode() {
        return Mode.values()[getNativeMode()];
    }

    protected void forwardStart(boolean startAtPress) {
        this.mInForward = true;
        nativeForwardStart(startAtPress);
    }

    public boolean inForward() {
        return this.mInForward;
    }

    public Model getPrototype(String symbolName, int modelId) {
        int nbStrokes = getModelVectTraceStrokesNb(symbolName, modelId);
        if (nbStrokes == 0) {
            return null;
        }
        Model model = new Model();
        for (int i = 0; i < nbStrokes; i++) {
            model.newStroke();
            short[] array = getModelVectTrace(symbolName, modelId, i);
            for (int j = 0; j < array.length - 1; j += 2) {
                model.addPoint(array[j], array[j + 1]);
            }
        }
        return model;
    }

    public boolean getCancelMotion() {
        return this.mCancelMotion;
    }

    protected void setCancelMotion(boolean cancel) {
        if (!cancel) {
            this.mCancelMotion = false;
        } else if (inForward()) {
            this.mCancelMotion = true;
            this.mInForward = false;
            forwardStop();
        }
    }

    public void initMSH(DgilMSHListener listener) {
        initMSH(listener, true, true, false);
    }

    public void initMSH(DgilMSHListener listener, boolean allowMaybeCompleted, boolean exitOnPatternRejected, boolean dynamicMode) {
        this.mMSHListener = listener;
        if (this.mMSHListener != null) {
            initMSH(allowMaybeCompleted, exitOnPatternRejected, dynamicMode);
        }
    }

    public void setMSHSymbolClass(String symbol, MSHSymbolClass symClass) {
        if (symClass == null || symbol == null) {
            Object[] objArr = new Object[1];
            objArr[0] = String.format("SetMSHSymbolClass: Invalid args (symbol %s, msh class %s)", new Object[]{symbol, symClass});
            TapLog.w(this, objArr);
            return;
        }
        int symC = 0;
        switch (symClass) {
            case MSH_NOT_COMPLETED:
                symC = 0;
                break;
            case MSH_MAYBE_COMPLETED:
                symC = 1;
                break;
            case MSH_COMPLETED:
                symC = 2;
                break;
        }
        setMSHSymbolClass(symbol, symC);
    }

    private void actionFromDgilMSH(int rawMSHMetaclass, int nbStrokes, int timeout, boolean errorOnTimeout, String symbol, boolean formFactorValid) {
        if (this.mMSHListener == null) {
            TapLog.w(this, new Object[]{"MSH listener is null"});
            return;
        }
        switch (MSHMetaclass.values()[rawMSHMetaclass]) {
            case MSH_START_FIRST_STROKE:
                this.mMSHListener.mshStartFirstStroke();
                return;
            case MSH_START_MOVE_IN_STROKE:
                this.mMSHListener.mshStartMoveInStroke();
                return;
            case MSH_WAIT_NEXT_STROKE:
                this.mMSHListener.mshWaitNextStroke(nbStrokes, timeout, errorOnTimeout);
                return;
            case MSH_COMPLETED:
                this.mMSHListener.mshCompleted(symbol, formFactorValid);
                return;
            case MSH_REJECTED:
                this.mMSHListener.mshRejected();
                return;
            case MSH_FORCE_DEFAULT_DRAG_MODE:
                this.mMSHListener.mshForceDefaultDragMode();
                return;
            case MSH_RESET_TIMEOUT:
                this.mMSHListener.mshResetTimeout();
                return;
            case MSH_RESET:
                this.mMSHListener.mshReset();
                return;
            default:
                Object[] objArr = new Object[1];
                objArr[0] = String.format("Invalid MSH metaclass = %s", new Object[]{MSHMetaclass.values()[rawMSHMetaclass]});
                TapLog.w(this, objArr);
                return;
        }
    }

    public int mshAdaptGetAlts(StringBuffer alt1, StringBuffer alt2) {
        if (alt1 == null || alt2 == null) {
            return 0;
        }
        int ret = mshAdaptGetAltsNb();
        alt1.delete(0, alt1.length());
        alt2.delete(0, alt2.length());
        if (ret >= 1) {
            alt1.append(mshAdaptGetAlt(1));
        }
        if (ret == 2) {
            alt2.append(mshAdaptGetAlt(2));
        }
        Object[] objArr = new Object[1];
        objArr[0] = String.format("alt1 %s / alt2 %s", new Object[]{alt1, alt2});
        TapLog.d(this, objArr);
        return ret;
    }
}
