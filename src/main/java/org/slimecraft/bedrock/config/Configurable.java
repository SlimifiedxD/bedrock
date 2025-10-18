package org.slimecraft.bedrock.config;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface Configurable<T extends Configurable<T>> {
    @NotNull Map<String, Object> toConfig();

    @NotNull T fromConfig(Map<String, Object> representation);
}
