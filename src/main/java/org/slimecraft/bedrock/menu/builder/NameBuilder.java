package org.slimecraft.bedrock.menu.builder;

import net.kyori.adventure.text.Component;

public interface NameBuilder<T extends OptionalBuilder<?, ?>> {
    T name(Component name);
}
