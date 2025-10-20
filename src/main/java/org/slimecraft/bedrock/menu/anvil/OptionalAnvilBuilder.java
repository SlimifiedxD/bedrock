package org.slimecraft.bedrock.menu.anvil;

import org.slimecraft.bedrock.menu.builder.OptionalBuilder;
import org.slimecraft.bedrock.menu.context.AnvilTextContext;

import java.util.function.Consumer;

public interface OptionalAnvilBuilder extends OptionalBuilder<OptionalAnvilBuilder, AnvilMenu> {
    OptionalAnvilBuilder whenTextChanged(Consumer<AnvilTextContext> whenTextChanged);
}
