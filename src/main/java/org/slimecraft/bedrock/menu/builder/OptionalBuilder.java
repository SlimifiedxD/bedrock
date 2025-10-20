package org.slimecraft.bedrock.menu.builder;

import org.slimecraft.bedrock.menu.Menu;
import org.slimecraft.bedrock.menu.button.Button;

public interface OptionalBuilder<S extends OptionalBuilder<S, M>, M extends Menu> {
    S button(Button button);

    S button(org.slimecraft.bedrock.menu.button.builder.OptionalBuilder builder);

    M build();
}
