package org.slimecraft.bedrock.util.item;

import io.papermc.paper.datacomponent.DataComponentBuilder;
import io.papermc.paper.datacomponent.DataComponentType;
import io.papermc.paper.datacomponent.item.ItemLore;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface OptionalBuilderStep {
    OptionalBuilderStep miniMessage(MiniMessage miniMessage);

    OptionalBuilderStep name(Component name);

    OptionalBuilderStep name(String name);

    OptionalBuilderStep lore(ItemLore lore);

    OptionalBuilderStep lore(List<Component> lore);

    OptionalBuilderStep loreString(List<String> lore);

    OptionalBuilderStep amount(int amount);

    OptionalBuilderStep component(DataComponentType.NonValued type);

    <T> OptionalBuilderStep component(DataComponentType.Valued<@NotNull T> type, T value);

    <T> OptionalBuilderStep component(DataComponentType.Valued<@NotNull T> type, DataComponentBuilder<@NotNull T> valueBuilder);

    <P, C> OptionalBuilderStep pdc(@NotNull NamespacedKey key, @NotNull PersistentDataType<P, C> type, @NotNull C value);

    ItemStack build();
}
