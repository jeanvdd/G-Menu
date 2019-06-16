package org.codehaus.jackson.map.deser.std;

import java.io.IOException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonParser.NumberType;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;

/* compiled from: JsonNodeDeserializer */
abstract class BaseNodeDeserializer<N extends JsonNode> extends StdDeserializer<N> {

    /* compiled from: JsonNodeDeserializer */
    /* renamed from: org.codehaus.jackson.map.deser.std.BaseNodeDeserializer$1 */
    static /* synthetic */ class C00241 {
        static final /* synthetic */ int[] $SwitchMap$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];

        static {
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.START_OBJECT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.START_ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 3;
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
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_TRUE.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_FALSE.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NULL.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
        }
    }

    public BaseNodeDeserializer(Class<N> nodeClass) {
        super((Class) nodeClass);
    }

    public Object deserializeWithType(JsonParser jp, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromAny(jp, ctxt);
    }

    protected void _reportProblem(JsonParser jp, String msg) throws JsonMappingException {
        throw new JsonMappingException(msg, jp.getTokenLocation());
    }

    protected void _handleDuplicateField(String fieldName, ObjectNode objectNode, JsonNode oldValue, JsonNode newValue) throws JsonProcessingException {
    }

    protected final ObjectNode deserializeObject(JsonParser jp, DeserializationContext ctxt, JsonNodeFactory nodeFactory) throws IOException, JsonProcessingException {
        ObjectNode node = nodeFactory.objectNode();
        JsonToken t = jp.getCurrentToken();
        if (t == JsonToken.START_OBJECT) {
            t = jp.nextToken();
        }
        while (t == JsonToken.FIELD_NAME) {
            JsonNode value;
            String fieldName = jp.getCurrentName();
            switch (C00241.$SwitchMap$org$codehaus$jackson$JsonToken[jp.nextToken().ordinal()]) {
                case 1:
                    value = deserializeObject(jp, ctxt, nodeFactory);
                    break;
                case 2:
                    value = deserializeArray(jp, ctxt, nodeFactory);
                    break;
                case 3:
                    value = nodeFactory.textNode(jp.getText());
                    break;
                default:
                    value = deserializeAny(jp, ctxt, nodeFactory);
                    break;
            }
            JsonNode old = node.put(fieldName, value);
            if (old != null) {
                _handleDuplicateField(fieldName, node, old, value);
            }
            t = jp.nextToken();
        }
        return node;
    }

    protected final ArrayNode deserializeArray(JsonParser jp, DeserializationContext ctxt, JsonNodeFactory nodeFactory) throws IOException, JsonProcessingException {
        ArrayNode node = nodeFactory.arrayNode();
        while (true) {
            switch (C00241.$SwitchMap$org$codehaus$jackson$JsonToken[jp.nextToken().ordinal()]) {
                case 1:
                    node.add(deserializeObject(jp, ctxt, nodeFactory));
                    break;
                case 2:
                    node.add(deserializeArray(jp, ctxt, nodeFactory));
                    break;
                case 3:
                    node.add(nodeFactory.textNode(jp.getText()));
                    break;
                case 4:
                    return node;
                default:
                    node.add(deserializeAny(jp, ctxt, nodeFactory));
                    break;
            }
        }
    }

    protected final JsonNode deserializeAny(JsonParser jp, DeserializationContext ctxt, JsonNodeFactory nodeFactory) throws IOException, JsonProcessingException {
        switch (C00241.$SwitchMap$org$codehaus$jackson$JsonToken[jp.getCurrentToken().ordinal()]) {
            case 1:
                return deserializeObject(jp, ctxt, nodeFactory);
            case 2:
                return deserializeArray(jp, ctxt, nodeFactory);
            case 3:
                return nodeFactory.textNode(jp.getText());
            case 5:
                return deserializeObject(jp, ctxt, nodeFactory);
            case 6:
                Object ob = jp.getEmbeddedObject();
                if (ob == null) {
                    return nodeFactory.nullNode();
                }
                if (ob.getClass() == byte[].class) {
                    return nodeFactory.binaryNode((byte[]) ob);
                }
                return nodeFactory.POJONode(ob);
            case 7:
                NumberType nt = jp.getNumberType();
                if (nt == NumberType.BIG_INTEGER || ctxt.isEnabled(Feature.USE_BIG_INTEGER_FOR_INTS)) {
                    return nodeFactory.numberNode(jp.getBigIntegerValue());
                }
                if (nt == NumberType.INT) {
                    return nodeFactory.numberNode(jp.getIntValue());
                }
                return nodeFactory.numberNode(jp.getLongValue());
            case 8:
                if (jp.getNumberType() == NumberType.BIG_DECIMAL || ctxt.isEnabled(Feature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return nodeFactory.numberNode(jp.getDecimalValue());
                }
                return nodeFactory.numberNode(jp.getDoubleValue());
            case 9:
                return nodeFactory.booleanNode(true);
            case 10:
                return nodeFactory.booleanNode(false);
            case 11:
                return nodeFactory.nullNode();
            default:
                throw ctxt.mappingException(getValueClass());
        }
    }
}
