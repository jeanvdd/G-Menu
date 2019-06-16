package com.orange.dgil.model.loader;

import com.orange.dgil.Dgil;
import com.orange.dgil.callback.ModelsStreamProvider;
import com.orange.dgil.model.bean.Unpexbin;
import com.orange.dgil.utils.TimeDuration;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ModelsLoader implements ModelsLoaderListener {
    private byte[] adaptation;
    private final Dgil dgil;
    private boolean loadModelsRequest;
    private List<Unpexbin> models;
    private final ModelsStreamProvider modelsStreamProvider;

    public ModelsLoader(Dgil dgil, ModelsStreamProvider modelsStreamProvider) {
        this.dgil = dgil;
        this.modelsStreamProvider = modelsStreamProvider;
        asyncLoad();
    }

    public boolean modelsLoaded() {
        return this.models != null;
    }

    public void onModelsLoaded(List<Unpexbin> models, byte[] adaptation) {
        this.models = models;
        this.adaptation = adaptation;
        if (this.loadModelsRequest) {
            loadModels();
        }
    }

    public void saveAdaptation() {
        try {
            OutputStream outputStream = this.modelsStreamProvider.adaptationOutputStream();
            if (outputStream != null) {
                this.adaptation = this.dgil.mshGetAdaptMu();
                outputStream.write(this.adaptation);
                outputStream.close();
            }
        } catch (IOException e) {
            System.out.println("Failed to save adaptation file: " + e.getMessage());
        }
    }

    public void setLoadModelsRequest(boolean loadRequest) {
        this.loadModelsRequest = loadRequest;
        if (this.loadModelsRequest && modelsLoaded()) {
            this.loadModelsRequest = false;
            loadModels();
        }
    }

    private void asyncLoad() {
        new ModelsAsyncLoader(this, this.modelsStreamProvider).start();
    }

    private void loadModels() {
        TimeDuration timeDuration = new TimeDuration();
        doLoadModels();
        timeDuration.showDeltaTime("Update dgil models");
    }

    private void doLoadModels() {
        this.dgil.unloadSymbols();
        for (Unpexbin unpexbin : this.models) {
            this.dgil.loadUnpexbin(unpexbin);
        }
        if (this.adaptation != null) {
            this.dgil.mshSetAdaptMu(this.adaptation);
        }
    }
}
