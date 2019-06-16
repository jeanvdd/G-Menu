package org.codehaus.jackson.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JacksonAnnotation
@Target({ElementType.METHOD})
@Deprecated
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonGetter {
    String value() default "";
}
