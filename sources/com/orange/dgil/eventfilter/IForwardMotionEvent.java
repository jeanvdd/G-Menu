package com.orange.dgil.eventfilter;

import android.view.MotionEvent;

public interface IForwardMotionEvent {
    void eventToChildViews(MotionEvent motionEvent);

    void eventToDgilAnalysis(MotionEvent motionEvent, int i);

    void firstMultitouchEvent(MotionEvent motionEvent);
}
