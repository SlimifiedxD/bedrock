package org.slimecraft.bedrock.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface PluginConfig {
    String apiVersion() default "1.21";
    String name();
    String version() default "1.0-SNAPSHOT";
}
