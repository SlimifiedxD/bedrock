package org.slimecraft.bedrock.menu.button;

import org.bukkit.inventory.ItemStack;
import org.slimecraft.bedrock.menu.button.builder.ButtonBuilder;
import org.slimecraft.bedrock.menu.button.builder.ItemBuilder;
import org.slimecraft.bedrock.menu.context.ClickContext;

import java.util.Optional;
import java.util.function.Consumer;

public class Button {
    private final ItemStack item;
    private final int slot;
    private Consumer<ClickContext> whenLeftClicked;
    private Consumer<ClickContext> whenRightClicked;

    public Button(ItemStack item, int slot) {
        this.item = item;
        this.slot = slot;
    }

    public static ItemBuilder builder() {
        return new ButtonBuilder();
    }

    public ItemStack getItem() {
        return item;
    }

    public int getSlot() {
        return slot;
    }

    public Optional<Consumer<ClickContext>> getWhenLeftClicked() {
        return Optional.ofNullable(whenLeftClicked);
    }

    public Optional<Consumer<ClickContext>> getWhenRightClicked() {
        return Optional.ofNullable(whenRightClicked);
    }

    public void setWhenLeftClicked(Consumer<ClickContext> whenLeftClicked) {
        this.whenLeftClicked = whenLeftClicked;
    }

    public void setWhenRightClicked(Consumer<ClickContext> whenRightClicked) {
        this.whenRightClicked = whenRightClicked;
    }
}
