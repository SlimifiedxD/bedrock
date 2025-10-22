package org.slimecraft.bedrock.event;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class EventListenerBuilder<T> {
    private final EventListener<T> listener;
    private List<Predicate<T>> filters;

    EventListenerBuilder(Class<T> type, Consumer<T> handler) {
        listener = new EventListener<>(type, handler);
    }

    public EventListenerBuilder<T> filter(Predicate<T> filter) {
        Predicate<T> finalPredicate = filter;
        for (final var existingFilter : filters) {
            finalPredicate = finalPredicate.and(existingFilter);
        } //
        filters.add(filter);
        listener.setFilter(finalPredicate);
        return this;
    }
    
    public EventListener<T> build() {
        return listener;
    }
}
