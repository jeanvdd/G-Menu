package com.orange.dgil.model.loader;

import com.orange.dgil.model.bean.Unpexbin;
import java.util.List;

interface ModelsLoaderListener {
    void onModelsLoaded(List<Unpexbin> list, byte[] bArr);
}
