package org.codehaus.jackson.node;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser.NumberType;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.SerializerProvider;

public final class DecimalNode extends NumericNode {
    protected final BigDecimal _value;

    public DecimalNode(BigDecimal v) {
        this._value = v;
    }

    public static DecimalNode valueOf(BigDecimal d) {
        return new DecimalNode(d);
    }

    public JsonToken asToken() {
        return JsonToken.VALUE_NUMBER_FLOAT;
    }

    public NumberType getNumberType() {
        return NumberType.BIG_DECIMAL;
    }

    public boolean isFloatingPointNumber() {
        return true;
    }

    public boolean isBigDecimal() {
        return true;
    }

    public Number getNumberValue() {
        return this._value;
    }

    public int getIntValue() {
        return this._value.intValue();
    }

    public long getLongValue() {
        return this._value.longValue();
    }

    public BigInteger getBigIntegerValue() {
        return this._value.toBigInteger();
    }

    public double getDoubleValue() {
        return this._value.doubleValue();
    }

    public BigDecimal getDecimalValue() {
        return this._value;
    }

    public String asText() {
        return this._value.toString();
    }

    public final void serialize(JsonGenerator jg, SerializerProvider provider) throws IOException, JsonProcessingException {
        jg.writeNumber(this._value);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        return ((DecimalNode) o)._value.equals(this._value);
    }

    public int hashCode() {
        return this._value.hashCode();
    }
}
