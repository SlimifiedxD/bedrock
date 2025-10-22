package org.slimecraft.bedrock.event;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Makes event registration and creation easier. {@link org.bukkit.event.Listener} becomes irrelevant.
 */
public final class Events {
    private Events() {
    }

    /**
     * Fire an event of a type. All subscribers listening to this event's class will have their {@link Consumer} accepted.
     *
     * @param <T> The event class.
     */
    public static <T> void fire(@NotNull T event) {
        fireEventNodeRecursive(event, EventNode.global());
    }

    private static <T> void fireEventNodeRecursive(T event, EventNode node) {
        fireEventNode(event, node);
        node.getChildren().forEach(child -> fireEventNodeRecursive(event, child));
    }

    @SuppressWarnings("unchecked")
    private static <T> void fireEventNode(T event, EventNode node) {
        node.getListeners().forEach(listener -> {
            if (!listener.getEventType().isAssignableFrom(event.getClass())) {
                return;
            }
            final var typedListener = (EventListener<T>) listener;
            for (var handler : typedListener.getHandlers()) {
                final var optFilter = handler.getFilter();
                if (optFilter.isPresent()) {
                    final var filter = optFilter.get();
                    if (!filter.getPredicate().test(event)) {
                        filter.getOrElse().ifPresent(orElseHandler -> {
                            orElseHandler.accept(event);
                        });
                        continue;
                    }
                }
                handler.getConsumer().accept(event);
            }
        });
    }
}
