package org.slimecraft.bedrock.event;

import java.util.function.Consumer;

public interface HandlerBuilder<T, O extends OptionalBuilder<T, ?>> {
    O handler(Consumer<T> handler);
}
