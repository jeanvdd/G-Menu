package com.orange.dgil.conf;

public class BooleanField extends Field {
    private final boolean def;

    public BooleanField(IFieldGetterSetter nativeInterface, boolean def, String name, int id) {
        super(nativeInterface, name, id);
        this.def = def;
    }

    public boolean get() {
        return getNativeInterface().getBoolean(getId());
    }

    public void set(boolean val) {
        getNativeInterface().setBoolean(getId(), val);
    }

    public boolean getDef() {
        return this.def;
    }
}
