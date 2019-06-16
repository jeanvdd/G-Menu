package org.codehaus.jackson.map.ser;

import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.map.util.EnumValues;

@JacksonStdImpl
@Deprecated
public class EnumSerializer extends org.codehaus.jackson.map.ser.std.EnumSerializer {
    public EnumSerializer(EnumValues v) {
        super(v);
    }
}
