package com.orange.dgil.nativeinterface;

public interface MSHResultsListener {
    void mshCompleted(String str, boolean z);

    void mshForceDefaultDragMode();

    void mshRejected();
}
