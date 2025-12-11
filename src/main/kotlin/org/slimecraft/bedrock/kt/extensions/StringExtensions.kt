package org.slimecraft.bedrock.kt.extensions

import net.kyori.adventure.text.Component
import org.slimecraft.bedrock.internal.Bedrock

fun String.component(): Component {
    return Bedrock.bedrock().miniMessage.deserialize(this)
}