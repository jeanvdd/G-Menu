package com.orange.tui.taplog;

public class TapLog {
    static final /* synthetic */ boolean $assertionsDisabled = (!TapLog.class.desiredAssertionStatus());
    public static boolean DEBUG = false;
    private static final TapLog instance = new TapLog();
    private ILogger logger = new DefaultLogger();
    private TagBuilder tagBuilder = new C02221();

    /* renamed from: com.orange.tui.taplog.TapLog$1 */
    class C02221 implements TagBuilder {
        C02221() {
        }

        public String buildTag(Object obect) {
            return obect.getClass().getName();
        }
    }

    private TapLog() {
    }

    public static void setLogger(ILogger logger) {
        if ($assertionsDisabled || logger != null) {
            instance.logger = logger;
            return;
        }
        throw new AssertionError();
    }

    public static void setTagBuilder(TagBuilder builder) {
        if ($assertionsDisabled || builder != null) {
            instance.tagBuilder = builder;
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: e */
    public static void m31e(String name, Throwable e, Object... objects) {
        instance.logger.mo1697e(name, e, objects);
    }

    /* renamed from: e */
    public static void m32e(String name, Object... objects) {
        instance.logger.mo1698e(name, objects);
    }

    /* renamed from: e */
    public static void m30e(Object o, Object... objects) {
        instance.logger.mo1698e(instance.tagBuilder.buildTag(o), objects);
    }

    /* renamed from: e */
    public static void m29e(Object o, Throwable e, Object... objects) {
        instance.logger.mo1697e(instance.tagBuilder.buildTag(o), e, objects);
    }

    /* renamed from: w */
    public static void m35w(Object o, Throwable e, Object... objects) {
        instance.logger.mo1700w(instance.tagBuilder.buildTag(o), e, objects);
    }

    /* renamed from: w */
    public static void m37w(String name, Throwable e, Object... objects) {
        instance.logger.mo1700w(name, e, objects);
    }

    /* renamed from: w */
    public static void m36w(Object o, Object... objects) {
        instance.logger.mo1701w(instance.tagBuilder.buildTag(o), objects);
    }

    /* renamed from: w */
    public static void m38w(String name, Object... objects) {
        instance.logger.mo1701w(name, objects);
    }

    /* renamed from: d */
    public static void m28d(String name, Object... objects) {
        if (DEBUG) {
            instance.logger.mo1696d(name, objects);
        }
    }

    /* renamed from: d */
    public static void m27d(Object o, Object... objects) {
        if (DEBUG) {
            instance.logger.mo1696d(instance.tagBuilder.buildTag(o), objects);
        }
    }

    /* renamed from: i */
    public static void m34i(String name, Object... objects) {
        if (DEBUG) {
            instance.logger.mo1699i(name, objects);
        }
    }

    /* renamed from: i */
    public static void m33i(Object o, Object... objects) {
        if (DEBUG) {
            instance.logger.mo1699i(instance.tagBuilder.buildTag(o), objects);
        }
    }
}
