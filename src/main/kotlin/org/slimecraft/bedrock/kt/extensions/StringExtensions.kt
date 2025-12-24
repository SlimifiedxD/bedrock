package org.slimecraft.bedrock.kt.extensions

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.tag.Tag
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import org.slimecraft.bedrock.internal.Bedrock

fun String.component(vararg args: Pair<String, Component>): Component {
    val resolvers = args.map { TagResolver.resolver(it.first, Tag.selfClosingInserting { it.second }) }.toTypedArray()
    return Bedrock.bedrock().miniMessage.deserialize(this, *resolvers)
}