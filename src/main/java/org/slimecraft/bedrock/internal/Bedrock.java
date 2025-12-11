package org.slimecraft.bedrock.internal;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.minimessage.MiniMessage;
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
    private final EventNode bedrockNode = new EventNode(Key.key("slimecraft", "bedrock"));
    private final Listener bukkitListener = new Listener() {};
    private final Set<Class<?>> lazyEvents = new HashSet<>();
    private MiniMessage miniMessage = MiniMessage.miniMessage();
    private Plugin plugin;

    static {
        try {
            Class.forName("org.slimecraft.bedrock.generated.GeneratedBedrockInit");
        } catch (ClassNotFoundException ignored) {
            ignored.printStackTrace();
        }
    }

    private Bedrock() {
    }

    public static Bedrock bedrock() {
        return Singleton.INSTANCE;
    }

    private static final class Singleton {
        public static final Bedrock INSTANCE = new Bedrock();
    }

    /**
     * Initialize bedrock with the given plugin.
     */
    public void init(Plugin plugin) {
        if (this.plugin != null) {
            return;
        }
        this.plugin = plugin;
        bedrockNode.attachTo(EventNode.global());
        FastBoardHelper.init();
        MenuManager.init();
    }

    /**
     * Get the plugin that Bedrock is using internally.
     *
     * @throws IllegalStateException When Bedrock has not been initialized.
     */
    public Plugin getPlugin() {
        if (plugin == null)
            throw new IllegalStateException("Bedrock was not initialized! Please initialize it before using library functions.");
        return plugin;
    }

    public MiniMessage getMiniMessage() {
        return miniMessage;
    }

    public void setMiniMessage(MiniMessage miniMessage) {
        this.miniMessage = miniMessage;
    }

    public Listener getBukkitListener() {
        return bukkitListener;
    }

    public Set<Class<?>> getLazyEvents() {
        return lazyEvents;
    }

    public EventNode getBedrockNode() {
        return bedrockNode;
    }
}
