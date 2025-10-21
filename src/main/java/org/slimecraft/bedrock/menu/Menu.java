package org.slimecraft.bedrock.menu;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.MenuType;
import org.bukkit.inventory.view.builder.InventoryViewBuilder;
import org.jetbrains.annotations.NotNull;
import org.slimecraft.bedrock.internal.MenuManager;
import org.slimecraft.bedrock.menu.anvil.AnvilMenuBuilder;
import org.slimecraft.bedrock.menu.anvil.OptionalAnvilBuilder;
import org.slimecraft.bedrock.menu.builder.NameBuilder;
import org.slimecraft.bedrock.menu.button.Button;
import org.slimecraft.bedrock.menu.inventory.InventoryMenuBuilder;
import org.slimecraft.bedrock.menu.state.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Menu {
    private final MenuType type;
    private final Component name;
    private final List<Button> buttons = new ArrayList<>();
    private InventoryView view;
    private boolean closeable = true;
    private List<State<?>> states = new ArrayList<>();

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

    public static class Inventory {

        public static NameBuilder<InventoryMenuBuilder<MenuType.Typed<@NotNull InventoryView, ? extends @NotNull InventoryViewBuilder<@NotNull InventoryView>>>> one() {
            return new InventoryMenuBuilder<>().type(MenuType.GENERIC_9X1);
        }

        public static NameBuilder<InventoryMenuBuilder<MenuType.Typed<@NotNull InventoryView, ? extends @NotNull InventoryViewBuilder<@NotNull InventoryView>>>> two() {
            return new InventoryMenuBuilder<>().type(MenuType.GENERIC_9X2);
        }

        public static NameBuilder<InventoryMenuBuilder<MenuType.Typed<@NotNull InventoryView, ? extends @NotNull InventoryViewBuilder<@NotNull InventoryView>>>> three() {
            return new InventoryMenuBuilder<>().type(MenuType.GENERIC_9X3);
        }

        public static NameBuilder<InventoryMenuBuilder<MenuType.Typed<@NotNull InventoryView, ? extends @NotNull InventoryViewBuilder<@NotNull InventoryView>>>> four() {
            return new InventoryMenuBuilder<>().type(MenuType.GENERIC_9X4);
        }

        public static NameBuilder<InventoryMenuBuilder<MenuType.Typed<@NotNull InventoryView, ? extends @NotNull InventoryViewBuilder<@NotNull InventoryView>>>> five() {
            return new InventoryMenuBuilder<>().type(MenuType.GENERIC_9X5);
        }

        public static NameBuilder<InventoryMenuBuilder<MenuType.Typed<@NotNull InventoryView, ? extends @NotNull InventoryViewBuilder<@NotNull InventoryView>>>> six() {
            return new InventoryMenuBuilder<>().type(MenuType.GENERIC_9X6);
        }
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

    public boolean isCloseable() {
        return closeable;
    }

    public void setCloseable(boolean closeable) {
        this.closeable = closeable;
    }

    public <T> State<T> getState(String name) {
        return (State<T>) states.stream().filter(state -> state.getName().equals(name)).findFirst().orElseThrow();
    }

    public List<State<?>> getStates() {
        return states;
    }

    public void addState(State<?> state) {
        states.add(state);
    }

    public void setStates(List<State<?>> states) {
        this.states = states;
    }
}
