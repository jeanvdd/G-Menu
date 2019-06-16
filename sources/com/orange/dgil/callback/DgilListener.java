package com.orange.dgil.callback;

public interface DgilListener extends DgilCallbacks {
    void onListenerSelected();

    void onListenerUnselected();

    void saveAdaptation();

    void setLoadModelsRequest(boolean z);
}
