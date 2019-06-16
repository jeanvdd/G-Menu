package com.orange.dgil;

import com.orange.dgil.event.MotionEventBuilder;

class ForgeParameters {
    private final MotionEventBuilder eventBuilder;
    private final FromDgil fromDgil;
    /* renamed from: t */
    private long f11t;
    /* renamed from: x */
    private int f12x;
    /* renamed from: y */
    private int f13y;

    ForgeParameters(MotionEventBuilder eventBuilder, FromDgil fromDgil) {
        this.eventBuilder = eventBuilder;
        this.fromDgil = fromDgil;
    }

    void setEventProperties(int x, int y, long t) {
        this.f12x = x;
        this.f13y = y;
        this.f11t = t;
    }

    int getX() {
        return this.f12x;
    }

    int getY() {
        return this.f13y;
    }

    long getT() {
        return this.f11t;
    }

    MotionEventBuilder getEventBuilder() {
        return this.eventBuilder;
    }

    FromDgil getFromDgil() {
        return this.fromDgil;
    }
}
