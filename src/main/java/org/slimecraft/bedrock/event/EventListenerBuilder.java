package org.slimecraft.bedrock.event;

import java.util.function.Consumer;
import java.util.function.Predicate;

public final class EventListenerBuilder<T> {
    private final EventListener<T> listener;
    private Predicate<T> filter;

    EventListenerBuilder(Class<T> type, Consumer<T> handler) {
        listener = new EventListener<>(type, handler);
    }

    public EventListenerBuilder<T> predicate(Predicate<T> filter) {
        this.filter = filter;
        return this;
    }

    public EventListener<T> build() {
        return listener;
    }
}
