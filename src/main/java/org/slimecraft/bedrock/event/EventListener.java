package org.slimecraft.bedrock.event;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public final class EventListener<T> {
    private final Class<T> eventType;
    private final List<EventHandler<T>> handlers = new ArrayList<>();

    EventListener(Class<T> eventType) {
        this.eventType = eventType;
    }

    public static <T> EventListenerBuilder<T> of(Class<T> clazz) {
        return new EventListenerBuilder<>(clazz);
    }

    public void addHandler(EventHandler<T> handler) {
        this.handlers.add(handler);
    }

    public void addHandler(Consumer<T> consumer) {
        handlers.add(new EventHandler<>(consumer));
    }

    public void addHandler(Consumer<T> consumer, Filter<T> filter) {
        this.addHandler(new EventHandler<>(consumer, filter));
    }

    public void addHandler(Consumer<T> consumer, FilterBuilder<T> builder) {
        this.addHandler(new EventHandler<>(consumer, builder.build()));
    }

    public Class<T> getEventType() {
        return eventType;
    }

    public List<EventHandler<T>> getHandlers() {
        return handlers;
    }
}
