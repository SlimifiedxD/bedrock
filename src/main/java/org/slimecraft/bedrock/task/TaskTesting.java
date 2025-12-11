package org.slimecraft.bedrock.task;

import org.bukkit.event.player.PlayerQuitEvent;
import org.slimecraft.bedrock.internal.Bedrock;

public class TaskTesting {
    void foo() {
        Task.builder()
                .whenRan(task -> {
                    System.out.println("root");
                })
                .run();
    }
}
