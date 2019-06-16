package com.orange.dgil.model.dao;

import com.orange.dgil.model.bean.Unpexbin;
import java.io.IOException;
import java.util.List;

public final class DgilReco {
    private static final DgilReco INSTANCE = new DgilReco();
    private UnpexbinReader reader;

    public static DgilReco get() {
        return INSTANCE;
    }

    private DgilReco() {
    }

    public UnpexbinReader getUnpexbinReader() {
        return this.reader;
    }

    public void setUnpexbinReader(UnpexbinReader reader) {
        this.reader = reader;
    }

    public List<Unpexbin> getUnpexbins() throws IOException {
        if (this.reader != null) {
            return this.reader.read();
        }
        throw new UnsupportedOperationException("unpexbin database access object not provided.");
    }
}
