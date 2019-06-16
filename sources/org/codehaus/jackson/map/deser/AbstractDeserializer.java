package org.codehaus.jackson.map.deser;

import java.io.IOException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.type.JavaType;

public class AbstractDeserializer extends JsonDeserializer<Object> {
    protected final boolean _acceptBoolean;
    protected final boolean _acceptDouble;
    protected final boolean _acceptInt;
    protected final boolean _acceptString;
    protected final JavaType _baseType;

    /* renamed from: org.codehaus.jackson.map.deser.AbstractDeserializer$1 */
    static /* synthetic */ class C00221 {
        static final /* synthetic */ int[] $SwitchMap$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];

        static {
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_TRUE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_FALSE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public AbstractDeserializer(JavaType bt) {
        boolean z;
        boolean z2 = false;
        this._baseType = bt;
        Class<?> cls = bt.getRawClass();
        this._acceptString = cls.isAssignableFrom(String.class);
        if (cls == Boolean.TYPE || cls.isAssignableFrom(Boolean.class)) {
            z = true;
        } else {
            z = false;
        }
        this._acceptBoolean = z;
        if (cls == Integer.TYPE || cls.isAssignableFrom(Integer.class)) {
            z = true;
        } else {
            z = false;
        }
        this._acceptInt = z;
        if (cls == Double.TYPE || cls.isAssignableFrom(Double.class)) {
            z2 = true;
        }
        this._acceptDouble = z2;
    }

    public Object deserializeWithType(JsonParser jp, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        Object result = _deserializeIfNatural(jp, ctxt);
        return result != null ? result : typeDeserializer.deserializeTypedFromObject(jp, ctxt);
    }

    public Object deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        throw ctxt.instantiationException(this._baseType.getRawClass(), "abstract types can only be instantiated with additional type information");
    }

    protected Object _deserializeIfNatural(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        switch (C00221.$SwitchMap$org$codehaus$jackson$JsonToken[jp.getCurrentToken().ordinal()]) {
            case 1:
                if (this._acceptString) {
                    return jp.getText();
                }
                break;
            case 2:
                if (this._acceptInt) {
                    return Integer.valueOf(jp.getIntValue());
                }
                break;
            case 3:
                if (this._acceptDouble) {
                    return Double.valueOf(jp.getDoubleValue());
                }
                break;
            case 4:
                if (this._acceptBoolean) {
                    return Boolean.TRUE;
                }
                break;
            case 5:
                if (this._acceptBoolean) {
                    return Boolean.FALSE;
                }
                break;
        }
        return null;
    }
}
