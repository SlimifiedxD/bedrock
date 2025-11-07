package org.slimecraft.bedrock.kt.extensions

import net.kyori.adventure.text.Component
import org.slimecraft.bedrock.internal.Bedrock

fun String.serializeToComponent(): Component {
    return Bedrock.getMiniMessage().deserialize(this)
}