package com.orange.dgil.model.loader;

import com.orange.dgil.callback.ModelsStreamProvider;
import com.orange.dgil.model.bean.Unpexbin;
import com.orange.dgil.model.dao.impl.json.UnpexbinJsonStreamReaderImpl;
import com.orange.dgil.utils.TimeDuration;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

class ModelsAsyncLoader extends Thread {
    private InputStream adaptationInputStream;
    private final ModelsLoaderListener listener;
    private InputStream modelsInputStream;

    ModelsAsyncLoader(ModelsLoaderListener listener, ModelsStreamProvider modelsStreamProvider) {
        this.listener = listener;
        init(modelsStreamProvider);
    }

    private void init(ModelsStreamProvider modelsStreamProvider) {
        try {
            this.modelsInputStream = modelsStreamProvider.modelsInputStream();
            this.adaptationInputStream = modelsStreamProvider.adaptationInputStream();
        } catch (IOException e) {
            showError(e);
        }
    }

    public void run() {
        try {
            loadFromInputStream();
        } catch (IOException e) {
            showError(e);
        }
    }

    private void showError(Exception e) {
        System.out.println(String.format("Failed to load models: %s", new Object[]{e.getMessage()}));
        e.printStackTrace();
    }

    private void loadFromInputStream() throws IOException {
        if (this.modelsInputStream != null) {
            doLoadFromInputStream();
            return;
        }
        throw new IOException("Input stream is null");
    }

    private void doLoadFromInputStream() throws IOException {
        TimeDuration measureTime = new TimeDuration();
        List<Unpexbin> models = readModels();
        this.modelsInputStream.close();
        this.modelsInputStream = null;
        this.listener.onModelsLoaded(models, readAdaptation());
        measureTime.showDeltaTime("Load models from resource");
    }

    private List<Unpexbin> readModels() throws IOException {
        return new UnpexbinJsonStreamReaderImpl(this.modelsInputStream).read();
    }

    private byte[] readAdaptation() throws IOException {
        if (this.adaptationInputStream == null) {
            return null;
        }
        byte[] adaptation = readAdaptationBytes();
        this.adaptationInputStream.close();
        this.adaptationInputStream = null;
        return adaptation;
    }

    private byte[] readAdaptationBytes() throws IOException {
        byte[] readArray = new byte[4096];
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        while (true) {
            int count = this.adaptationInputStream.read(readArray, 0, readArray.length);
            if (count != -1) {
                buffer.write(readArray, 0, count);
            } else {
                buffer.flush();
                return buffer.toByteArray();
            }
        }
    }
}
