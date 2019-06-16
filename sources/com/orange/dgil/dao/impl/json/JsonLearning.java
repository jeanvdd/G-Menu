package com.orange.dgil.dao.impl.json;

import com.orange.dgil.bean.Learning;
import com.orange.dgil.bean.linked.LinkedSymbol;
import java.util.ArrayList;
import java.util.List;

public class JsonLearning extends Learning {
    private static final long serialVersionUID = 1;
    private final List<LinkedSymbol> symbols = new ArrayList();

    public JsonLearning(int id, String name) {
        super((long) id, name);
    }

    public List<LinkedSymbol> getSymbols() {
        return this.symbols;
    }
}
