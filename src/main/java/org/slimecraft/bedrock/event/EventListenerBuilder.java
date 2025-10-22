package org.slimecraft.bedrock.event;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class EventListenerBuilder<T>
        implements
        TypeBuilder<T, EventListenerBuilder<T>>,
        HandlerBuilder<T, EventListenerBuilder<T>>,
        OptionalBuilder<T, EventListenerBuilder<T>>
{
    private EventListener<T> listener;
    private Class<T> eventClass;
    private Consumer<T> handler;

    private List<Predicate<T>> filters;

    EventListenerBuilder(Class<T> eventClass) {
        this.eventClass = eventClass;
    }

    @Override
    public EventListenerBuilder<T> handler(Consumer<T> handler) {
        this.handler = handler;
        this.listener = new EventListener<>(eventClass, handler);
        return this;
    }

    @Override
    public EventListenerBuilder<T> filter(Predicate<T> filter) {
        Predicate<T> finalPredicate = filter;
        for (final var existingFilter : filters) {
            finalPredicate = finalPredicate.and(existingFilter);
        }
        filters.add(filter);
        listener.setFilter(finalPredicate);
        return this;
    }

    @Override
    public EventListener<T> build() {
        return listener;
    }
}
