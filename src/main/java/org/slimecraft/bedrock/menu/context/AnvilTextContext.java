package org.slimecraft.bedrock.menu.context;

import org.bukkit.entity.Player;

public class AnvilTextContext extends PlayerContext {
    private final String text;

    public AnvilTextContext(Player player, String text) {
        super(player);
        this.text = text;
    }

    /**
     * Get the text of the anvil. Be wary of the fact that if the text in the anvil
     * is equal to that of the item's name, located in the left slot (index of 0),
     * the return value will be an empty string.
     */
    public String text() {
        return text;
    }
}
