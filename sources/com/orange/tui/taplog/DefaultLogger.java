package com.orange.tui.taplog;

public class DefaultLogger extends ILogger {
    /* renamed from: d */
    public void mo1696d(String name, Object... objects) {
        System.out.println("DEBUG [" + name + "] " + append(objects));
    }

    /* renamed from: i */
    public void mo1699i(String name, Object... objects) {
        System.out.println("INFO [" + name + "] " + append(objects));
    }

    /* renamed from: e */
    public void mo1697e(String name, Throwable e, Object... objects) {
        System.out.println("ERROR [" + name + "] " + append(objects));
        e.printStackTrace();
    }

    /* renamed from: e */
    public void mo1698e(String name, Object... objects) {
        System.out.println("ERROR [" + name + "] " + append(objects));
    }

    /* renamed from: w */
    public void mo1700w(String name, Throwable e, Object... objects) {
        System.out.println("WARNING [" + name + "] " + append(objects));
        e.printStackTrace();
    }

    /* renamed from: w */
    public void mo1701w(String name, Object... objects) {
        System.out.println("WARNING [" + name + "] " + append(objects));
    }
}
