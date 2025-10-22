package org.slimecraft.bedrock.event;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface OptionalBuilder<T, S extends OptionalBuilder<T, S>> {
    S filter(Predicate<T> filter);

    S handler(Consumer<T> handler);

    EventListener<T> build();
}
