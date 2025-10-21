package org.slimecraft.bedrock.menu.button.builder;

import org.bukkit.inventory.ItemStack;
import org.slimecraft.bedrock.menu.button.Button;
import org.slimecraft.bedrock.menu.context.ClickContext;

import java.util.function.Consumer;

public class ButtonBuilder implements ItemBuilder, SlotBuilder, OptionalBuilder {
    private Button button;
    private ItemStack item;
    private int slot;

    public ButtonBuilder() {}

    @Override
    public SlotBuilder item(ItemStack item) {
        this.item = item;
        return this;
    }

    @Override
    public OptionalBuilder slot(int slot) {
        this.slot = slot;
        button = new Button(item, slot);
        return this;
    }

    @Override
    public OptionalBuilder leftClicked(Consumer<ClickContext> leftClicked) {
        button.setWhenLeftClicked(leftClicked);
        return this;
    }

    @Override
    public OptionalBuilder rightClicked(Consumer<ClickContext> rightClicked) {
        button.setWhenRightClicked(rightClicked);
        return this;
    }

    @Override
    public OptionalBuilder moveable() {
        button.setMoveable(true);
        return this;
    }

    @Override
    public Button build() {
        return button;
    }
}
