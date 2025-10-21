package org.slimecraft.bedrock.menu.context;

import org.slimecraft.bedrock.menu.Menu;

public class MenuContext {
    private final Menu menu;

    public MenuContext(Menu menu) {
        this.menu = menu;
    }

    public Menu menu() {
        return menu;
    }
}
