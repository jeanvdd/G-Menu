package com.orange.dgil.conf;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private final int id;
    private final String name;
    private final IFieldGetterSetter nativeInterface;

    public Field(IFieldGetterSetter nativeInterface, String name, int id) {
        this.name = name;
        this.id = id;
        this.nativeInterface = nativeInterface;
    }

    static void set(List<Field> fields, String name, String val) {
        for (Field f : fields) {
            if (f.name.equals(name)) {
                if (f.getClass().equals(BooleanField.class)) {
                    f.nativeInterface.setBoolean(f.id, val.toLowerCase().equals("true"));
                } else if (f.getClass().equals(IntegerField.class)) {
                    f.nativeInterface.setInteger(f.id, Integer.parseInt(val));
                } else if (f.getClass().equals(FloatField.class)) {
                    f.nativeInterface.setFloat(f.id, Float.parseFloat(val));
                } else {
                    f.nativeInterface.setString(f.id, val);
                }
            }
        }
    }

    static List<BooleanField> getBooleanFields(List<Field> fields) {
        return getFields(fields, BooleanField.class);
    }

    static List<IntegerField> getIntegerFields(List<Field> fields) {
        return getFields(fields, IntegerField.class);
    }

    static List<FloatField> getFloatFields(List<Field> fields) {
        return getFields(fields, FloatField.class);
    }

    static List<StringField> getStringFields(List<Field> fields) {
        return getFields(fields, StringField.class);
    }

    private static <T extends Field> List<T> getFields(List<Field> fields, Class<T> clazz) {
        List<T> typedFields = new ArrayList();
        for (Field f : fields) {
            if (f.getClass().equals(clazz)) {
                typedFields.add(f);
            }
        }
        return typedFields;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public IFieldGetterSetter getNativeInterface() {
        return this.nativeInterface;
    }
}
