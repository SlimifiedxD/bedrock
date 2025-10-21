package org.slimecraft.bedrock.menu.builder;

import org.slimecraft.bedrock.menu.Menu;
import org.slimecraft.bedrock.menu.button.Button;
import org.slimecraft.bedrock.menu.state.State;

public interface OptionalBuilder<S extends OptionalBuilder<S, M>, M extends Menu> {
    S button(Button button);

    S button(org.slimecraft.bedrock.menu.button.builder.OptionalBuilder builder);

    S notCloseable();

    S state(State<?>... states);

    M build();
}
