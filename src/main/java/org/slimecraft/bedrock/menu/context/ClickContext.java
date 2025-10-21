package org.slimecraft.bedrock.menu.context;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.slimecraft.bedrock.menu.Menu;
import org.slimecraft.bedrock.menu.button.Button;

public class ClickContext extends PlayerContext {
    private final InventoryClickEvent event;
    private final Button button;

    public ClickContext(Menu menu, InventoryClickEvent event, Button button) {
        super(menu, (Player) event.getWhoClicked());
        this.event = event;
        this.button = button;
    }

    public Button button() {
        return button;
    }
}
