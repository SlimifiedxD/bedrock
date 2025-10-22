package org.slimecraft.bedrock.event;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public final class EventListenerBuilder<T>
        implements
        OptionalBuilder<T, EventListenerBuilder<T>>
{
    private final EventListener<T> listener;

    EventListenerBuilder(Class<T> eventClass) {
        this.listener = new EventListener<>(eventClass);
    }

    @Override
    public EventListenerBuilder<T> handler(Consumer<T> handler) {
        listener.addHandler(handler);
        return this;
    }

    @Override
    public EventListenerBuilder<T> filter(Predicate<T> filter) {
        listener.addFilter(filter);
        return this;
    }

    @Override
    public EventListener<T> build() {
        return listener;
    }
}
