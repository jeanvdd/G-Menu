package org.codehaus.jackson.map.deser.std;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.map.util.ObjectBuffer;

@JacksonStdImpl
public class UntypedObjectDeserializer extends StdDeserializer<Object> {
    private static final Object[] NO_OBJECTS = new Object[0];

    /* renamed from: org.codehaus.jackson.map.deser.std.UntypedObjectDeserializer$1 */
    static /* synthetic */ class C00271 {
        static final /* synthetic */ int[] $SwitchMap$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];

        static {
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.START_OBJECT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.END_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.START_ARRAY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.END_ARRAY.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_EMBEDDED_OBJECT.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_TRUE.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_FALSE.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NULL.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
        }
    }

    public UntypedObjectDeserializer() {
        super(Object.class);
    }

    public Object deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        switch (C00271.$SwitchMap$org$codehaus$jackson$JsonToken[jp.getCurrentToken().ordinal()]) {
            case 1:
                return mapObject(jp, ctxt);
            case 3:
                return mapArray(jp, ctxt);
            case 5:
                return mapObject(jp, ctxt);
            case 6:
                return jp.getEmbeddedObject();
            case 7:
                return jp.getText();
            case 8:
                if (ctxt.isEnabled(Feature.USE_BIG_INTEGER_FOR_INTS)) {
                    return jp.getBigIntegerValue();
                }
                return jp.getNumberValue();
            case 9:
                if (ctxt.isEnabled(Feature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return jp.getDecimalValue();
                }
                return Double.valueOf(jp.getDoubleValue());
            case 10:
                return Boolean.TRUE;
            case 11:
                return Boolean.FALSE;
            case 12:
                return null;
            default:
                throw ctxt.mappingException(Object.class);
        }
    }

    public Object deserializeWithType(JsonParser jp, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        switch (C00271.$SwitchMap$org$codehaus$jackson$JsonToken[jp.getCurrentToken().ordinal()]) {
            case 1:
            case 3:
            case 5:
                return typeDeserializer.deserializeTypedFromAny(jp, ctxt);
            case 6:
                return jp.getEmbeddedObject();
            case 7:
                return jp.getText();
            case 8:
                if (ctxt.isEnabled(Feature.USE_BIG_INTEGER_FOR_INTS)) {
                    return jp.getBigIntegerValue();
                }
                return Integer.valueOf(jp.getIntValue());
            case 9:
                if (ctxt.isEnabled(Feature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return jp.getDecimalValue();
                }
                return Double.valueOf(jp.getDoubleValue());
            case 10:
                return Boolean.TRUE;
            case 11:
                return Boolean.FALSE;
            case 12:
                return null;
            default:
                throw ctxt.mappingException(Object.class);
        }
    }

    protected Object mapArray(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        if (ctxt.isEnabled(Feature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
            return mapArrayToArray(jp, ctxt);
        }
        if (jp.nextToken() == JsonToken.END_ARRAY) {
            return new ArrayList(4);
        }
        ObjectBuffer buffer = ctxt.leaseObjectBuffer();
        Object[] values = buffer.resetAndStart();
        int ptr = 0;
        int totalSize = 0;
        while (true) {
            Object value = deserialize(jp, ctxt);
            totalSize++;
            if (ptr >= values.length) {
                values = buffer.appendCompletedChunk(values);
                ptr = 0;
            }
            int ptr2 = ptr + 1;
            values[ptr] = value;
            if (jp.nextToken() == JsonToken.END_ARRAY) {
                List result = new ArrayList(((totalSize >> 3) + totalSize) + 1);
                buffer.completeAndClearBuffer(values, ptr2, result);
                return result;
            }
            ptr = ptr2;
        }
    }

    protected Object mapObject(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonToken t = jp.getCurrentToken();
        if (t == JsonToken.START_OBJECT) {
            t = jp.nextToken();
        }
        if (t != JsonToken.FIELD_NAME) {
            return new LinkedHashMap(4);
        }
        String field1 = jp.getText();
        jp.nextToken();
        Object value1 = deserialize(jp, ctxt);
        if (jp.nextToken() != JsonToken.FIELD_NAME) {
            Object result = new LinkedHashMap(4);
            result.put(field1, value1);
            return result;
        }
        String field2 = jp.getText();
        jp.nextToken();
        Object value2 = deserialize(jp, ctxt);
        if (jp.nextToken() != JsonToken.FIELD_NAME) {
            result = new LinkedHashMap(4);
            result.put(field1, value1);
            result.put(field2, value2);
            return result;
        }
        result = new LinkedHashMap();
        result.put(field1, value1);
        result.put(field2, value2);
        do {
            String fieldName = jp.getText();
            jp.nextToken();
            result.put(fieldName, deserialize(jp, ctxt));
        } while (jp.nextToken() != JsonToken.END_OBJECT);
        return result;
    }

    protected Object[] mapArrayToArray(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        if (jp.nextToken() == JsonToken.END_ARRAY) {
            return NO_OBJECTS;
        }
        ObjectBuffer buffer = ctxt.leaseObjectBuffer();
        Object[] values = buffer.resetAndStart();
        int ptr = 0;
        while (true) {
            Object value = deserialize(jp, ctxt);
            if (ptr >= values.length) {
                values = buffer.appendCompletedChunk(values);
                ptr = 0;
            }
            int ptr2 = ptr + 1;
            values[ptr] = value;
            if (jp.nextToken() == JsonToken.END_ARRAY) {
                return buffer.completeAndClearBuffer(values, ptr2);
            }
            ptr = ptr2;
        }
    }
}
