package com.orange.dgil;

import android.os.Handler;
import com.google.common.annotations.VisibleForTesting;
import com.orange.dgil.event.EventProperties;
import com.orange.dgil.event.IGestureInfo;
import com.orange.dgil.event.IToDgil;
import com.orange.dgil.event.MotionEventBuilder;
import com.orange.dgil.eventfilter.MultitouchFilter;
import com.orange.dgil.forwardflick.ForwardFlickRunnable;
import com.orange.dgil.trail.android.ITrailDrawer;

class Context {
    private final DgilAndroid dgil;
    private DgilView dgilView;
    private DispatchTouchEvent dispatchTouchEvent;
    private ITrailDrawer drawer;
    private MotionEventBuilder eventBuilder;
    private EventProperties eventProperties;
    private ForwardFlickRunnable forwardFlick;
    private FromDgil fromDgil;
    private IGestureInfo gestureInfo;
    private final MultitouchFilter multitouchFilter = new MultitouchFilter();
    private ToDgil toDgil;
    private IToDgil toDgilInterface;

    public void setDgilView(DgilView dgilView) {
        this.dgilView = dgilView;
    }

    public DgilView getDgilView() {
        return this.dgilView;
    }

    public DgilAndroid getDgil() {
        return this.dgil;
    }

    public DispatchTouchEvent getDispatchTouchEvent() {
        return this.dispatchTouchEvent;
    }

    public IToDgil getToDgilInterface() {
        return this.toDgilInterface;
    }

    public IGestureInfo getGestureInfo() {
        return this.gestureInfo;
    }

    public void setDrawer(ITrailDrawer drawer) {
        this.drawer = drawer;
    }

    public ITrailDrawer getDrawer() {
        return this.drawer;
    }

    public MultitouchFilter getMultitouchFilter() {
        return this.multitouchFilter;
    }

    public EventProperties getEventProperties() {
        return this.eventProperties;
    }

    public ToDgil getToDgil() {
        return this.toDgil;
    }

    public FromDgil getFromDgil() {
        return this.fromDgil;
    }

    public ForwardFlickRunnable getForwardFlick() {
        return this.forwardFlick;
    }

    public MotionEventBuilder getEventBuilder() {
        return this.eventBuilder;
    }

    Context(DgilView dgilView, DgilAndroid dgil, ITrailDrawer drawer) {
        this.dgilView = dgilView;
        this.dgil = dgil;
        this.drawer = drawer;
        init();
    }

    @VisibleForTesting
    void init() {
        this.gestureInfo = this.dgil;
        this.toDgilInterface = this.dgil;
        this.eventBuilder = new MotionEventBuilder(this.dgil.getTs());
        this.toDgil = new ToDgil(this);
        this.fromDgil = new FromDgil(this);
        this.dispatchTouchEvent = new DispatchTouchEvent(this);
        initForwardFlick();
        initEventProperties();
    }

    @VisibleForTesting
    void initForwardFlick() {
        this.forwardFlick = new ForwardFlickRunnable(this.dgil, getHandler());
        this.forwardFlick.setEnabled(this.dgil.getSettings().getFlickReplayEnabled(), this.dgil.getTs());
    }

    @VisibleForTesting
    Handler getHandler() {
        return new Handler();
    }

    @VisibleForTesting
    void initEventProperties() {
        this.eventProperties = new EventProperties();
        this.eventProperties.setForward(this.dispatchTouchEvent);
    }
}
