package test.minecraft.mplugin.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TaskManager {
    private final Map<String, Runnable> task = new HashMap<>();

    private TaskManager() {
        PlayTimeManager playTime = PlayTimeManager.getInstance();

        this.set("notifyAll", () -> {
            //모든 플레이어에게 각각의 플레이 시간 출력
            Set<Player> players = new HashSet<>(Bukkit.getOnlinePlayers());
            for (Player p : players) {
                p.sendMessage(ChatColor.GREEN + playTime.print(p));
            }
        });

        //스케줄에 의해 자동 실행
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
