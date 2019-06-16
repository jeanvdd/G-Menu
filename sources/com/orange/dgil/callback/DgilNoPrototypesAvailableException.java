package com.orange.dgil.callback;

public class DgilNoPrototypesAvailableException extends RuntimeException {
    private static final long serialVersionUID = 1;

    DgilNoPrototypesAvailableException(String msg) {
        super(msg);
    }
}
