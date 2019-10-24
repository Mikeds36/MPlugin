package test.minecraft.mplugin.core;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TaskManager {
    private Map<String, Runnable> task = new HashMap<>();

    private TaskManager() {
        PlayTimeManager playTime = PlayTimeManager.getInstance();

        this.set("notifyAll", () -> {
            Set<Player> players = new HashSet<>(Bukkit.getOnlinePlayers());
            for (Player p : players) {
                p.sendMessage(playTime.print(p));
            }
        });

        this.set("notify_refresh", playTime::refresh);
    }

    private static class LazyHolder {
        static final TaskManager INSTANCE = new TaskManager();
    }

    public static TaskManager getInstance() {
        return LazyHolder.INSTANCE;
    }

    private void set(String key, Runnable task) {
        this.task.put(key, task);
    }

    public Runnable get(String key) {
        return this.task.get(key);
    }
}
