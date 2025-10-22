package org.slimecraft.bedrock.event;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class FilterBuilder<T> {
    private final Filter<T> filter;

    public FilterBuilder(Predicate<T> predicate) {
        this.filter = new Filter<>(predicate);
    }

    public FilterBuilder<T> orElse(Consumer<T> orElse) {
        filter.setOrElse(orElse);
        return this;
    }

    public Filter<T> build() {
        return filter;
    }
}
