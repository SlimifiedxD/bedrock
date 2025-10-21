package org.slimecraft.bedrock.menu.inventory;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.MenuType;
import org.bukkit.inventory.view.builder.InventoryViewBuilder;
import org.jetbrains.annotations.NotNull;
import org.slimecraft.bedrock.menu.builder.NameBuilder;
import org.slimecraft.bedrock.menu.builder.OptionalBuilder;
import org.slimecraft.bedrock.menu.builder.TypeBuilder;
import org.slimecraft.bedrock.menu.button.Button;

public class InventoryMenuBuilder<T extends MenuType.Typed<@NotNull InventoryView, ? extends @NotNull InventoryViewBuilder<@NotNull InventoryView>>>
        implements
        TypeBuilder<InventoryMenuBuilder<T>, T>,
        NameBuilder<InventoryMenuBuilder<T>>,
        OptionalBuilder<InventoryMenuBuilder<T>, InventoryMenu> {
    private InventoryMenu menu;
    private T type;

    @Override
    public InventoryMenuBuilder<T> type(T type) {
        this.type = type;
        return this;
    }

    @Override
    public InventoryMenuBuilder<T> name(Component name) {
        this.menu = new InventoryMenu(type, name);
        return this;
    }

    @Override
    public InventoryMenuBuilder<T> button(Button button) {
        menu.addButton(button);
        return this;
    }

    @Override
    public InventoryMenuBuilder<T> button(org.slimecraft.bedrock.menu.button.builder.OptionalBuilder builder) {
        button(builder.build());
        return this;
    }

    @Override
    public InventoryMenuBuilder<T> notCloseable() {
        menu.setCloseable(false);
        return this;
    }

    @Override
    public InventoryMenu build() {
        return menu;
    }
}
