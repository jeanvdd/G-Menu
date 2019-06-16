package com.orange.tui.taplog;

public abstract class ILogger {
    /* renamed from: d */
    public abstract void mo1696d(String str, Object... objArr);

    /* renamed from: e */
    public abstract void mo1697e(String str, Throwable th, Object... objArr);

    /* renamed from: e */
    public abstract void mo1698e(String str, Object... objArr);

    /* renamed from: i */
    public abstract void mo1699i(String str, Object... objArr);

    /* renamed from: w */
    public abstract void mo1700w(String str, Throwable th, Object... objArr);

    /* renamed from: w */
    public abstract void mo1701w(String str, Object... objArr);

    public String append(Object... objects) {
        StringBuilder builder = new StringBuilder();
        for (Object object : objects) {
            builder.append(object == null ? "null" : object.toString());
        }
        return builder.toString();
    }
}
