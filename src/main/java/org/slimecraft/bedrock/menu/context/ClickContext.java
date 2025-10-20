package org.slimecraft.bedrock.menu.context;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ClickContext extends PlayerContext {
    private final InventoryClickEvent event;

    public ClickContext(InventoryClickEvent event) {
        super((Player) event.getWhoClicked());
        this.event = event;
    }

}
