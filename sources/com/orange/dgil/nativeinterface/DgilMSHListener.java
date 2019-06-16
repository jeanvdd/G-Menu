package com.orange.dgil.nativeinterface;

public interface DgilMSHListener {
    void mshCompleted(String str, boolean z);

    void mshForceDefaultDragMode();

    void mshRejected();

    void mshReset();

    void mshResetTimeout();

    void mshStartFirstStroke();

    void mshStartMoveInStroke();

    void mshWaitNextStroke(int i, int i2, boolean z);

    void startSymbolic();
}
