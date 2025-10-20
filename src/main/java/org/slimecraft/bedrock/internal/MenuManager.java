package org.slimecraft.bedrock.internal;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.view.AnvilView;
import org.slimecraft.bedrock.menu.Menu;
import org.slimecraft.bedrock.menu.anvil.AnvilMenu;
import org.slimecraft.bedrock.menu.button.Button;
import org.slimecraft.bedrock.menu.context.AnvilTextContext;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MenuManager {
    public static final Map<UUID, Menu> MENUS = new HashMap<>();
    /**
     * Because the {@link PrepareAnvilEvent} is called multiple times, and we can't expose that to the end user,
     * we simply check if the text from this map exists, and if it is different from the one from the event,
     * we use that in the {@link AnvilTextContext}. If it does not exist, we simply put the text from the
     * event into the map. That way, the consumer of the context is only run once.
     */
    private static final Map<UUID, String> ANVIL_TEXT = new HashMap<>();

    public static void init() {
        Bedrock.BEDROCK_NODE
                .addListener(InventoryOpenEvent.class, event -> {
                    final UUID id = event.getPlayer().getUniqueId();
                    if (!MENUS.containsKey(id)) {
                        return;
                    }
                    final Menu menu = MENUS.get(id);

                    for (Button button : menu.getButtons()) {
                        menu.getView().setItem(button.getSlot(), button.getItem());
                    }
                });

        Bedrock.BEDROCK_NODE
                        .addListener(PrepareAnvilEvent.class, event -> {
                            final AnvilView view = event.getView();
                            final Player player = (Player) view.getPlayer();
                            final UUID id = player.getUniqueId();
                            if (!MENUS.containsKey(id)) {
                                return;
                            }
                            final Menu menu = MENUS.get(id);
                            if (!(menu instanceof final AnvilMenu anvilMenu)) return;
                            view.setRepairCost(0);
                            if (anvilMenu.getWhenTextChanged().isEmpty()) return;

                            String text = ANVIL_TEXT.get(id);
                            final String renameText = view.getRenameText();
                            if (text == null || !text.equals(renameText)) {
                                if (renameText == null) return;
                                ANVIL_TEXT.put(id, renameText);
                                text = renameText;
                                final AnvilTextContext context = new AnvilTextContext(player, text);
                                anvilMenu.getWhenTextChanged().get().accept(context);
                            }
                        });

        Bedrock.BEDROCK_NODE
                .addListener(InventoryCloseEvent.class, event -> {
                    final UUID id = event.getPlayer().getUniqueId();
                    if (!MENUS.containsKey(id)) {
                        return;
                    }
                    MENUS.remove(id);
                });
    }
}
