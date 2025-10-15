package org.slimecraft.bedrock.task;

import org.bukkit.scheduler.BukkitTask;

/**
 * A wrapper around BukkitTask that is meant for a more developer-friendly class without the legacy Bukkit methods.
 * This is usually created with some method in {@link Tasks}.
 */
public sealed class Task permits RepeatingTask {
    private final BukkitTask delegate;

    /**
     * Creates a task.
     * @param delegate The delegate which this task should use.
     */
    Task(BukkitTask delegate) {
        this.delegate = delegate;
    }

    /**
     * Cancels this task.
     */
    public void cancel() {
        this.delegate.cancel();
    }
}
