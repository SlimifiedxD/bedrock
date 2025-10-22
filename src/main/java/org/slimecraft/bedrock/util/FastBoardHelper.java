package org.slimecraft.bedrock.util;

import fr.mrmicky.fastboard.adventure.FastBoard;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;
import org.slimecraft.bedrock.event.EventNode;
import org.slimecraft.bedrock.internal.Bedrock;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

/**
 * A helper for {@link fr.mrmicky.fastboard.adventure.FastBoard}s that manages their state internally
 * for a more friendly developer experience.
 */
public final class FastBoardHelper {
    private FastBoardHelper() {}

    private static final Map<UUID, FastBoard> BOARDS = new HashMap<>();

    public static void init() {
        Bedrock.BEDROCK_NODE.addListener(PlayerQuitEvent.class, event -> delete(event.getPlayer()));
    }

    public static FastBoard create(Player player) {
        final FastBoard board = new FastBoard(player);
        BOARDS.put(player.getUniqueId(), board);
        return board;
    }

    public static void delete(Player player) {
        BOARDS.computeIfPresent(player.getUniqueId(), (uuid, board) -> {
            board.delete();
            return null;
        });
    }

    public static void refreshBoards(Function<Player, Component> title, Function<Player, Collection<Component>> lines) {
        BOARDS.values().forEach(board -> {
            board.updateTitle(title.apply(board.getPlayer()));
            board.updateLines(lines.apply(board.getPlayer()));
        });
    }

    public static void refreshBoards(Function<Player, Collection<Component>> lines) {
        BOARDS.values().forEach(board -> refreshBoards(player -> board.getTitle(), lines));
    }
}
