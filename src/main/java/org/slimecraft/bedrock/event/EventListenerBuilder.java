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
    private Filter<T> previousFilter;

    EventListenerBuilder(Class<T> eventClass) {
        this.listener = new EventListener<>(eventClass);
    }

    @Override
    public EventListenerBuilder<T> handler(Consumer<T> consumer) {
        EventHandler<T> handler;
        if (previousFilter != null) {
            handler = new EventHandler<>(consumer, previousFilter);
            previousFilter = null;
        } else {
            handler = new EventHandler<>(consumer);
        }
        listener.addHandler(handler);
        return this;
    }

    @Override
    public EventListenerBuilder<T> filter(Filter<T> filter) {
        previousFilter = filter;
        return this;
    }

    @Override
    public EventListenerBuilder<T> filter(FilterBuilder<T> builder) {
        this.filter(builder.build());
        return this;
    }

    @Override
    public EventListenerBuilder<T> filter(Predicate<T> predicate) {
        this.filter(new Filter<>(predicate));
        return this;
    }

    @Override
    public EventListenerBuilder<T> filter(Predicate<T> predicate, Consumer<T> orElse) {
        final Filter<T> filter = new Filter<>(predicate);
        filter.setOrElse(orElse);
        this.filter(filter);
        return this;
    }

    @Override
    public EventListener<T> build() {
        return listener;
    }
}
