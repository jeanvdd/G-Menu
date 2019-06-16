package com.orange.dgil;

public final class DgilLoader {
    private static final DgilLoader INSTANCE = new DgilLoader();

    private DgilLoader() {
    }

    public static DgilLoader get() {
        return INSTANCE;
    }

    public void load(String symbolName, byte[] unpexBin) {
    }
}
