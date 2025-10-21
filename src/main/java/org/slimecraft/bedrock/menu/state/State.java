package org.slimecraft.bedrock.menu.state;

import java.util.Objects;

public class State<T> {
    private final String name;
    private T value;

    State(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public static <T> State<T> state(String name, T type) {
        return new State<>(name, type);
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        State<?> state = (State<?>) object;
        return Objects.equals(name, state.name) && Objects.equals(value, state.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

    @Override
    public String toString() {
        return "State{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
