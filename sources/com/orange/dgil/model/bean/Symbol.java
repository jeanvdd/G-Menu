package com.orange.dgil.model.bean;

import com.orange.dgil.conf.MSHSymbolClasses.MSHSymbolClass;
import java.io.Serializable;

public class Symbol implements Serializable {
    private static final long serialVersionUID = 1;
    private long id;
    private MSHSymbolClass mshClass;
    private String name;

    public Symbol(long id, String name, MSHSymbolClass mshClass) {
        this.id = id;
        this.name = name;
        this.mshClass = mshClass;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MSHSymbolClass getMshClass() {
        return this.mshClass;
    }

    public void setMshClass(MSHSymbolClass mshClass) {
        this.mshClass = mshClass;
    }
}
