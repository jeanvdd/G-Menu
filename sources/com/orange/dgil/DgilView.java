package com.orange.dgil;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.google.common.annotations.VisibleForTesting;
import com.orange.android.activitylifecycle.Lifecycle;
import com.orange.android.activitylifecycle.LifecycleEvent;

public class DgilView extends FrameLayout implements Lifecycle {
    private DgilAndroid dgil = new DgilAndroid(this);

    public DgilAndroid getDgil() {
        return this.dgil;
    }

    public void setDgil(DgilAndroid dgil) {
        this.dgil = dgil;
    }

    public DgilView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ActivityLifecycle.register(context, this);
    }

    public void onLifecycleEvent(LifecycleEvent lifecycleEvent) {
        if (lifecycleEvent == LifecycleEvent.ON_RESUME) {
            onResume();
        }
    }

    @VisibleForTesting
    void onResume() {
        onSizeChanged(-1, -1, -1, -1);
    }

    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        int[] location = getLocation();
        DisplayMetrics dm = getDisplayMetrics();
        this.dgil.onSizeChanged(dm.widthPixels, dm.heightPixels, location[0], location[1]);
    }

    @VisibleForTesting
    int[] getLocation() {
        int[] location = new int[2];
        getLocationOnScreen(location);
        return location;
    }

    @VisibleForTesting
    DisplayMetrics getDisplayMetrics() {
        WindowManager wm = (WindowManager) getContext().getSystemService("window");
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    public void reset() {
        this.dgil.reset();
    }

    boolean dispatchFromDgilTouchEvent(MotionEvent event) {
        return dispatchToChilds(event);
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return dispatchToChilds(event);
        }
        this.dgil.dispatchTouchEvent(event);
        return true;
    }

    @VisibleForTesting
    boolean dispatchToChilds(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (isEnabled()) {
            drawTrailOnCanvas(canvas);
        }
    }

    @VisibleForTesting
    void drawTrailOnCanvas(Canvas canvas) {
        this.dgil.getDrawer().draw(canvas);
    }
}
