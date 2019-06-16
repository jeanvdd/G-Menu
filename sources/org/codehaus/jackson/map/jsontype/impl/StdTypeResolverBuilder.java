package org.codehaus.jackson.map.jsontype.impl;

import java.util.Collection;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.MapperConfig;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.jsontype.NamedType;
import org.codehaus.jackson.map.jsontype.TypeIdResolver;
import org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import org.codehaus.jackson.type.JavaType;

public class StdTypeResolverBuilder implements TypeResolverBuilder<StdTypeResolverBuilder> {
    protected TypeIdResolver _customIdResolver;
    protected Class<?> _defaultImpl;
    protected Id _idType;
    protected As _includeAs;
    protected String _typeProperty;

    /* renamed from: org.codehaus.jackson.map.jsontype.impl.StdTypeResolverBuilder$1 */
    static /* synthetic */ class C00321 {
        static final /* synthetic */ int[] $SwitchMap$org$codehaus$jackson$annotate$JsonTypeInfo$As = new int[As.values().length];
        static final /* synthetic */ int[] $SwitchMap$org$codehaus$jackson$annotate$JsonTypeInfo$Id = new int[Id.values().length];

        static {
            try {
                $SwitchMap$org$codehaus$jackson$annotate$JsonTypeInfo$Id[Id.CLASS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$annotate$JsonTypeInfo$Id[Id.MINIMAL_CLASS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$annotate$JsonTypeInfo$Id[Id.NAME.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$annotate$JsonTypeInfo$Id[Id.NONE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$annotate$JsonTypeInfo$Id[Id.CUSTOM.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$annotate$JsonTypeInfo$As[As.WRAPPER_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$annotate$JsonTypeInfo$As[As.PROPERTY.ordinal()] = 2;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$annotate$JsonTypeInfo$As[As.WRAPPER_OBJECT.ordinal()] = 3;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$annotate$JsonTypeInfo$As[As.EXTERNAL_PROPERTY.ordinal()] = 4;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    public Class<?> getDefaultImpl() {
        return this._defaultImpl;
    }

    public static StdTypeResolverBuilder noTypeInfoBuilder() {
        return new StdTypeResolverBuilder().init(Id.NONE, null);
    }

    public StdTypeResolverBuilder init(Id idType, TypeIdResolver idRes) {
        if (idType == null) {
            throw new IllegalArgumentException("idType can not be null");
        }
        this._idType = idType;
        this._customIdResolver = idRes;
        this._typeProperty = idType.getDefaultPropertyName();
        return this;
    }

    public TypeSerializer buildTypeSerializer(SerializationConfig config, JavaType baseType, Collection<NamedType> subtypes, BeanProperty property) {
        if (this._idType == Id.NONE) {
            return null;
        }
        TypeIdResolver idRes = idResolver(config, baseType, subtypes, true, false);
        switch (C00321.$SwitchMap$org$codehaus$jackson$annotate$JsonTypeInfo$As[this._includeAs.ordinal()]) {
            case 1:
                return new AsArrayTypeSerializer(idRes, property);
            case 2:
                return new AsPropertyTypeSerializer(idRes, property, this._typeProperty);
            case 3:
                return new AsWrapperTypeSerializer(idRes, property);
            case 4:
                return new AsExternalTypeSerializer(idRes, property, this._typeProperty);
            default:
                throw new IllegalStateException("Do not know how to construct standard type serializer for inclusion type: " + this._includeAs);
        }
    }

    public TypeDeserializer buildTypeDeserializer(DeserializationConfig config, JavaType baseType, Collection<NamedType> subtypes, BeanProperty property) {
        if (this._idType == Id.NONE) {
            return null;
        }
        TypeIdResolver idRes = idResolver(config, baseType, subtypes, false, true);
        switch (C00321.$SwitchMap$org$codehaus$jackson$annotate$JsonTypeInfo$As[this._includeAs.ordinal()]) {
            case 1:
                return new AsArrayTypeDeserializer(baseType, idRes, property, this._defaultImpl);
            case 2:
                return new AsPropertyTypeDeserializer(baseType, idRes, property, this._defaultImpl, this._typeProperty);
            case 3:
                return new AsWrapperTypeDeserializer(baseType, idRes, property, this._defaultImpl);
            case 4:
                return new AsExternalTypeDeserializer(baseType, idRes, property, this._defaultImpl, this._typeProperty);
            default:
                throw new IllegalStateException("Do not know how to construct standard type serializer for inclusion type: " + this._includeAs);
        }
    }

    public StdTypeResolverBuilder inclusion(As includeAs) {
        if (includeAs == null) {
            throw new IllegalArgumentException("includeAs can not be null");
        }
        this._includeAs = includeAs;
        return this;
    }

    public StdTypeResolverBuilder typeProperty(String typeIdPropName) {
        if (typeIdPropName == null || typeIdPropName.length() == 0) {
            typeIdPropName = this._idType.getDefaultPropertyName();
        }
        this._typeProperty = typeIdPropName;
        return this;
    }

    public StdTypeResolverBuilder defaultImpl(Class<?> defaultImpl) {
        this._defaultImpl = defaultImpl;
        return this;
    }

    public String getTypeProperty() {
        return this._typeProperty;
    }

    protected TypeIdResolver idResolver(MapperConfig<?> config, JavaType baseType, Collection<NamedType> subtypes, boolean forSer, boolean forDeser) {
        if (this._customIdResolver != null) {
            return this._customIdResolver;
        }
        if (this._idType == null) {
            throw new IllegalStateException("Can not build, 'init()' not yet called");
        }
        switch (C00321.$SwitchMap$org$codehaus$jackson$annotate$JsonTypeInfo$Id[this._idType.ordinal()]) {
            case 1:
                return new ClassNameIdResolver(baseType, config.getTypeFactory());
            case 2:
                return new MinimalClassNameIdResolver(baseType, config.getTypeFactory());
            case 3:
                return TypeNameIdResolver.construct(config, baseType, subtypes, forSer, forDeser);
            case 4:
                return null;
            default:
                throw new IllegalStateException("Do not know how to construct standard type id resolver for idType: " + this._idType);
        }
    }
}
