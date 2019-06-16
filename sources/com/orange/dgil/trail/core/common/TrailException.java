package com.orange.dgil.trail.core.common;

public abstract class TrailException extends RuntimeException {
    public TrailException(String message) {
        super(message);
        trace(message);
    }

    private void trace(String message) {
        System.out.println(String.format("Trail exception: '%s'", new Object[]{message}));
    }
}
