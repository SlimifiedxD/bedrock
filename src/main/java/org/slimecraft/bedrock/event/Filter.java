package org.slimecraft.bedrock.event;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Filter<T> {
    private final Predicate<T> predicate;
    private Consumer<T> orElse;

    Filter(Predicate<T> predicate) {
        this.predicate = predicate;
    }

    public static <T> FilterBuilder<T> of(Predicate<T> predicate) {
        return new FilterBuilder<>(predicate);
    }

    public Optional<Consumer<T>> getOrElse() {
        return Optional.ofNullable(orElse);
    }

    public void setOrElse(Consumer<T> orElse) {
        this.orElse = orElse;
    }
}
