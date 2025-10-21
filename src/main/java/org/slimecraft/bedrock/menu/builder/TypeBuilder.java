package org.slimecraft.bedrock.menu.builder;

import org.bukkit.inventory.MenuType;

public interface TypeBuilder<N extends NameBuilder<?>, T extends MenuType> {
    N type(T type);
}
