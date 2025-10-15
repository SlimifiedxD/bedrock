package org.slimecraft.bedrock.task;

import org.bukkit.Bukkit;
import org.slimecraft.bedrock.Bedrock;
import org.slimecraft.bedrock.util.Ticks;

import java.util.function.Consumer;

/**
 * A class that provides simple methods to run tasks that are sync or async, without legacy Bukkit methods.
 * The various {@link Long}s that are taken in by the methods can be retrieved using {@link Ticks} for a more
 * developer-friendly experience.
 */
public final class Tasks {
    private Tasks() {}

    /**
     * Run a task, either blocking or async.
     */
    public static void run(Consumer<Task> taskConsumer, boolean async) {
        if (async) {
            Bukkit.getScheduler().runTaskAsynchronously(Bedrock.getPlugin(), bukkitTask -> {
                taskConsumer.accept(new Task(bukkitTask));
            });
            return;
        }
        Bukkit.getScheduler().runTask(Bedrock.getPlugin(), bukkitTask -> {
            taskConsumer.accept(new Task(bukkitTask));
        });
    }

    /**
     * Run a blocking task.
     * See {@link #run(Consumer, boolean)}.
     */
    public static void run(Consumer<Task> taskConsumer) {
        run(taskConsumer, false);
    }

    /**
     * Run a task, either blocking or async, after the given time in ticks has passed.
     * @param later The ticks to run the task after.
     */
    public static void later(Consumer<Task> taskConsumer, long later, boolean async) {
        if (async) {
            Bukkit.getScheduler().runTaskLaterAsynchronously(Bedrock.getPlugin(), bukkitTask -> {
                taskConsumer.accept(new Task(bukkitTask));
            }, later);
            return;
        }
        Bukkit.getScheduler().runTaskLater(Bedrock.getPlugin(), bukkitTask -> {
            taskConsumer.accept(new Task(bukkitTask));
        }, later);
    }

    /**
     * Run a blocking task later.
     * See {@link #later(Consumer, long, boolean)}.
     */
    public static void later(Consumer<Task> taskConsumer, long later) {
        later(taskConsumer, later, false);
    }

    /**
     * Run a task, either blocking or async, after the given time in ticks has passed, and repeat it after the given time in ticks has passed until it is cancelled.
     * @param delay The ticks to run the task after.
     * @param timer The ticks to repeat the task after.
     */
    public static void repeating(Consumer<Task> taskConsumer, long delay, long timer, boolean async) {
        if (async) {
            Bukkit.getScheduler().runTaskTimerAsynchronously(Bedrock.getPlugin(), bukkitTask -> {
                taskConsumer.accept(new Task(bukkitTask));
            }, delay, timer);
            return;
        }
        Bukkit.getScheduler().runTaskTimer(Bedrock.getPlugin(), bukkitTask -> {
            taskConsumer.accept(new Task(bukkitTask));
        }, delay, timer);
    }

    /**
     * Run a blocking task later at a certain time interval.
     * See {@link #repeating(Consumer, long, long, boolean)}.
     */
    public static void repeating(Consumer<Task> taskConsumer, long delay, long timer) {
        repeating(taskConsumer, delay, timer, false);
    }
}
