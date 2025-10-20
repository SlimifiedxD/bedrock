package org.slimecraft.bedrock.menu;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.MenuType;
import org.slimecraft.bedrock.internal.MenuManager;
import org.slimecraft.bedrock.menu.anvil.AnvilMenuBuilder;
import org.slimecraft.bedrock.menu.anvil.OptionalAnvilBuilder;
import org.slimecraft.bedrock.menu.builder.NameBuilder;
import org.slimecraft.bedrock.menu.button.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Menu {
    private final MenuType type;
    private final Component name;
    private final List<Button> buttons = new ArrayList<>();
    private InventoryView view;

    protected Menu(MenuType type, Component name) {
        this.type = type;
        this.name = name;
    }

    public void show(Player player) {
        MenuManager.MENUS.put(player.getUniqueId(), this);
        view = type.create(player, name);
        player.openInventory(view);
    }

    public void addButton(Button button) {
        buttons.add(button);
    }

    public List<Button> getButtons() {
        return Collections.unmodifiableList(buttons);
    }

    public static NameBuilder<OptionalAnvilBuilder> anvil() {
        return new AnvilMenuBuilder();
    }

    public MenuType getType() {
        return type;
    }

    public InventoryView getView() {
        return view;
    }
}
