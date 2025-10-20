package org.slimecraft.bedrock.menu.context;

import org.bukkit.entity.Player;

public class AnvilTextContext extends PlayerContext {
    private final String text;

    public AnvilTextContext(Player player, String text) {
        super(player);
        this.text = text;
    }

    public String text() {
        return text;
    }
}
