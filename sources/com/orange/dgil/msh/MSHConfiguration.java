package com.orange.dgil.msh;

public class MSHConfiguration {
    private final MSHAnimationDelays animationDelays = new MSHAnimationDelays();
    private final MSHColors colors = new MSHColors();
    private boolean enableMaybeCompletedFading;
    private boolean enableNotCompletedFading = true;

    public MSHColors getColors() {
        return this.colors;
    }

    public MSHAnimationDelays getAnimationDelays() {
        return this.animationDelays;
    }

    public boolean isEnableMaybeCompletedFading() {
        return this.enableMaybeCompletedFading;
    }

    public boolean isEnableNotCompletedFading() {
        return this.enableNotCompletedFading;
    }

    public void setTransitionsBehaviour(boolean enableMaybeCompletedFading, boolean enableNotCompletedFading) {
        this.enableMaybeCompletedFading = enableMaybeCompletedFading;
        this.enableNotCompletedFading = enableNotCompletedFading;
    }
}
