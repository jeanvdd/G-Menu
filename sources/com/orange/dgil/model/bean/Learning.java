package com.orange.dgil.model.bean;

import java.io.Serializable;

public class Learning implements Serializable {
    private static final long serialVersionUID = 1;
    private long id;
    private String name;

    public Learning(long id, String name) {
        this.id = id;
        this.name = name;
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
}
