package org.slimecraft.bedrock.menu.button.builder;

import org.slimecraft.bedrock.menu.button.Button;
import org.slimecraft.bedrock.menu.context.ClickContext;

import java.util.function.Consumer;

public interface OptionalBuilder {
    OptionalBuilder leftClicked(Consumer<ClickContext> leftClicked);

    OptionalBuilder rightClicked(Consumer<ClickContext> rightClicked);

    OptionalBuilder moveable();

    Button build();
}
