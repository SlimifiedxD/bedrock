package org.slimecraft.bedrock.menu.button;

import org.bukkit.inventory.ItemStack;
import org.slimecraft.bedrock.menu.button.builder.ButtonBuilder;
import org.slimecraft.bedrock.menu.button.builder.ItemBuilder;
import org.slimecraft.bedrock.menu.context.ClickContext;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class Button {
    private ItemStack item;
    private final int slot;
    private Consumer<ClickContext> whenLeftClicked;
    private Consumer<ClickContext> whenRightClicked;
    private boolean moveable;

    public Button(ItemStack item, int slot) {
        this.item = item;
        this.slot = slot;
    }

    public Button(ItemStack item, int slot, Consumer<ClickContext> whenLeftClicked, Consumer<ClickContext> whenRightClicked, boolean moveable) {
        this.item = item;
        this.slot = slot;
        this.whenLeftClicked = whenLeftClicked;
        this.whenRightClicked = whenRightClicked;
        this.moveable = moveable;
    }

    public static ItemBuilder builder() {
        return new ButtonBuilder();
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
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

    public boolean isMoveable() {
        return moveable;
    }

    public void setMoveable(boolean moveable) {
        this.moveable = moveable;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Button button = (Button) object;
        return slot == button.slot && moveable == button.moveable && Objects.equals(item, button.item) && Objects.equals(whenLeftClicked, button.whenLeftClicked) && Objects.equals(whenRightClicked, button.whenRightClicked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, slot, whenLeftClicked, whenRightClicked, moveable);
    }

    @Override
    public String toString() {
        return "Button{" +
                "item=" + item +
                ", slot=" + slot +
                ", whenLeftClicked=" + whenLeftClicked +
                ", whenRightClicked=" + whenRightClicked +
                ", moveable=" + moveable +
                '}';
    }
}
