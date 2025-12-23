package org.slimecraft.bedrock.kt.extensions

import org.slimecraft.bedrock.util.Ticks

private fun longToTicks(long: Long, type: TickType): Long {
    return when(type) {
        TickType.SECONDS -> Ticks.seconds(long)
        TickType.MINUTES -> Ticks.minutes(long)
        TickType.HOURS -> Ticks.hours(long)
        TickType.DAYS -> Ticks.days(long)
    }
}

fun Int.ticks(type: TickType): Long {
    return longToTicks(this.toLong(), type)
}

fun Long.ticks(type: TickType): Long {
    return longToTicks(this, type)
}