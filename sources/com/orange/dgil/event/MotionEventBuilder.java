package com.orange.dgil.event;

import android.support.v4.view.InputDeviceCompat;
import android.view.MotionEvent;
import com.google.common.annotations.VisibleForTesting;
import com.orange.dgil.nativeinterface.DgilParamTouchscreen;
import com.orange.tui.taplog.TapLog;

public class MotionEventBuilder {
    private int action;
    private long downTime;
    private boolean hasSourceMethod;
    private int screenHeight;
    private int screenWidth;
    /* renamed from: x */
    private int f14x;
    private int xWindowOffset;
    /* renamed from: y */
    private int f15y;
    private int yWindowOffset;

    public MotionEventBuilder() {
        init(null);
    }

    public MotionEventBuilder(DgilParamTouchscreen ts) {
        init(ts);
    }

    @VisibleForTesting
    void init(DgilParamTouchscreen ts) {
        initHasSourceMethod();
        if (ts != null) {
            setScreenResolution(ts.getScreenResolutionX(), ts.getScreenResolutionY());
            setWindowOffset(ts.getWindowOffsetX(), ts.getWindowOffsetY());
        }
    }

    @VisibleForTesting
    void initHasSourceMethod() {
        this.hasSourceMethod = true;
        try {
            tryToGetSetSourceMethod();
        } catch (NoSuchMethodException e) {
            this.hasSourceMethod = false;
        }
    }

    @VisibleForTesting
    void tryToGetSetSourceMethod() throws NoSuchMethodException {
        MotionEvent.class.getMethod("setSource", new Class[]{Integer.TYPE});
    }

    public MotionEvent obtainMotionEvent(int x, int y, long t) {
        updatePosition(x, y);
        MotionEvent event = obtainEvent(x, y, t);
        if (this.hasSourceMethod) {
            event.setSource(InputDeviceCompat.SOURCE_TOUCHSCREEN);
        }
        return event;
    }

    @VisibleForTesting
    void updatePosition(int x, int y) {
        this.f14x = x;
        this.f15y = y;
    }

    @VisibleForTesting
    MotionEvent obtainEvent(int x, int y, long t) {
        MotionEvent event = MotionEvent.obtain(this.downTime, t, this.action, (float) (this.xWindowOffset + x), (float) (this.yWindowOffset + y), 0);
        event.offsetLocation((float) (-this.xWindowOffset), (float) (-this.yWindowOffset));
        return event;
    }

    public void checkPoint(int x, int y) {
        boolean invalid;
        if (this.xWindowOffset + x < 0 || this.yWindowOffset + y < 0 || this.xWindowOffset + x > this.screenWidth || this.yWindowOffset + y > this.screenHeight) {
            invalid = true;
        } else {
            invalid = false;
        }
        if (invalid) {
            Object[] objArr = new Object[1];
            objArr[0] = String.format("Invalid pt (%d, %d) (%d %d %d %d)", new Object[]{Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(this.xWindowOffset), Integer.valueOf(this.yWindowOffset), Integer.valueOf(this.screenWidth), Integer.valueOf(this.screenHeight)});
            TapLog.w(this, objArr);
        }
    }

    public long getDownTime() {
        return this.downTime;
    }

    public int getX() {
        return this.f14x;
    }

    public int getY() {
        return this.f15y;
    }

    public void setDownTime(long downTime) {
        this.downTime = downTime;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public void setScreenResolution(int width, int height) {
        this.screenWidth = width;
        this.screenHeight = height;
    }

    public void setWindowOffset(int xWindowOffset, int yWindowOffset) {
        this.xWindowOffset = xWindowOffset;
        this.yWindowOffset = yWindowOffset;
    }
}
