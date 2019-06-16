package org.codehaus.jackson.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JacksonAnnotation
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonIgnoreType {
    boolean value() default true;
}
