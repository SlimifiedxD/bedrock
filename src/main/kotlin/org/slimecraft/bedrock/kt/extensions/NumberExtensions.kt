package org.slimecraft.bedrock.kt.extensions

import org.slimecraft.bedrock.util.Ticks

val Int.seconds get() = Ticks.seconds(this.toLong())

val Int.minutes get() = Ticks.minutes(this.toLong())

val Int.hours get() = Ticks.hours(this.toLong())

val Int.days get() = Ticks.days(this.toLong())

val Long.seconds get() = Ticks.seconds(this)

val Long.minutes get() = Ticks.minutes(this)

val Long.hours get() = Ticks.hours(this)

val Long.days get() = Ticks.days(this)
