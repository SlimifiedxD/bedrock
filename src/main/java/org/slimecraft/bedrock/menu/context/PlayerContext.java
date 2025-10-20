package org.slimecraft.bedrock.menu.context;

import org.bukkit.entity.Player;

public class PlayerContext {
    private final Player player;

    public PlayerContext(Player player) {
        this.player = player;
    }

    public Player player() {
        return player;
    }
}
