package org.slimecraft.bedrock.util.item;

import io.papermc.paper.datacomponent.DataComponentBuilder;
import io.papermc.paper.datacomponent.DataComponentType;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.ItemLore;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.slimecraft.bedrock.internal.Bedrock;

import java.util.List;

public final class ItemBuilder implements MaterialBuilderStep, OptionalBuilderStep {
    private ItemBuilder() {}

    public static MaterialBuilderStep create() {
        return new ItemBuilder();
    }

    public static ItemBuilder from(ItemStack itemStack) {
        final ItemBuilder builder = new ItemBuilder();
        builder.itemStack = itemStack;
        return builder;
    }

    private ItemStack itemStack;

    @Override
    public OptionalBuilderStep material(Material material) {
        itemStack = ItemStack.of(material);
        return this;
    }

    @Override
    public OptionalBuilderStep name(Component name) {
        return component(DataComponentTypes.ITEM_NAME, name);
    }

    @Override
    public OptionalBuilderStep name(String name) {
        name(deserialize(name));
        return this;
    }

    @Override
    public OptionalBuilderStep lore(ItemLore lore) {
        return component(DataComponentTypes.LORE, lore);
    }

    @Override
    public OptionalBuilderStep lore(List<Component> lore) {
        final ItemLore.Builder itemLore = ItemLore.lore();
        lore.forEach(itemLore::addLine);
        lore(itemLore.build());
        return this;
    }

    @Override
    public OptionalBuilderStep loreString(List<String> lore) {
        final ItemLore.Builder itemLore = ItemLore.lore();
        lore.forEach(s -> {
            itemLore.addLine(deserialize(s));
        });
        lore(itemLore.build());
        return this;
    }

    private Component deserialize(String input) {
        return Bedrock.bedrock().getMiniMessage().deserialize(input).colorIfAbsent(NamedTextColor.WHITE).decorationIfAbsent(TextDecoration.ITALIC, TextDecoration.State.FALSE);
    }

    @Override
    public OptionalBuilderStep amount(int amount) {
        itemStack = itemStack.clone();
        itemStack.setAmount(amount);
        return this;
    }

    @Override
    public OptionalBuilderStep component(DataComponentType.NonValued type) {
        itemStack = itemStack.clone();
        itemStack.setData(type);
        return this;
    }

    @Override
    public <T> OptionalBuilderStep component(DataComponentType.Valued<@NotNull T> type, T value) {
        itemStack = itemStack.clone();
        itemStack.setData(type, value);
        return this;
    }

    @Override
    public <T> OptionalBuilderStep component(DataComponentType.Valued<@NotNull T> type, DataComponentBuilder<@NotNull T> valueBuilder) {
        itemStack = itemStack.clone();
        itemStack.setData(type, valueBuilder);
        return this;
    }

    @Override
    public <P, C> OptionalBuilderStep pdc(@NotNull NamespacedKey key, @NotNull PersistentDataType<P, C> type, @NotNull C value) {
        itemStack = itemStack.clone();
        itemStack.editPersistentDataContainer(pdc -> {
            pdc.set(key, type, value);
        });
        return this;
    }

    @Override
    public ItemStack build() {
        return itemStack.clone();
    }
}
