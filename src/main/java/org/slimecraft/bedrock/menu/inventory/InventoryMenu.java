package org.slimecraft.bedrock.menu.inventory;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.MenuType;
import org.bukkit.inventory.view.builder.InventoryViewBuilder;
import org.jetbrains.annotations.NotNull;
import org.slimecraft.bedrock.menu.Menu;

public class InventoryMenu extends Menu {
    public <T extends MenuType.Typed<@NotNull InventoryView, ? extends @NotNull InventoryViewBuilder<@NotNull InventoryView>>>
    InventoryMenu(T type, Component name) {
        super(type, name);
    }
}
