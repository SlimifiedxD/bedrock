package org.slimecraft.bedrock;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slimecraft.bedrock.event.Events;
import org.slimecraft.bedrock.util.FastBoardHelper;

/**
 * The main class for bedrock. {@link Bedrock#init(Plugin)} initializes Bedrock and allows the rest of the library to use
 * the functionality it provides.
 */
public final class Bedrock {
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
        FastBoardHelper.init();
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
                    clazz.getMethod("getHandlerList");
                    Bukkit.getServer().getPluginManager().registerEvent(clazz,
                            bukkitListener,
                            EventPriority.LOWEST,
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
     * @throws IllegalStateException When Bedrock has not been initialized.
     */
    public static Plugin getPlugin() {
        if (Bedrock.plugin == null)
            throw new IllegalStateException("Bedrock was not initialized! Please initialize it before using library functions.");
        return plugin;
    }
}
