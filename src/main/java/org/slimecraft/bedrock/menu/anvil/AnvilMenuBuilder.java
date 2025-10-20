package org.slimecraft.bedrock.menu.anvil;

import net.kyori.adventure.text.Component;
import org.slimecraft.bedrock.menu.builder.NameBuilder;
import org.slimecraft.bedrock.menu.button.Button;
import org.slimecraft.bedrock.menu.button.builder.OptionalBuilder;
import org.slimecraft.bedrock.menu.context.AnvilTextContext;

import java.util.function.Consumer;

public class AnvilMenuBuilder implements NameBuilder<OptionalAnvilBuilder>, OptionalAnvilBuilder {
    private AnvilMenu menu;

    @Override
    public OptionalAnvilBuilder name(Component name) {
        menu = new AnvilMenu(name);
        return this;
    }

    @Override
    public OptionalAnvilBuilder button(Button button) {
        menu.addButton(button);
        return this;
    }

    @Override
    public OptionalAnvilBuilder button(OptionalBuilder builder) {
        button(builder.build());
        return this;
    }

    @Override
    public OptionalAnvilBuilder whenTextChanged(Consumer<AnvilTextContext> whenTextChanged) {
        menu.setWhenTextChanged(whenTextChanged);
        return this;
    }

    @Override
    public AnvilMenu build() {
        return menu;
    }
}
