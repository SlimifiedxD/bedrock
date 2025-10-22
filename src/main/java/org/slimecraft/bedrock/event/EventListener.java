package org.slimecraft.bedrock.event;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class EventListener<T> {
    private final Class<T> eventType;
    private final List<Consumer<T>> handlers = new ArrayList<>();
    private final List<Predicate<T>> filters = new ArrayList<>();

    EventListener(Class<T> eventType) {
        this.eventType = eventType;
    }

    public static <T> EventListenerBuilder<T> of(Class<T> clazz) {
        return new EventListenerBuilder<>(clazz);
    }

    public void addHandler(Consumer<T> handler) {
        handlers.add(handler);
    }

    public void addFilter(Predicate<T> filter) {
        filters.add(filter);
    }

    public Class<T> getEventType() {
        return eventType;
    }

    public List<Consumer<T>> getHandlers() {
        return handlers;
    }

    public List<Predicate<T>> getFilters() {
        return filters;
    }
}
