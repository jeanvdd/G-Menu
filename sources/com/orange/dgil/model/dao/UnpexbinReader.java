package com.orange.dgil.model.dao;

import com.orange.dgil.model.bean.Unpexbin;
import java.io.IOException;
import java.util.List;

public interface UnpexbinReader {
    List<Unpexbin> read() throws IOException;
}
