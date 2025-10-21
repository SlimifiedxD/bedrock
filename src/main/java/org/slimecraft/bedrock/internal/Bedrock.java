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
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * The main class for bedrock. {@link Bedrock#init(Plugin)} initializes Bedrock and allows the rest of the library to use
 * the functionality it provides.
 * This class should not be touched by end-users.
 */
@ApiStatus.Internal
public final class Bedrock {
    public static final EventNode BEDROCK_NODE = new EventNode(Key.key("slimecraft", "bedrock"));

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
        try (final ScanResult scanResult = new ClassGraph()
                .enableClassInfo()
                .scan()) {
            final Listener bukkitListener = new Listener() {
            };
            for (final ClassInfo classInfo : scanResult.getSubclasses("org.bukkit.event.Event")) {
                if (classInfo.isAbstract()) {
                    continue;
                }
                @SuppressWarnings("unchecked")
                Class<? extends Event> clazz = (Class<? extends Event>) classInfo.loadClass();

                if (clazz.getCanonicalName().contains("PlayerLoginEvent")) {
                    continue; // want to be able to use player configuration events so skip this unsupported hack event
                }
                try {
                    final Method method = clazz.getMethod("getHandlerList");
                    if (method.getDeclaringClass() != clazz) continue;
                    Bukkit.getServer().getPluginManager().registerEvent(clazz,
                            bukkitListener,
                            EventPriority.HIGHEST,
                            (listener, event) -> {
                                Events.fire(event);
                            }, plugin);
                } catch (NoSuchMethodException ignored) {
                }
            }
        }
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
