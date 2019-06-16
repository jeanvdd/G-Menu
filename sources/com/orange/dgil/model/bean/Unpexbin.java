package com.orange.dgil.model.bean;

import com.orange.dgil.conf.MSHSymbolClasses.MSHSymbolClass;
import java.io.Serializable;
import java.util.Arrays;

public class Unpexbin implements Serializable {
    private static final long serialVersionUID = 1;
    private byte[] data;
    private long id;
    private MSHSymbolClass mshClass;
    private String symbolName;

    public Unpexbin(long id, String symbolName, MSHSymbolClass mshClass, byte[] data) {
        this.id = id;
        this.symbolName = symbolName;
        this.mshClass = mshClass;
        this.data = Arrays.copyOf(data, data.length);
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSymbolName() {
        return this.symbolName;
    }

    public void setSymbolName(String symbolName) {
        this.symbolName = symbolName;
    }

    public MSHSymbolClass getMshClass() {
        return this.mshClass;
    }

    public void setMshClass(MSHSymbolClass mshClass) {
        this.mshClass = mshClass;
    }

    public byte[] getData() {
        return Arrays.copyOf(this.data, this.data.length);
    }

    public void setData(byte[] data) {
        this.data = Arrays.copyOf(data, data.length);
    }
}
