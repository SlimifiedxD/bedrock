package org.slimecraft.bedrock.kt.extensions

import org.slimecraft.bedrock.task.Task
import org.slimecraft.bedrock.task.TaskBuilder

class TaskDsl(private val builder: TaskBuilder) {
    var delay: Long
        set(value) {
            builder.delay(value)
        }
        get() = error("delay is write-only")
    var repeat: Long
        set(value) {
            builder.repeat(value)
        }
        get() = error("repeat is write-only")
    var delayRepeat: Long
        set(value) {
            builder.delayAndRepeat(value)
        }
        get() = error("delayRepeat is write-only")
    var ran: (task: Task) -> Unit
        set(value) {
            builder.whenRan(value)
        }
        get() = error("ran is write-only")
    var stopped: (task: Task) -> Unit
        set(value) {
            builder.whenStopped(value)
        }
        get() = error("stopped is write-only")
    var error: (task: Task, throwable: Throwable) -> Unit
        set(value) {
            builder.whenError(value)
        }
        get() = error("stopped is write-only")
    var expireWhen: (task: Task) -> Boolean
        set(value) {
            builder.expireWhen(value)
        }
        get() = error("expireWhen is write-only")
    var expireAfter: Long
        set(value) {
            builder.expireAfter(value)
        }
        get() = error("expireAfter is write-only")
    val async: Unit
        get() {
            builder.async()
        }
    fun then(block: TaskDsl.() -> Unit) {
        val nextBuilder = TaskBuilder()
        val nextDsl = TaskDsl(nextBuilder)

        nextDsl.apply(block)
        builder.then(nextBuilder)
    }
}

fun task(block: TaskDsl.() -> Unit): TaskBuilder {
    val builder = TaskBuilder()
    TaskDsl(builder).apply(block)
    return builder;
}