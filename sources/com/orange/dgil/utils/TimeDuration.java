package com.orange.dgil.utils;

public class TimeDuration {
    private final long t0 = getTime();

    public void showDeltaTime(String msg) {
        System.out.println(TimeDuration.class.getSimpleName() + String.format("%s - %d ms", new Object[]{msg, Integer.valueOf((int) ((getTime() - this.t0) / 1000000))}));
    }

    private static long getTime() {
        return System.nanoTime();
    }
}
