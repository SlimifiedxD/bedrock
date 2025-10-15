package org.slimecraft.bedrock.task;

import org.bukkit.scheduler.BukkitTask;

public final class RepeatingTask extends Task {
    private long timesRan;

    /**
     * Creates a repeating task.
     * @param delegate The delegate which this task should use.
     */
    public RepeatingTask(BukkitTask delegate) {
        super(delegate);
    }

    public long getTimesRan() {
        return timesRan;
    }

    void incrementTimesRan() {
        timesRan++;
    }
}
