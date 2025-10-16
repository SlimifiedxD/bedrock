package org.slimecraft.bedrock.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotate a class that extends {@link org.bukkit.plugin.java.JavaPlugin}; this will automatically generate all
 * boilerplate and will bootstrap Bedrock.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface PluginConfig {
    String apiVersion() default "1.21";
    String name();
    String description() default "";
    String version() default "1.0-SNAPSHOT";
    String bootstrapper() default "";
    String loader() default "";
    Dependency[] dependencies() default {};
}
