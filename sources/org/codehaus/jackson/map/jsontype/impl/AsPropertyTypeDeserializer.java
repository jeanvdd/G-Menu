package org.codehaus.jackson.map.jsontype.impl;

import java.io.IOException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.jsontype.TypeIdResolver;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.util.JsonParserSequence;
import org.codehaus.jackson.util.TokenBuffer;

public class AsPropertyTypeDeserializer extends AsArrayTypeDeserializer {
    protected final String _typePropertyName;

    /* renamed from: org.codehaus.jackson.map.jsontype.impl.AsPropertyTypeDeserializer$1 */
    static /* synthetic */ class C00311 {
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

    @Deprecated
    public AsPropertyTypeDeserializer(JavaType bt, TypeIdResolver idRes, BeanProperty property, String typePropName) {
        this(bt, idRes, property, null, typePropName);
    }

    public AsPropertyTypeDeserializer(JavaType bt, TypeIdResolver idRes, BeanProperty property, Class<?> defaultImpl, String typePropName) {
        super(bt, idRes, property, defaultImpl);
        this._typePropertyName = typePropName;
    }

    public As getTypeInclusion() {
        return As.PROPERTY;
    }

    public String getPropertyName() {
        return this._typePropertyName;
    }

    public Object deserializeTypedFromObject(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonToken t = jp.getCurrentToken();
        if (t == JsonToken.START_OBJECT) {
            t = jp.nextToken();
        } else if (t == JsonToken.START_ARRAY) {
            return _deserializeTypedUsingDefaultImpl(jp, ctxt, null);
        } else {
            if (t != JsonToken.FIELD_NAME) {
                return _deserializeTypedUsingDefaultImpl(jp, ctxt, null);
            }
        }
        TokenBuffer tb = null;
        while (t == JsonToken.FIELD_NAME) {
            String name = jp.getCurrentName();
            jp.nextToken();
            if (this._typePropertyName.equals(name)) {
                JsonDeserializer<Object> deser = _findDeserializer(ctxt, jp.getText());
                if (tb != null) {
                    jp = JsonParserSequence.createFlattened(tb.asParser(jp), jp);
                }
                jp.nextToken();
                return deser.deserialize(jp, ctxt);
            }
            if (tb == null) {
                tb = new TokenBuffer(null);
            }
            tb.writeFieldName(name);
            tb.copyCurrentStructure(jp);
            t = jp.nextToken();
        }
        return _deserializeTypedUsingDefaultImpl(jp, ctxt, tb);
    }

    protected Object _deserializeTypedUsingDefaultImpl(JsonParser jp, DeserializationContext ctxt, TokenBuffer tb) throws IOException, JsonProcessingException {
        if (this._defaultImpl != null) {
            JsonDeserializer<Object> deser = _findDefaultImplDeserializer(ctxt);
            if (tb != null) {
                tb.writeEndObject();
                jp = tb.asParser(jp);
                jp.nextToken();
            }
            return deser.deserialize(jp, ctxt);
        }
        Object result = _deserializeIfNatural(jp, ctxt);
        if (result != null) {
            return result;
        }
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            return super.deserializeTypedFromAny(jp, ctxt);
        }
        throw ctxt.wrongTokenException(jp, JsonToken.FIELD_NAME, "missing property '" + this._typePropertyName + "' that is to contain type id  (for class " + baseTypeName() + ")");
    }

    public Object deserializeTypedFromAny(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            return super.deserializeTypedFromArray(jp, ctxt);
        }
        return deserializeTypedFromObject(jp, ctxt);
    }

    protected Object _deserializeIfNatural(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        switch (C00311.$SwitchMap$org$codehaus$jackson$JsonToken[jp.getCurrentToken().ordinal()]) {
            case 1:
                if (this._baseType.getRawClass().isAssignableFrom(String.class)) {
                    return jp.getText();
                }
                break;
            case 2:
                if (this._baseType.getRawClass().isAssignableFrom(Integer.class)) {
                    return Integer.valueOf(jp.getIntValue());
                }
                break;
            case 3:
                if (this._baseType.getRawClass().isAssignableFrom(Double.class)) {
                    return Double.valueOf(jp.getDoubleValue());
                }
                break;
            case 4:
                if (this._baseType.getRawClass().isAssignableFrom(Boolean.class)) {
                    return Boolean.TRUE;
                }
                break;
            case 5:
                if (this._baseType.getRawClass().isAssignableFrom(Boolean.class)) {
                    return Boolean.FALSE;
                }
                break;
        }
        return null;
    }
}
