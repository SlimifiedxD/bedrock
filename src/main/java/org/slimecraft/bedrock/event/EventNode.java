package org.slimecraft.bedrock.event;

import net.kyori.adventure.key.Key;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class EventNode {
    private final Key identifier;
    private final List<EventListener<?>> listeners;
    private final List<EventNode> children;

    private static final class SingletonHelper {
        private static final EventNode GLOBAL_NODE = new EventNode(Key.key("bedrock", "global"), new ArrayList<>(), new ArrayList<>());
    }

    public static EventNode global() {
        return SingletonHelper.GLOBAL_NODE;
    }

    public EventNode(Key identifier, List<EventListener<?>> listeners, List<EventNode> children) {
        this.identifier = identifier;
        this.listeners = listeners;
        this.children = children;
    }

    public EventNode(Key identifier) {
        this(identifier, new ArrayList<>(), new ArrayList<>());
    }

    public <T> void addListener(EventListener<T> listener) {
        listeners.add(listener);
    }

    public <T> void addListener(Class<T> eventType, Consumer<T> action) {
        this.addListener(new EventListener<>(eventType, action));
    }

    public <T> void addListener(Class<T> eventType, Consumer<T> action, Predicate<T> predicate) {
        this.addListener(new EventListener<>(eventType, action, predicate));
    }

    public <T> void addListener(EventListenerBuilder<T> builder) {
        this.listeners.add(builder.build());
    }

    public void addChild(EventNode child) {
        this.children.add(child);
    }

    public Key getIdentifier() {
        return identifier;
    }

    public List<EventNode> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public List<EventListener<?>> getListeners() {
        return Collections.unmodifiableList(listeners);
    }
}
