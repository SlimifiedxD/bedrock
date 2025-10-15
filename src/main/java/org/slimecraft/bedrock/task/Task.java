package org.slimecraft.bedrock.task;

import org.bukkit.scheduler.BukkitTask;

/**
 * A wrapper around BukkitTask that is meant for a more developer-friendly class without the legacy Bukkit methods.
 * This is usually created with some method in {@link Tasks}.
 */
public final class Task {
    private final BukkitTask delegate;

    /**
     * Creates a task.
     * @param delegate The delegate which this task should use.
     */
    public Task(BukkitTask delegate) {
        this.delegate = delegate;
    }

    /**
     * Cancels this task.
     */
    public void cancel() {
        this.delegate.cancel();
    }
}
