package org.slimecraft.bedrock;

import java.time.Duration;

/**
 * A simple way to convert between {@link Duration}s and ticks. Common units of time are also included
 * so one does not have to waste time typing boilerplate in the {@link Ticks#duration(Duration)} method.
 */
public final class Ticks {
    private Ticks() {}

    public static long duration(Duration duration) {
        return duration.toMillis() / 50;
    }

    public static long none() {
        return 0;
    }

    public static long of(long ticks) {
        return ticks;
    }

    public static long seconds(long seconds) {
        return duration(Duration.ofSeconds(seconds));
    }

    public static long minutes(long minutes) {
        return duration(Duration.ofMinutes(minutes));
    }

    public static long hours(long hours) {
        return duration(Duration.ofHours(hours));
    }

    public static long days(long days) {
        return duration(Duration.ofDays(days));
    }
}
