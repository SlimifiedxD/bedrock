package org.slimecraft.bedrock.task;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.ApiStatus;
import org.slimecraft.bedrock.internal.Bedrock;
import org.slimecraft.bedrock.util.Ticks;

import java.util.function.Consumer;

/**
 * A class that provides simple methods to run tasks that are sync or async, without legacy Bukkit methods.
 * The various {@link Long}s that are taken in by the methods can be retrieved using {@link Ticks} for a more
 * developer-friendly experience.
 *
 * <br><br>This is obsolete because of the new {@link TaskBuilder} API which is much more flexible
 * and allows for the composition of tasks. This API cannot keep up.
 */
@ApiStatus.Obsolete
public final class Tasks {
    private Tasks() {
    }

    /**
     * Run a task, either blocking or async.
     */
    public static void run(Consumer<Task> taskConsumer, boolean async) {
        if (async) {
            Bukkit.getScheduler().runTaskAsynchronously(Bedrock.bedrock().getPlugin(), bukkitTask -> {
                taskConsumer.accept(new Task(bukkitTask, 0, null, null));
            });
            return;
        }
        Bukkit.getScheduler().runTask(Bedrock.bedrock().getPlugin(), bukkitTask -> {
            taskConsumer.accept(new Task(bukkitTask, 0, null, null));
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
     *
     * @param later The ticks to run the task after.
     */
    public static void later(Consumer<Task> taskConsumer, long later, boolean async) {
        if (async) {
            Bukkit.getScheduler().runTaskLaterAsynchronously(Bedrock.bedrock().getPlugin(), bukkitTask -> {
                taskConsumer.accept(new Task(bukkitTask, 0, null, null));
            }, later);
            return;
        }
        Bukkit.getScheduler().runTaskLater(Bedrock.bedrock().getPlugin(), bukkitTask -> {
            taskConsumer.accept(new Task(bukkitTask, 0, null, null));
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
     *
     * @param delay The ticks to run the task after.
     * @param timer The ticks to repeat the task after.
     */
    public static void repeating(Consumer<Task> taskConsumer, long delay, long timer, boolean async) {
        final Task[] holder = new Task[1];
        final Runnable runnable = () -> {
            final Task task = holder[0];
            if (task == null) return;
            taskConsumer.accept(task);
            task.incrementTimesRan();
        };

        BukkitTask bukkitTask;
        if (async) {
            bukkitTask = Bukkit.getScheduler().runTaskTimerAsynchronously(Bedrock.bedrock().getPlugin(), runnable, delay, timer);
        } else {
            bukkitTask = Bukkit.getScheduler().runTaskTimer(Bedrock.bedrock().getPlugin(), runnable, delay, timer);
        }

        holder[0] = new Task(bukkitTask, 0, null, null);
    }

    /**
     * Run a blocking task later at a certain time interval.
     * See {@link #repeating(Consumer, long, long, boolean)}.
     */
    public static void repeating(Consumer<Task> taskConsumer, long delay, long timer) {
        repeating(taskConsumer, delay, timer, false);
    }
}
