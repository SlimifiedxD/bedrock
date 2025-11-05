package org.slimecraft.bedrock.annotation.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents a file-extension agnostic configuration value.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigurationValue {
    /**
     * Separate keys with dot notation. For example,<br>
     * "foo"<br>
     * "foo.bar"
     * @return The path of the configuration value.
     */
    String value();
}
