package org.slimecraft.bedrock.dependency;

public enum LoadStage {
    /**
     * Dependencies that are used in the boostrap phase.
     */
    BOOTSTRAP,
    /**
     * Dependencies that are used for the core functionality of your plugin, used whilst the server is running.
     * This should be used unless you know that you need a dependency in the bootstrap phase.
     */
    SERVER
}
