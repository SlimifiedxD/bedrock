package org.slimecraft.bedrock.event;

import java.util.function.Predicate;

public interface OptionalBuilder<T, S extends OptionalBuilder<T, S>> {
    S filter(Predicate<T> filter);

    EventListener<T> build();
}
