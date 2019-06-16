package com.orange.dgil;

import android.content.Context;
import com.orange.dgil.callback.ListenerParams;
import com.orange.dgil.callback.ModelsStreamProvider;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AndroidListenerParams extends ListenerParams {
    private static final String ADAPT_FILENAME = "dgil_models_adaptation.bin";

    public AndroidListenerParams(Dgil dgil) {
        super(dgil);
    }

    public AndroidListenerParams(Dgil dgil, int modelsResourceId) {
        super(dgil, modelsStreamProvider(dgil, modelsResourceId, null, null));
    }

    public AndroidListenerParams(Dgil dgil, String modelsAssetFileName) throws IOException {
        super(dgil, modelsStreamProvider(dgil, 0, modelsAssetFileName, null));
    }

    public AndroidListenerParams(Dgil dgil, int modelsResourceId, String adaptationFileName) throws IOException {
        super(dgil, modelsStreamProvider(dgil, modelsResourceId, null, adaptationFileName));
    }

    public AndroidListenerParams(Dgil dgil, String modelsAssetFileName, String adaptationFileName) throws IOException {
        super(dgil, modelsStreamProvider(dgil, 0, modelsAssetFileName, adaptationFileName));
    }

    private static ModelsStreamProvider modelsStreamProvider(Dgil dgil, final int modelsResourceId, final String modelsAssetFileName, final String adaptationFileName) {
        final Context context = ((DgilAndroid) dgil).getDgilView().getContext();
        return new ModelsStreamProvider() {
            public InputStream modelsInputStream() throws IOException {
                if (modelsAssetFileName == null) {
                    return context.getResources().openRawResource(modelsResourceId);
                }
                return context.getAssets().open(modelsAssetFileName);
            }

            public InputStream adaptationInputStream() throws IOException {
                return adaptationFileName == null ? null : context.openFileInput(adaptationFileName);
            }

            public OutputStream adaptationOutputStream() throws IOException {
                return adaptationFileName == null ? null : context.openFileOutput(adaptationFileName, 0);
            }
        };
    }
}
