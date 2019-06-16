package com.orange.dgil.callback;

public class FlickParameters {
    private int flickDirection;
    private int flickDirectionAngularError;
    private int pixelPitch;
    private int velocityEnd;
    private int velocityEndX;
    private int velocityEndY;

    void set(int velocityEnd, int velocityEndX, int velocityEndY, int pixelPitch, int flickDirection, int flickDirectionAngularError) {
        this.velocityEnd = velocityEnd;
        this.velocityEndX = velocityEndX;
        this.velocityEndY = velocityEndY;
        this.pixelPitch = pixelPitch;
        this.flickDirection = flickDirection;
        this.flickDirectionAngularError = flickDirectionAngularError;
    }

    public int getVelocityEnd() {
        return this.velocityEnd;
    }

    public int getVelocityEndX() {
        return this.velocityEndX;
    }

    public int getVelocityEndY() {
        return this.velocityEndY;
    }

    public int getPixelPitch() {
        return this.pixelPitch;
    }

    public void setFlickDirection(int flickDirection) {
        this.flickDirection = flickDirection;
    }

    public int getFlickDirection() {
        return this.flickDirection;
    }

    public int getFlickDirectionAngularError() {
        return this.flickDirectionAngularError;
    }
}
