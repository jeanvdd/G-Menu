package com.orange.dgil.model.dao;

import com.orange.dgil.model.bean.Unpexbin;
import java.io.IOException;
import java.util.List;

public interface UnpexbinWriter {
    void write(List<Unpexbin> list) throws IOException;
}
