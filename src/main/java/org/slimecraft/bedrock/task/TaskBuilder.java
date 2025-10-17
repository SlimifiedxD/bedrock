package org.slimecraft.bedrock.task.builder;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;
import org.slimecraft.bedrock.internal.Bedrock;
import org.slimecraft.bedrock.task.Task;
import org.slimecraft.bedrock.util.Ticks;

import java.util.function.Consumer;

public final class TaskBuilder {
    private Long delay;
    private Long repeat;
    private Consumer<Task> whenRan;

    public TaskBuilder() {}

    public TaskBuilder delay(long delay) {
        this.delay = delay;
        return this;
    }

    public TaskBuilder repeat(long repeat) {
        this.repeat = repeat;
        return this;
    }

    public TaskBuilder ran(Consumer<Task> whenRan) {
        this.whenRan = whenRan;
        return this;
    }

    public Task run() {
        return new Task(this.fromFields());
    }

    private BukkitTask fromFields() {
        final BukkitTask[] mutableTask = new BukkitTask[1];
        if (delay == null && repeat == null) {
            Bukkit.getScheduler().runTask(Bedrock.getPlugin(), bukkitTask -> {
                mutableTask[0] = bukkitTask;
                whenRan.accept(new Task(bukkitTask));
            });
        } else if (delay != null && repeat == null) {
            Bukkit.getScheduler().runTaskLater(Bedrock.getPlugin(), bukkitTask -> {
                mutableTask[0] = bukkitTask;
                whenRan.accept(new Task(bukkitTask));
            }, delay);
        } else if (delay == null) {
            Bukkit.getScheduler().runTaskTimer(Bedrock.getPlugin(), bukkitTask -> {
                mutableTask[0] = bukkitTask;
                whenRan.accept(new Task(bukkitTask));
            }, Ticks.none(), repeat);
        } else {
            Bukkit.getScheduler().runTaskTimer(Bedrock.getPlugin(), bukkitTask -> {
                mutableTask[0] = bukkitTask;
                whenRan.accept(new Task(bukkitTask));
            }, delay, repeat);
        }

        return mutableTask[0];
    }
}
