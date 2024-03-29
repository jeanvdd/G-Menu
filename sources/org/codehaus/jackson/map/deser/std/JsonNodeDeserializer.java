package org.codehaus.jackson.map.deser.std;

import java.io.IOException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

public class JsonNodeDeserializer extends BaseNodeDeserializer<JsonNode> {
    private static final JsonNodeDeserializer instance = new JsonNodeDeserializer();

    /* renamed from: org.codehaus.jackson.map.deser.std.JsonNodeDeserializer$1 */
    static /* synthetic */ class C00251 {
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
        }
    }

    static final class ArrayDeserializer extends BaseNodeDeserializer<ArrayNode> {
        protected static final ArrayDeserializer _instance = new ArrayDeserializer();

        protected ArrayDeserializer() {
            super(ArrayNode.class);
        }

        public static ArrayDeserializer getInstance() {
            return _instance;
        }

        public ArrayNode deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            if (jp.isExpectedStartArrayToken()) {
                return deserializeArray(jp, ctxt, ctxt.getNodeFactory());
            }
            throw ctxt.mappingException(ArrayNode.class);
        }
    }

    static final class ObjectDeserializer extends BaseNodeDeserializer<ObjectNode> {
        protected static final ObjectDeserializer _instance = new ObjectDeserializer();

        protected ObjectDeserializer() {
            super(ObjectNode.class);
        }

        public static ObjectDeserializer getInstance() {
            return _instance;
        }

        public ObjectNode deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            if (jp.getCurrentToken() == JsonToken.START_OBJECT) {
                jp.nextToken();
                return deserializeObject(jp, ctxt, ctxt.getNodeFactory());
            } else if (jp.getCurrentToken() == JsonToken.FIELD_NAME) {
                return deserializeObject(jp, ctxt, ctxt.getNodeFactory());
            } else {
                throw ctxt.mappingException(ObjectNode.class);
            }
        }
    }

    public /* bridge */ /* synthetic */ Object deserializeWithType(JsonParser x0, DeserializationContext x1, TypeDeserializer x2) throws IOException, JsonProcessingException {
        return super.deserializeWithType(x0, x1, x2);
    }

    protected JsonNodeDeserializer() {
        super(JsonNode.class);
    }

    public static JsonDeserializer<? extends JsonNode> getDeserializer(Class<?> nodeClass) {
        if (nodeClass == ObjectNode.class) {
            return ObjectDeserializer.getInstance();
        }
        if (nodeClass == ArrayNode.class) {
            return ArrayDeserializer.getInstance();
        }
        return instance;
    }

    public JsonNode deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        switch (C00251.$SwitchMap$org$codehaus$jackson$JsonToken[jp.getCurrentToken().ordinal()]) {
            case 1:
                return deserializeObject(jp, ctxt, ctxt.getNodeFactory());
            case 2:
                return deserializeArray(jp, ctxt, ctxt.getNodeFactory());
            default:
                return deserializeAny(jp, ctxt, ctxt.getNodeFactory());
        }
    }
}
