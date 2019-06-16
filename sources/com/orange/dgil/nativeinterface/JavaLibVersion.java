package com.orange.dgil.nativeinterface;

import com.orange.tui.taplog.TapLog;

class JavaLibVersion {
    private static final int DGIL_JAVA_VERSION = 42752;

    JavaLibVersion() {
    }

    void checkNativeLibraryVersion(int nativeVersion) {
        boolean invalidVersion;
        if (nativeVersion != DGIL_JAVA_VERSION) {
            invalidVersion = true;
        } else {
            invalidVersion = false;
        }
        if (invalidVersion) {
            throw new DgilVersionError(String.format("Invalid version: native %x - java %x", new Object[]{Integer.valueOf(nativeVersion), Integer.valueOf(DGIL_JAVA_VERSION)}));
        }
        Object[] objArr = new Object[1];
        objArr[0] = String.format("Dgil version: %x", new Object[]{Integer.valueOf(DGIL_JAVA_VERSION)});
        TapLog.w(this, objArr);
    }
}
