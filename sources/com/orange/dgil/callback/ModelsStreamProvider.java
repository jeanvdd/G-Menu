package com.orange.dgil.callback;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface ModelsStreamProvider {
    InputStream adaptationInputStream() throws IOException;

    OutputStream adaptationOutputStream() throws IOException;

    InputStream modelsInputStream() throws IOException;
}
