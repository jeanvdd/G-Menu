package com.orange.dgil.conf;

public class StringField extends Field {
    private final String def;

    public StringField(IFieldGetterSetter nativeInterface, String def, String name, int id) {
        super(nativeInterface, name, id);
        this.def = def;
    }

    public String get() {
        return getNativeInterface().getString(getId());
    }

    public void set(String val) {
        getNativeInterface().setString(getId(), val);
    }

    public String getDef() {
        return this.def;
    }
}
