package com.orange.dgil.model.bean.linked;

import com.orange.dgil.conf.MSHSymbolClasses.MSHSymbolClass;
import com.orange.dgil.model.bean.Model;
import com.orange.dgil.model.bean.Symbol;
import java.util.ArrayList;
import java.util.List;

public class LinkedSymbol extends Symbol {
    private static final long serialVersionUID = -4232641064157246669L;
    private List<Model> traces = new ArrayList();

    public LinkedSymbol(long id, String name, MSHSymbolClass mshClass) {
        super(id, name, mshClass);
    }

    public LinkedSymbol(long id, String name, MSHSymbolClass mshClass, List<Model> models) {
        super(id, name, mshClass);
        this.traces = models;
    }

    public List<Model> getModels() {
        return this.traces;
    }

    public List<Model> getTraces() {
        return this.traces;
    }

    public void setTraces(List<Model> traces) {
        this.traces = traces;
    }
}
