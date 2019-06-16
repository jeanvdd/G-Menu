package com.orange.dgil.callback;

import com.orange.dgil.Dgil;

public class ListenerParams {
    private final Dgil dgil;
    private ModelsStreamProvider modelsStreamProvider;

    public ListenerParams(Dgil dgil) {
        this.dgil = dgil;
    }

    public Dgil dgil() {
        return this.dgil;
    }

    public ModelsStreamProvider modelsStreamProvider() {
        return this.modelsStreamProvider;
    }

    public ListenerParams(Dgil dgil, ModelsStreamProvider modelsStreamProvider) {
        this(dgil);
        this.modelsStreamProvider = modelsStreamProvider;
    }

    public boolean hasModelsStreamProvider() {
        return this.modelsStreamProvider != null;
    }
}
