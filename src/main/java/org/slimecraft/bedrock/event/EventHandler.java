package org.slimecraft.bedrock.event;

import java.util.Optional;
import java.util.function.Consumer;

public class EventHandler<T> {
    private final Consumer<T> consumer;
    private Filter<T> filter;

    EventHandler(Consumer<T> consumer) {
        this.consumer = consumer;
    }

    public EventHandler(Consumer<T> consumer, Filter<T> filter) {
        this.consumer = consumer;
        this.filter = filter;
    }

    public Consumer<T> getConsumer() {
        return consumer;
    }

    public Optional<Filter<T>> getFilter() {
        return Optional.ofNullable(filter);
    }

    public void setFilter(Filter<T> filter) {
        this.filter = filter;
    }
}
