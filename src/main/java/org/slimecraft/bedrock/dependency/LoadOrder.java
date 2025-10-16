package org.slimecraft.bedrock.dependency;

/**
 * Represents the order in which a dependency is loaded.
 */
public enum LoadOrder {
    /**
     * The dependency will be loaded before your plugin.
     */
    BEFORE,
    /**
     * The dependency will be loaded after your plugin.
     */
    AFTER,
    /**
     * The dependency will have undefined ordering behaviour; it may run before or after.
     */
    OMIT
}
