package com.orange.android.activitylifecycle;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import java.util.Iterator;
import java.util.List;

class Debug {
    private static final String DEBUG_TAG = "ActivityLifeCycle";
    private final SparseArray<String> activitiesName = new SparseArray();
    private boolean enabled;
    private final SparseArray<List<Lifecycle>> listeners;

    public Debug(SparseArray<List<Lifecycle>> listeners) {
        this.listeners = listeners;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    void showEvent(Context context, String message) {
        if (this.enabled) {
            Log.i(DEBUG_TAG, String.format("%s: %s", new Object[]{context, message}));
        }
    }

    void showListeners() {
        for (int i = 0; i < this.listeners.size(); i++) {
            showListenersFor(this.listeners.keyAt(i));
        }
    }

    private void showListenersFor(int contextHashCode) {
        StringBuilder stringBuilder = new StringBuilder("");
        Iterator i$ = ((List) this.listeners.get(contextHashCode)).iterator();
        while (i$.hasNext()) {
            stringBuilder.append(String.format(" %s", new Object[]{((Lifecycle) i$.next()).getClass().getName()}));
        }
        Log.i(DEBUG_TAG, String.format("%s (%x) listeners: %s", new Object[]{this.activitiesName.get(contextHashCode), Integer.valueOf(contextHashCode), stringBuilder}));
    }

    void register(Context context) {
        this.activitiesName.put(context.hashCode(), context.getClass().getName());
    }
}
