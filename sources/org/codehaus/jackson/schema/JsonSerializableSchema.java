package org.codehaus.jackson.schema;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.codehaus.jackson.annotate.JacksonAnnotation;

@JacksonAnnotation
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonSerializableSchema {
    String schemaItemDefinition() default "##irrelevant";

    String schemaObjectPropertiesDefinition() default "##irrelevant";

    String schemaType() default "any";
}
