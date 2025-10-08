package org.slimecraft.bedrock;

import org.bukkit.Bukkit;

import java.util.function.Consumer;

/**
 * A class that provides simple methods to run tasks that are sync or async, without legacy Bukkit methods.
 * The various {@link Long}s that are taken in by the methods can be retrieved using {@link Ticks} for a more
 * developer-friendly experience.
 */
public final class Tasks {
    private Tasks() {}

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

    public static void run(Consumer<Task> taskConsumer) {
        run(taskConsumer, false);
    }

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

    public static void later(Consumer<Task> taskConsumer, long later) {
        later(taskConsumer, later, false);
    }

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

    public static void repeating(Consumer<Task> taskConsumer, long delay, long timer) {
        repeating(taskConsumer, delay, timer, false);
    }
}
