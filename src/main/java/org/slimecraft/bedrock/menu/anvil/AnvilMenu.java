package org.slimecraft.bedrock.menu.anvil;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.MenuType;
import org.slimecraft.bedrock.menu.Menu;
import org.slimecraft.bedrock.menu.context.AnvilTextContext;
import org.slimecraft.bedrock.menu.context.PlayerContext;

import java.util.Optional;
import java.util.function.Consumer;

public class AnvilMenu extends Menu {
    private Consumer<AnvilTextContext> whenTextChanged;

    AnvilMenu(Component name) {
        super(MenuType.ANVIL, name);
    }

    public Optional<Consumer<AnvilTextContext>> getWhenTextChanged() {
        return Optional.ofNullable(whenTextChanged);
    }

    public void setWhenTextChanged(Consumer<AnvilTextContext> whenTextChanged) {
        this.whenTextChanged = whenTextChanged;
    }
}
