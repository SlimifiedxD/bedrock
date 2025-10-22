package org.slimecraft.bedrock.event;

import java.util.function.Consumer;
import java.util.function.Predicate;

public interface OptionalBuilder<T, S extends OptionalBuilder<T, S>> {
    S filter(Filter<T> filter);

    S filter(FilterBuilder<T> builder);

    S filter(Predicate<T> predicate);

    S filter(Predicate<T> predicate, Consumer<T> orElse);

    S handler(Consumer<T> handler);

    EventListener<T> build();
}
