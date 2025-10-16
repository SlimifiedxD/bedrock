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
    /**
     * The Paper API version of the plugin.
     */
    String apiVersion() default "1.21";

    /**
     * The name of the plugin, used for end users and {@link Dependency}s.
     */
    String name();

    /**
     * The description of the plugin.
     */
    String description() default "";

    /**
     * The version of the plugin.
     */
    String version() default "1.0-SNAPSHOT";

    /**
     * The {@link io.papermc.paper.plugin.bootstrap.PluginBootstrap} of the plugin.
     */
    String bootstrapper() default "";

    /**
     * The {@link io.papermc.paper.plugin.loader.PluginLoader} of the plugin.
     */
    String loader() default "";

    /**
     * The dependencies of the plugin.
     */
    Dependency[] dependencies() default {};
}
