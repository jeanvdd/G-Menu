package com.orange.dgil;

import android.view.MotionEvent;
import com.google.common.annotations.VisibleForTesting;
import com.orange.android.activitylifecycle.Lifecycle;
import com.orange.android.activitylifecycle.LifecycleEvent;
import com.orange.dgil.aperiodictrigger.impl.AperiodicTriggerAndroidImpl;
import com.orange.dgil.callback.FlickParameters;
import com.orange.dgil.conf.Modes.Mode;
import com.orange.dgil.event.IFromDgilMotionEvent;
import com.orange.dgil.event.IGestureInfo;
import com.orange.dgil.event.IToDgil;
import com.orange.dgil.model.dao.UnpexbinReader;
import com.orange.dgil.nativeinterface.DgilLibLoader;
import com.orange.dgil.trail.android.ITrailDrawer;
import com.orange.dgil.trail.android.impl.TrailDrawer;
import java.io.IOException;

public class DgilAndroid extends Dgil implements IFromDgilMotionEvent, IToDgil, IGestureInfo, Lifecycle {
    private BasicFlick basicFlick;
    private Context context;
    private AndroidDetectedImpl detectedImpl;
    private boolean libLoaded;

    public DgilAndroid(DgilView dgilView) {
        this(dgilView, new TrailDrawer(dgilView));
    }

    public DgilAndroid(DgilView dgilView, ITrailDrawer drawer, UnpexbinReader reader) throws IOException {
        this(dgilView, drawer);
        setUnpexbeanReader(reader);
    }

    public DgilAndroid(DgilView dgilView, ITrailDrawer drawer) {
        super(new DgilParamTouchscreenAndroid(dgilView.getContext()), new AperiodicTriggerAndroidImpl());
        this.libLoaded = DgilLibLoader.isLoaded();
        if (this.libLoaded) {
            init(dgilView, drawer);
        }
    }

    @VisibleForTesting
    void init(DgilView dgilView, ITrailDrawer drawer) {
        dgilView.setDgil(this);
        this.context = getNewContext(dgilView, drawer);
        this.detectedImpl = getNewDetectedImpl(this.context);
        this.basicFlick = new BasicFlick();
        registerForLifecycle(dgilView);
    }

    @VisibleForTesting
    Context getNewContext(DgilView dgilView, ITrailDrawer drawer) {
        return new Context(dgilView, this, drawer);
    }

    @VisibleForTesting
    AndroidDetectedImpl getNewDetectedImpl(Context ctx) {
        return new AndroidDetectedImpl(ctx);
    }

    @VisibleForTesting
    void setNativeDgilJavaObjectToThis() {
        updateDgilJavaObject();
    }

    public void reset() {
        if (this.libLoaded) {
            this.context.getToDgil().reset();
            this.context.getForwardFlick().reset();
            parentReset();
        }
    }

    @VisibleForTesting
    void parentReset() {
        super.reset();
    }

    public void dispatchTouchEvent(MotionEvent event) {
        this.context.getDispatchTouchEvent().dispatchTouchEvent(event);
    }

    public void onSizeChanged(int screenW, int screenH, int viewXOffset, int viewYOffset) {
        if (this.libLoaded) {
            this.context.getEventBuilder().setWindowOffset(viewXOffset, viewYOffset);
            this.context.getEventBuilder().setScreenResolution(screenW, screenH);
            setScreenResolutionAndViewOffset(screenW, screenH, viewXOffset, viewYOffset);
        }
    }

    @VisibleForTesting
    void setScreenResolutionAndViewOffset(int screenW, int screenH, int viewXOffset, int viewYOffset) {
        setHardwareParamTS(0, screenW, 0, screenH, viewXOffset, viewYOffset);
    }

    public void pressFromDgil(int x, int y) {
        this.context.getFromDgil().pressFromDgil(x, y);
    }

    public void moveFromDgil(int x, int y) {
        this.context.getFromDgil().moveFromDgil(x, y);
    }

    public void releaseFromDgil(int vEndX, int vEndY, int vEnd) {
        this.context.getFromDgil().releaseFromDgil();
    }

    public void tapDetected(int x, int y) {
        this.detectedImpl.tapDetected(x, y);
    }

    public void flickDetected(FlickParameters flickParameters) {
        super.flickDetected(flickParameters);
        this.detectedImpl.flickDetected();
    }

    public boolean symbolicDetectedInternal(String s1, int sim1) {
        return this.detectedImpl.symbolicDetectedInternal(s1, sim1);
    }

    public int basicFlickDirection() {
        return this.basicFlick.basicFlickDirection(this.context.getToDgil());
    }

    public void setMode(Mode mode) {
        if (mode == Mode.POINTING) {
            this.context.getDrawer().touchCancel();
        }
        parentSetMode(mode);
    }

    @VisibleForTesting
    void parentSetMode(Mode mode) {
        super.setMode(mode);
    }

    protected void setDgilView(DgilView dgilView) {
        this.context.setDgilView(dgilView);
    }

    public DgilView getDgilView() {
        return this.context.getDgilView();
    }

    public ITrailDrawer getDrawer() {
        return this.context.getDrawer();
    }

    public void setDrawer(ITrailDrawer drawer) {
        this.context.setDrawer(drawer);
    }

    public boolean isInGesture() {
        return this.context.getToDgil().isInGesture();
    }

    public boolean isMotionCancelled() {
        return getCancelMotion();
    }

    @VisibleForTesting
    void registerForLifecycle(DgilView dgilView) {
        ActivityLifecycle.register(dgilView.getContext(), this);
    }

    public void onLifecycleEvent(LifecycleEvent lifecycleEvent) {
        if (lifecycleEvent == LifecycleEvent.ON_PAUSE) {
            onPause();
        } else if (lifecycleEvent == LifecycleEvent.ON_RESUME) {
            onResume();
        }
    }

    @VisibleForTesting
    protected void onPause() {
        reset();
    }

    @VisibleForTesting
    protected void onResume() {
        setNativeDgilJavaObjectToThis();
    }
}
