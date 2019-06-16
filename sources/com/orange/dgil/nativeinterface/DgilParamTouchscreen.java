package com.orange.dgil.nativeinterface;

public class DgilParamTouchscreen {
    private int pixelPitchUm;
    private int screenResolutionX;
    private int screenResolutionY;
    private int screenSizeXMm;
    private int screenSizeYMm;
    private int windowOffsetX;
    private int windowOffsetY;

    public void setPixelPitchUm(int pixelPitchUm) {
        this.pixelPitchUm = pixelPitchUm;
    }

    public void setScreenResolutionX(int screenResolutionX) {
        this.screenResolutionX = screenResolutionX;
    }

    public void setScreenResolutionY(int screenResolutionY) {
        this.screenResolutionY = screenResolutionY;
    }

    public void setScreenSizeXMm(int screenSizeXMm) {
        this.screenSizeXMm = screenSizeXMm;
    }

    public void setScreenSizeYMm(int screenSizeYMm) {
        this.screenSizeYMm = screenSizeYMm;
    }

    public void setWindowOffsetX(int windowOffsetX) {
        this.windowOffsetX = windowOffsetX;
    }

    public void setWindowOffsetY(int windowOffsetY) {
        this.windowOffsetY = windowOffsetY;
    }

    public int getScreenResolutionX() {
        return this.screenResolutionX;
    }

    public int getScreenResolutionY() {
        return this.screenResolutionY;
    }

    public int getScreenSizeXMm() {
        return this.screenSizeXMm;
    }

    public int getScreenSizeYMm() {
        return this.screenSizeYMm;
    }

    public int getWindowOffsetX() {
        return this.windowOffsetX;
    }

    public int getWindowOffsetY() {
        return this.windowOffsetY;
    }

    public int getPixelPitchUm() {
        return this.pixelPitchUm;
    }
}
