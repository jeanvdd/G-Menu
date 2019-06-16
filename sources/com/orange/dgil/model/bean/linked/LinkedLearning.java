package com.orange.dgil.model.bean.linked;

import com.orange.dgil.model.bean.Learning;
import java.util.ArrayList;
import java.util.List;

public class LinkedLearning extends Learning {
    private static final long serialVersionUID = 1;
    private List<LinkedSymbol> symbols = new ArrayList();

    public LinkedLearning(long id, String name) {
        super(id, name);
    }

    public LinkedLearning(long id, String name, List<LinkedSymbol> symbols) {
        super(id, name);
        this.symbols = symbols;
    }

    public List<LinkedSymbol> getSymbols() {
        return this.symbols;
    }
}
