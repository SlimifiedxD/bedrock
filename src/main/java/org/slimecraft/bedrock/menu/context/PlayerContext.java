package org.slimecraft.bedrock.menu.context;

import org.bukkit.entity.Player;
import org.slimecraft.bedrock.menu.Menu;

public class PlayerContext extends MenuContext {
    private final Player player;

    public PlayerContext(Menu menu, Player player) {
        super(menu);
        this.player = player;
    }

    public Player player() {
        return player;
    }
}
