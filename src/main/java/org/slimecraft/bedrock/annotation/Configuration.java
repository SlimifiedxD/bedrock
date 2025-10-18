package org.slimecraft.bedrock.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that this type will be used for configuration. Configuration should always be stateless;
 * therefore, the type annotated must have a no-args constructor. There should never be dependencies
 * for configuration.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Configuration {
    /**
     * The path to where this config should be read and written to, along with its name, with no file extension. For example,<br>
     * "config"<br>
     * "configs/messages"
     */
    String value();
}
