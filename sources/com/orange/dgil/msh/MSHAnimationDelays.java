package com.orange.dgil.msh;

public class MSHAnimationDelays {
    private static final int DEFAULT_FADING_DELAY = 200;
    private int fadingDelayNok = 200;
    private int fadingDelayOk;
    private int fadingDurationNok = 200;
    private int fadingDurationOk = 200;

    public void setFadingDelayNok(int fadingDelayNok) {
        this.fadingDelayNok = fadingDelayNok;
    }

    public void setFadingDelayOk(int fadingDelayOk) {
        this.fadingDelayOk = fadingDelayOk;
    }

    public void setFadingDurationNok(int fadingDurationNok) {
        this.fadingDurationNok = fadingDurationNok;
    }

    public void setFadingDurationOk(int fadingDurationOk) {
        this.fadingDurationOk = fadingDurationOk;
    }

    public int getFadingDelayOk() {
        return this.fadingDelayOk;
    }

    public int getFadingDelayNok() {
        return this.fadingDelayNok;
    }

    public int getFadingDurationOk() {
        return this.fadingDurationOk;
    }

    public int getFadingDurationNok() {
        return this.fadingDurationNok;
    }
}
