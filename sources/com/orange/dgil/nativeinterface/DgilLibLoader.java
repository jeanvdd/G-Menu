package com.orange.dgil.nativeinterface;

import com.orange.tui.taplog.TapLog;

public class DgilLibLoader {
    private static String libPath;
    private static boolean loadEnabled = true;
    private static boolean loaded;

    public static boolean isLoaded() {
        return loaded;
    }

    public static void setLibPath(String path) {
        libPath = path;
    }

    public static void setLoadEnabled(boolean enabled) {
        loadEnabled = enabled;
    }

    static boolean isLoadEnabled() {
        return loadEnabled;
    }

    void loadNativeLib() {
        if (!loaded && loadEnabled) {
            doLoadNativeLib();
        }
    }

    private void doLoadNativeLib() {
        try {
            systemLoad();
            loaded = true;
        } catch (NoSuchMethodError e) {
            String simpleName = DgilNative.class.getSimpleName();
            r2 = new Object[2];
            r2[0] = String.format("Failed to load dgil library (%s)", new Object[]{e.getMessage()});
            r2[1] = e;
            TapLog.e(simpleName, r2);
        }
    }

    private void systemLoad() {
        if (libPath == null) {
            System.loadLibrary("dgil");
        } else {
            System.load(libPath);
        }
    }
}
