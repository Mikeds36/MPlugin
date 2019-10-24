package test.minecraft.mplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import test.minecraft.mplugin.commands.GamemodeCmd;
import test.minecraft.mplugin.commands.HelloCmd;
import test.minecraft.mplugin.commands.notify.NotifyCmd;
import test.minecraft.mplugin.core.GameEventListener;
import test.minecraft.mplugin.core.PlayTimeManager;
import test.minecraft.mplugin.core.ConstructTabCompleter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        // Hello, World!
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            PlayTimeManager playTime = PlayTimeManager.getInstance();
            Set<Player> players = new HashSet<>(Bukkit.getOnlinePlayers());
            for (Player p : players) {
                p.sendMessage(playTime.print(p)); //debug
            }
        }, 0L, 1200L);

        getServer().getPluginManager().registerEvents(new GameEventListener.PlayerJoin(this), this);
        Objects.requireNonNull(getCommand("hello")).setExecutor(new HelloCmd(this));
        Objects.requireNonNull(getCommand("gm")).setExecutor(new GamemodeCmd(this));
        Objects.requireNonNull(getCommand("gm")).setTabCompleter(new ConstructTabCompleter.Gamemode());
        Objects.requireNonNull(getCommand("notify")).setExecutor(new NotifyCmd(this));
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "Hello, World!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
