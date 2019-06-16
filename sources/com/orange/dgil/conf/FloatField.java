package com.orange.dgil.conf;

public class FloatField extends Field {
    private final float def;

    public FloatField(IFieldGetterSetter nativeInterface, float def, String name, int id) {
        super(nativeInterface, name, id);
        this.def = def;
    }

    public float get() {
        return getNativeInterface().getFloat(getId());
    }

    public void set(float val) {
        getNativeInterface().setFloat(getId(), val);
    }

    public float getDef() {
        return this.def;
    }
}
