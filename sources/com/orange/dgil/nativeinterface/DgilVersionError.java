package com.orange.dgil.nativeinterface;

public final class DgilVersionError extends RuntimeException {
    private static final long serialVersionUID = 1;

    public DgilVersionError(String msg) {
        super(msg);
    }
}
