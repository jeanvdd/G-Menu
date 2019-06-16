package com.orange.dgil.aperiodictrigger;

public abstract class AperiodicTrigger {
    public abstract void check();

    public abstract void disable();

    public abstract void init(long j);

    protected native void nativeTrigger(boolean z);

    public abstract void resetTrigger();

    public abstract void start();

    public abstract void trigger(boolean z);
}
