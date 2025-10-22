package org.slimecraft.bedrock.event;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class EventListener<T> {
    private final Class<T> eventType;
    private final Consumer<T> action;
    private Predicate<T> filter;

    EventListener(Class<T> eventType, Consumer<T> action) {
        this.eventType = eventType;
        this.action = action;
    }

    EventListener(Class<T> eventType, Consumer<T> action, Predicate<T> filter) {
        this(eventType, action);
        this.filter = filter;
    }

    public static <T> EventListenerBuilder<T> builder(Class<T> type, Consumer<T> handler) {
        return new EventListenerBuilder<>(type, handler);
    }

    public Class<T> getEventType() {
        return eventType;
    }

    public Consumer<T> getAction() {
        return action;
    }

    public Optional<Predicate<T>> getFilter() {
        return Optional.ofNullable(filter);
    }

    public void setFilter(Predicate<T> filter) {
        this.filter = filter;
    }
}
