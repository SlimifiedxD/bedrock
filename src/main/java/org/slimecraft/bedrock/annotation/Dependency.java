package org.slimecraft.bedrock.annotation;

import org.slimecraft.bedrock.dependency.LoadOrder;
import org.slimecraft.bedrock.dependency.LoadStage;

/**
 * Marks a dependency for a plugin.
 */
public @interface Dependency {
    /**
     * The name of the dependency.
     */
    String name();

    /**
     * The {@link LoadStage} in which a dependency should be loaded.
     */
    LoadStage loadStage();

    /**
     * The {@link LoadOrder} of when the dependency should be loaded.
     */
    LoadOrder loadOrder();

    /**
     * If the dependency is required for the plugin to function.
     */
    boolean required() default false;

    /**
     * Whether the dependency's classpath should be joined.
     */
    boolean joinClasspath() default true;
}
