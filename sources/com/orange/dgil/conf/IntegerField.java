package com.orange.dgil.conf;

public class IntegerField extends Field {
    private final int def;

    public IntegerField(IFieldGetterSetter nativeInterface, int def, String name, int id) {
        super(nativeInterface, name, id);
        this.def = def;
    }

    public int get() {
        return getNativeInterface().getInteger(getId());
    }

    public void set(int val) {
        getNativeInterface().setInteger(getId(), val);
    }

    public int getDef() {
        return this.def;
    }
}
