package org.slimecraft.bedrock.internal;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;
import net.kyori.adventure.key.Key;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.ApiStatus;
import org.slimecraft.bedrock.event.EventNode;
import org.slimecraft.bedrock.event.Events;
import org.slimecraft.bedrock.menu.Menu;
import org.slimecraft.bedrock.util.FastBoardHelper;

import java.lang.reflect.Method;
import java.util.*;

/**
 * The main class for bedrock. {@link Bedrock#init(Plugin)} initializes Bedrock and allows the rest of the library to use
 * the functionality it provides.
 * This class should not be touched by end-users.
 */
@ApiStatus.Internal
public final class Bedrock {
    public static final EventNode BEDROCK_NODE = new EventNode(Key.key("slimecraft", "bedrock"));
    public static final Listener BUKKIT_LISTENER = new Listener() {};
    public static final Set<Class<?>> LAZY_EVENTS = new HashSet<>();

    static {
        try {
            Class.forName("org.slimecraft.bedrock.generated.GeneratedBedrockInit");
        } catch (ClassNotFoundException ignored) {
            ignored.printStackTrace();
        }
    }

    private static Plugin plugin;

    private Bedrock() {
    }

    /**
     * Initialize bedrock with the given plugin.
     */
    public static void init(Plugin plugin) {
        if (Bedrock.plugin != null) {
            return;
        }
        Bedrock.plugin = plugin;
        BEDROCK_NODE.attachTo(EventNode.global());
        FastBoardHelper.init();
        MenuManager.init();
    }

    /**
     * Get the plugin that Bedrock is using internally.
     *
     * @throws IllegalStateException When Bedrock has not been initialized.
     */
    public static Plugin getPlugin() {
        if (Bedrock.plugin == null)
            throw new IllegalStateException("Bedrock was not initialized! Please initialize it before using library functions.");
        return plugin;
    }
}
