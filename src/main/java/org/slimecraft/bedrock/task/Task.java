package org.slimecraft.bedrock.task;

import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * A wrapper around BukkitTask that is meant for a more developer-friendly class without the legacy Bukkit methods.
 * This is usually created with some method in {@link TaskBuilder}.
 */
public final class Task {
    private final BukkitTask delegate;
    private long timesRan;
    private final long expireAfter;
    private Consumer<Task> whenStopped;

    /**
     * Creates a task.
     * @param delegate The delegate which this task should use.
     */
    public Task(@NotNull BukkitTask delegate, long expireAfter, @Nullable Consumer<Task> whenStopped) {
        this.delegate = delegate;
        this.expireAfter = expireAfter;
        this.whenStopped = whenStopped;
    }

    public static TaskBuilder builder() {
        return new TaskBuilder();
    }

    public long getTimesRan() {
        return timesRan;
    }

    public void incrementTimesRan() {
        timesRan++;
        if (this.expireAfter == timesRan) {
            this.cancel();
        }
    }

    /**
     * Cancels this task.
     */
    public void cancel() {
        if (whenStopped != null) {
            whenStopped.accept(this);
        }
        this.delegate.cancel();
    }
}
